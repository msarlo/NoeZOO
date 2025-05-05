package com.zoomanagement.model;

import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String password;
    private String name;
    private String email;
    private UserRole role;

    // Enum for user roles
    public enum UserRole {
        ADMIN,
        STAFF,
        VISITOR
    }

    // Constructors
    public User() {
        this.id = UUID.randomUUID();
    }

    public User(UUID id, String username, String password, String name, String email, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}