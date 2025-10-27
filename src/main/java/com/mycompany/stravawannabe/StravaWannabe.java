/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.stravawannabe;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class StravaWannabe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- MULTI-USER LOGIN/REGISTER ---
        UserManager userManager = new UserManager();
        User loggedInUser = null;

        System.out.println("Welcome! Do you want to (1) Login or (2) Register?");
        String option = scanner.nextLine().trim();
        if (option.equals("1")) {
            loggedInUser = userManager.loginUser(scanner);
        } else {
            loggedInUser = userManager.registerUser(scanner);
        }

        // --- CREATE TRACKER FOR LOGGED-IN USER ---
        Tracker tracker = new Tracker(loggedInUser);
        System.out.println(loggedInUser);
        System.out.println("\nTracker created for user: " + loggedInUser.getName());

        // --- LOAD PREVIOUS DATA ---
        System.out.print("\nDo you want to load previous data from file? (yes/no): ");
        String loadChoice = scanner.nextLine();
        if (loadChoice.equalsIgnoreCase("yes")) {
            tracker.loadFromFile("activity_log.txt");
        }

        // ===== MENU LOOP START =====
        boolean running = true;
        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Add new activity");
            System.out.println("2. View all activities");
            System.out.println("3. Show summary");
            System.out.println("4. Save data to file");
            System.out.println("5. Load data from file");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addActivityInteractive(scanner, tracker);
                    break;
                case "2":
                    tracker.listActivities();
                    break;
                case "3":
                    tracker.showSummary();
                    break;
                case "4":
                    tracker.saveToFile("activity_log.txt");
                    break;
                case "5":
                    tracker.loadFromFile("activity_log.txt");
                    break;
                case "6":
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 6.");
            }
        }
        // ===== MENU LOOP END =====

        // --- DISPLAY ALL ACTIVITIES AND SUMMARY BEFORE EXIT ---
        System.out.println("\nAll logged activities:");
        tracker.listActivities();

        System.out.println("\nUser Summary:");
        tracker.showSummary();
        tracker.saveToFile("activity_log.txt");
    }

    // --- Helper method to add activity ---
    private static void addActivityInteractive(Scanner scanner, Tracker tracker) {
        System.out.println("\n--- Add New Activity ---");

        // === ACTIVITY TYPE ===
        List<String> validActivities = Arrays.asList("Running", "Cycling", "Walking", "Swimming", "Gym", "Hiking");
        String type = "";
        while (true) {
            System.out.print("Enter activity type (Running / Cycling / Walking / Swimming / Gym / Hiking): ");
            type = scanner.nextLine().trim();
            boolean matchFound = false;
            for (String valid : validActivities) {
                if (type.equalsIgnoreCase(valid)) {
                    type = valid; // normalize
                    matchFound = true;
                    break;
                }
            }
            if (matchFound) break;
            System.out.println("Invalid activity type! Please enter one of the available options.");
        }

        // === DISTANCE ===
        double distance = 0;
        while (true) {
            try {
                System.out.print("Enter distance (km): ");
                distance = Double.parseDouble(scanner.nextLine());
                if (distance > 0) break;
                System.out.println("Distance must be positive!");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a numeric value.");
            }
        }

        // === DURATION ===
        double duration = 0;
        while (true) {
            try {
                System.out.print("Enter duration (minutes): ");
                duration = Double.parseDouble(scanner.nextLine());
                if (duration > 0) break;
                System.out.println("Duration must be positive!");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a numeric value.");
            }
        }

        // === LOCATION ===
        List<String> validLocations = Arrays.asList("Park", "Gym", "Street", "Trail", "Home");
        String location = "";
        while (true) {
            System.out.print("Enter location (Park / Gym / Street / Trail / Home): ");
            location = scanner.nextLine().trim();
            boolean matchFound = false;
            for (String valid : validLocations) {
                if (location.equalsIgnoreCase(valid)) {
                    location = valid; // normalize
                    matchFound = true;
                    break;
                }
            }
            if (matchFound) break;
            System.out.println("Invalid location! Please enter one of the listed options.");
        }

        // === CREATE AND ADD ACTIVITY ===
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
}
