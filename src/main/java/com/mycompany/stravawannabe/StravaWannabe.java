/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.stravawannabe;

/**
 *
 * @author antal
 */
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class StravaWannabe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Create User
        String name = "";
        while (true) {
            System.out.print("Enter your name: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                break;
            } else {
                System.out.println("Name cannot be empty! Please try again.");
            }
        }

        int age = 0;
        while (true) {
            try {
                System.out.print("Enter your age: ");
                age = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number for age.");
            }
        }

        // --- WEIGHT (with error handling) ---
        double weight = 0;
        while (true) {
            try {
                System.out.print("Enter your weight (kg): ");
                weight = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number for weight.");
            }
        }

        // --- HEIGHT (with error handling) ---
        double height = 0;
        while (true) {
            try {
                System.out.print("Enter your height (cm): ");
                height = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number for height.");
            }
        }

        String gender = "";
        while (true) {
            System.out.print("Enter your gender: ");
            gender = scanner.nextLine().trim();
            if (!gender.isEmpty()) {
                break;
            } else {
                System.out.println("Gender cannot be empty! Please try again.");
            }
        }

        // --- GOAL TYPE (validated) ---
        List<String> validGoals = Arrays.asList(
                "Weightloss", "Staying fit", "Improving speed", "Hobby"
        );

        String goalType = "";
        while (true) {
            System.out.print("Enter your goal type (Weightloss / Staying fit / Improving speed / Hobby): ");
            goalType = scanner.nextLine().trim();

            boolean matchFound = false;
            for (String valid : validGoals) {
                if (goalType.equalsIgnoreCase(valid)) {
                    goalType = valid; // normalize capitalization
                    matchFound = true;
                    break;
                }
            }

            if (matchFound) {
                break;
            } else {
                System.out.println("This is not a possible goal type. Please choose one of the listed options.");
            }
        }

        User user = new User(name, age, weight, height, gender, goalType);
        System.out.println("\nUser created:");
        System.out.println(user);

        // Step 2: Create Tracker
        Tracker tracker = new Tracker(user);
        System.out.println("\nTracker created for user: " + user.getName());
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

        // Step 4: Display all activities
        System.out.println("\nAll logged activities:");
        tracker.listActivities(); // prints all activities

        // Display summary stats
        System.out.println("\nUser Summary:");
        tracker.showSummary();
        tracker.saveToFile("activity_log.txt");

    }
    // --- Helper method stubs (implement next) ---

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

            if (matchFound) {
                break;
            } else {
                System.out.println("Invalid activity type! Please enter one of the available options.");
            }
        }

        // === DISTANCE ===
        double distance = 0;
        while (true) {
            try {
                System.out.print("Enter distance (km): ");
                distance = Double.parseDouble(scanner.nextLine());
                if (distance > 0) {
                    break;
                } else {
                    System.out.println("Distance must be positive!");
                }
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
                if (duration > 0) {
                    break;
                } else {
                    System.out.println("Duration must be positive!");
                }
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
                    location = valid; // normalize capitalization
                    matchFound = true;
                    break;
                }
            }

            if (matchFound) {
                break;
            } else {
                System.out.println("Invalid location! Please enter one of the listed options.");
            }
        }

        // === CREATE AND ADD ACTIVITY ===
        Activity activity = new Activity(
                LocalDateTime.now(),
                distance,
                duration,
                type,
                location,
                tracker.getUser().getWeight() // use the user's weight for calorie calculation
        );

        tracker.addActivity(activity);
        System.out.println("Activity added successfully!");
    }
}
