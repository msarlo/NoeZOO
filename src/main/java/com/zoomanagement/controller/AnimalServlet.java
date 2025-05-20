package com.zoomanagement.controller;

import com.zoomanagement.model.Animal;
import com.zoomanagement.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@WebServlet("/animal/*")
public class AnimalServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            // List all animals
            request.getRequestDispatcher("/WEB-INF/views/animal/list.jsp").forward(request, response);
        } else if (pathInfo.equals("/new")) {
            // Show create form
            request.getRequestDispatcher("/WEB-INF/views/animal/form.jsp").forward(request, response);
        } else if (pathInfo.startsWith("/edit/")) {
            // Show edit form
            String idStr = pathInfo.substring(6);
            UUID id = UUID.fromString(idStr);
            // TODO: Get animal by ID and set to request attribute
            request.getRequestDispatcher("/WEB-INF/views/animal/form.jsp").forward(request, response);
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

        // Process form submission
        String name = request.getParameter("name");
        String species = request.getParameter("species");
        String habitat = request.getParameter("habitat");
        String healthStatusStr = request.getParameter("healthStatus");
        String arrivalDateStr = request.getParameter("arrivalDate");

        try {
            Animal animal = new Animal();
            animal.setName(name);
            animal.setSpecies(species);
            animal.setHabitat(habitat);
            animal.setHealthStatus(Animal.HealthStatus.valueOf(healthStatusStr));
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date arrivalDate = dateFormat.parse(arrivalDateStr);
            animal.setArrivalDate(arrivalDate);

            // TODO: Save animal to database
            
            response.sendRedirect(request.getContextPath() + "/animal");
        } catch (ParseException e) {
            request.setAttribute("error", "Invalid date format");
            request.getRequestDispatcher("/WEB-INF/views/animal/form.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Handle update
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Handle delete
    }
}