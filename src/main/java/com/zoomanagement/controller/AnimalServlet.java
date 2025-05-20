package com.zoomanagement.controller;

import com.zoomanagement.dao.AnimalDao;
import com.zoomanagement.model.Animal;
import com.zoomanagement.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet("/animal/*")
public class AnimalServlet extends HttpServlet {
    private final AnimalDao animalDao;
    
    public AnimalServlet() {
        this.animalDao = new AnimalDao();
        try {
            this.animalDao.createTable();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create animals table", e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // List all animals
                List<Animal> animals = animalDao.findAll();
                request.setAttribute("animals", animals);
                request.getRequestDispatcher("/WEB-INF/views/animal/list.jsp").forward(request, response);
            } else if (pathInfo.equals("/new")) {
                // Show create form
                request.getRequestDispatcher("/WEB-INF/views/animal/form.jsp").forward(request, response);
            } else if (pathInfo.startsWith("/edit/")) {
                // Show edit form
                String idStr = pathInfo.substring(6);
                UUID id = UUID.fromString(idStr);
                Animal animal = animalDao.findById(id);
                if (animal != null) {
                    request.setAttribute("animal", animal);
                    request.getRequestDispatcher("/WEB-INF/views/animal/form.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Check user authorization
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        User currentUser = (User) session.getAttribute("user");
        if (currentUser.getRole() != User.UserRole.ADMIN && 
            currentUser.getRole() != User.UserRole.STAFF) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        try {
            // Process form submission
            String name = request.getParameter("name");
            String species = request.getParameter("species");
            String habitat = request.getParameter("habitat");
            String healthStatusStr = request.getParameter("healthStatus");
            String arrivalDateStr = request.getParameter("arrivalDate");

            Animal animal = new Animal();
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                animal.setId(UUID.fromString(idStr));
            }
            
            animal.setName(name);
            animal.setSpecies(species);
            animal.setHabitat(habitat);
            animal.setHealthStatus(Animal.HealthStatus.valueOf(healthStatusStr));
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date arrivalDate = dateFormat.parse(arrivalDateStr);
            animal.setArrivalDate(arrivalDate);

            if (idStr != null && !idStr.isEmpty()) {
                animalDao.update(animal);
                request.setAttribute("message", "Animal updated successfully");
            } else {
                animalDao.save(animal);
                request.setAttribute("message", "Animal created successfully");
            }
            
            response.sendRedirect(request.getContextPath() + "/animal");
        } catch (ParseException e) {
            request.setAttribute("error", "Invalid date format");
            request.getRequestDispatcher("/WEB-INF/views/animal/form.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Check user authorization
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        
        User currentUser = (User) session.getAttribute("user");
        if (currentUser.getRole() != User.UserRole.ADMIN && 
            currentUser.getRole() != User.UserRole.STAFF) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        try {
            String pathInfo = request.getPathInfo();
            if (pathInfo != null && pathInfo.length() > 1) {
                String idStr = pathInfo.substring(1);
                UUID id = UUID.fromString(idStr);
                animalDao.delete(id);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}