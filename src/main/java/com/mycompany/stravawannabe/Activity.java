/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stravawannabe;

/**
 *
 * @author antal
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Activity {
    private LocalDateTime dateTime; // when the activity took place
    private double distance; // in kilometers
    private double duration; // in minutes
    private String type; // "Running", "Cycling", etc.
    private String location;
    private double averageSpeed;
    private double caloriesBurned;

    // Constructor
    public Activity(LocalDateTime dateTime, double distance, double duration, String type, String location, double userWeight) {
        this.dateTime = dateTime;
        this.distance = distance;
        this.duration = duration;
        this.type = type;
        this.location = location;

        // Calculate derived data
        this.averageSpeed = distance / (duration / 60.0); // km/h
        this.caloriesBurned = calculateCalories(userWeight, distance);
    }

    // --- ABSTRACT CALORIE FORMULA ---
    // Each subclass must provide its own calorie calculation
    protected abstract double calculateCalories(double weight, double distance);

    // Getters
    public LocalDateTime getDateTime() { return dateTime; }
    public double getDistance() { return distance; }
    public double getDuration() { return duration; }
    public String getType() { return type; }

    // Setters
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setDistance(double distance) { this.distance = distance; }
    public void setDuration(double duration) { this.duration = duration; }
    public void setType(String type) { this.type = type; }

    // Calculate pace (minutes per km)
    public double getPace() {
        if (distance == 0) return 0;
        return duration / distance;
    }

    // Optional: formatted string for display
    @Override
    public String toString() {
        return String.format(
            "%s | Type: %s | Distance: %.2f km | Duration: %.1f min | Avg Speed: %.2f km/h | Calories: %.0f | Location: %s",
            dateTime.toString(), type, distance, duration, averageSpeed, caloriesBurned, location
        );
    }
}

