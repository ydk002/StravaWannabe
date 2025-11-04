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
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


public class ActivityInput {

    public static void addActivityInteractive(Scanner scanner, Tracker tracker) {
        System.out.println("\n--- Add New Activity ---");

        // Valid activities
        List<String> validActivities = new ArrayList<>();
        validActivities.add("Running");
        validActivities.add("Cycling");
        validActivities.add("Walking");
        validActivities.add("Swimming");
        validActivities.add("Gym");
        validActivities.add("Hiking");

        String type = getValidatedString(scanner, "Enter activity type", validActivities);

        double distance = getPositiveDouble(scanner, "Enter distance (km): ");
        double duration = getPositiveDouble(scanner, "Enter duration (minutes): ");

        // Valid locations
        List<String> validLocations = new ArrayList<>();
        validLocations.add("Park");
        validLocations.add("Gym");
        validLocations.add("Street");
        validLocations.add("Trail");
        validLocations.add("Home");

        String location = getValidatedString(scanner, "Enter location", validLocations);

        // Create activity and add to tracker
        Activity activity = new Activity(
                LocalDateTime.now(),
                distance,
                duration,
                type,
                location,
                tracker.getUser().getWeight()
        );

        tracker.addActivity(activity);
        System.out.println("Activity added successfully!");
    }

    private static String getValidatedString(Scanner scanner, String prompt, List<String> validOptions) {
        String input;
        while (true) {
            System.out.print(prompt + " " + validOptions + ": ");
            input = scanner.nextLine().trim();

            boolean matchFound = false;
            for (String option : validOptions) {
                if (input.equalsIgnoreCase(option)) {
                    input = option; // normalize
                    matchFound = true;
                    break;
                }
            }

            if (matchFound) {
                return input;
            } else {
                System.out.println("Invalid input! Choose one from: " + validOptions);
            }
        }
    }

    private static double getPositiveDouble(Scanner scanner, String prompt) {
        double value;
        while (true) {
            try {
                System.out.print(prompt);
                value = Double.parseDouble(scanner.nextLine());
                if (value > 0) return value;
                System.out.println("Value must be positive!");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number! Try again.");
            }
        }
    }
}