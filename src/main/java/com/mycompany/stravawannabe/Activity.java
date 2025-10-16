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

public class Activity {
    private LocalDateTime dateTime; // when the activity took place
    private double distance; // in kilometers
    private double duration; // in minutes
    private String type; // "Running", "Cycling", etc.

    // Constructor
    public Activity(LocalDateTime dateTime, double distance, double duration, String type) {
        this.dateTime = dateTime;
        this.distance = distance;
        this.duration = duration;
        this.type = type;
    }

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Activity{" +
               "dateTime=" + dateTime.format(formatter) +
               ", distance=" + distance + " km" +
               ", duration=" + duration + " min" +
               ", type='" + type + '\'' +
               ", pace=" + String.format("%.2f", getPace()) + " min/km" +
               '}';
    }
}

