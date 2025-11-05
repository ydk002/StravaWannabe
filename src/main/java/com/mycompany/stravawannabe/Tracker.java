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
import java.io.*;

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

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public double totalDistance() {
        double total = 0;
        for (Activity a : activities) total += a.getDistance();
        return total;
    }

    public double totalDuration() {
        double total = 0;
        for (Activity a : activities) total += a.getDuration();
        return total;
    }

    public double averagePace() {
        double totalDistance = totalDistance();
        return totalDistance == 0 ? 0 : totalDuration() / totalDistance;
    }

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
        activities.clear(); // clear previous data
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("\nLoading data from " + filename + "...\n");
            while ((line = reader.readLine()) != null) {
                // TODO: Parse the line into Activity object
                // For now, just print
                System.out.println(line);
            }
            System.out.println("\nData loaded successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("No previous activity file found for user. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Activity a : activities) {
                writer.write(a.toString() + "\n"); // for now, saving as string
            }
            System.out.println("Data saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void showSummary() {
        System.out.println("User: " + user.getName());
        System.out.println("Goal: " + user.getGoalType());
        System.out.println("Total activities: " + activities.size());
        System.out.println("Total distance: " + String.format("%.2f", totalDistance()) + " km");
        System.out.println("Total duration: " + String.format("%.2f", totalDuration()) + " min");
        System.out.println("Average pace: " + String.format("%.2f", averagePace()) + " min/km");
    }
}

