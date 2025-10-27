/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stravawannabe;

/**
 *
 * @author antal
 */
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Tracker {
    private User user;
    private ArrayList<Activity> activities;

    // Constructor
    public Tracker(User user) {
        this.user = user;
        this.activities = new ArrayList<>();
    }
    
    
    public User getUser() {
    return user;
}


    // Add a new activity
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    // Get total distance (sum of all activities)
    public double totalDistance() {
        double total = 0;
        for (Activity a : activities) {
            total += a.getDistance();
        }
        return total;
    }

    // Get total duration (sum of all activities)
    public double totalDuration() {
        double total = 0;
        for (Activity a : activities) {
            total += a.getDuration();
        }
        return total;
    }

    // Get average pace across all activities (minutes per km)
    public double averagePace() {
        double totalDistance = totalDistance();
        if (totalDistance == 0) return 0;
        return totalDuration() / totalDistance;
    }

    // List all activities
    public void listActivities() {
        if (activities.isEmpty()) {
            System.out.println("No activities logged yet.");
        } else {
            for (Activity a : activities) {
                System.out.println(a);
            }
        }
    }
    
    public void loadFromFile(String filename) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        System.out.println("\nLoading data from " + filename + "...\n");

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        System.out.println("\nData loaded successfully!");
    } catch (IOException e) {
        System.out.println("Error loading data: " + e.getMessage());
    }
}

    
    public void saveToFile(String filename) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        // Save user info
        writer.write("User Info:\n");
        writer.write(user.toString() + "\n\n");

        // Save activities
        writer.write("Activities:\n");
        for (Activity a : activities) {
            writer.write(a.toString() + "\n");
        }

        System.out.println("Data saved successfully to " + filename);
    } catch (IOException e) {
        System.out.println("Error saving data: " + e.getMessage());
    }
}


    // Optional: show a summary of user stats
    public void showSummary() {
        System.out.println("User: " + user.getName());
        System.out.println("Goal: " + user.getGoalType());
        System.out.println("Total activities: " + activities.size());
        System.out.println("Total distance: " + String.format("%.2f", totalDistance()) + " km");
        System.out.println("Total duration: " + String.format("%.2f", totalDuration()) + " min");
        System.out.println("Average pace: " + String.format("%.2f", averagePace()) + " min/km");
    }
}

