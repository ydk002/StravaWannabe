/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stravawannabe;

/**
 *
 * @author antal
 */

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;


public class ActivityInput {

    public static void addActivityInteractive(Scanner scanner, Tracker tracker) {
        System.out.println("\n--- Add New Activity ---");

        List<String> validActivities = Arrays.asList("Running", "Cycling", "Walking", "Swimming", "Gym", "Hiking");
        String type = getValidatedString(scanner, "Enter activity type", validActivities);

        double distance = getPositiveDouble(scanner, "Enter distance (km): ");
        double duration = getPositiveDouble(scanner, "Enter duration (minutes): ");

        List<String> validLocations = Arrays.asList("Park", "Gym", "Street", "Trail", "Home");
        String location = getValidatedString(scanner, "Enter location", validLocations);

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
            for (String valid : validOptions) {
                if (input.equalsIgnoreCase(valid)) return valid;
            }
            System.out.println("Invalid input! Choose one from: " + validOptions);
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