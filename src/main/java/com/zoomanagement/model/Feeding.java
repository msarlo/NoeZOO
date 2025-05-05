package com.zoomanagement.model;

import java.util.Date;
import java.util.UUID;

public class Feeding {
    private UUID id;
    private Animal animal;
    private String foodType;
    private Date feedingTime;
    private User responsibleStaff;
    private double quantity;
    private String observations;

    // Constructors
    public Feeding() {
        this.id = UUID.randomUUID();
    }

    public Feeding(UUID id, Animal animal, String foodType, Date feedingTime, User responsibleStaff, double quantity, String observations) {
        this.id = id;
        this.animal = animal;
        this.foodType = foodType;
        this.feedingTime = feedingTime;
        this.responsibleStaff = responsibleStaff;
        this.quantity = quantity;
        this.observations = observations;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public Date getFeedingTime() {
        return feedingTime;
    }

    public void setFeedingTime(Date feedingTime) {
        this.feedingTime = feedingTime;
    }

    public User getResponsibleStaff() {
        return responsibleStaff;
    }

    public void setResponsibleStaff(User responsibleStaff) {
        this.responsibleStaff = responsibleStaff;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
