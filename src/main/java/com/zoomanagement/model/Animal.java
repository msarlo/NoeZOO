package com.zoomanagement.model;

import java.util.Date;
import java.util.UUID;

public class Animal {
    private UUID id;
    private String name;
    private String species;
    private String habitat;
    private Date arrivalDate;
    private HealthStatus healthStatus;

    // Enum for health status
    public enum HealthStatus {
        HOSPITALIZED,
        OBSERVATION,
        HEALTHY
    }

    // Constructors
    public Animal() {
        this.id = UUID.randomUUID();
    }

    public Animal(UUID id, String name, String species, String habitat, Date arrivalDate, HealthStatus healthStatus) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.habitat = habitat;
        this.arrivalDate = arrivalDate;
        this.healthStatus = healthStatus;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }
}