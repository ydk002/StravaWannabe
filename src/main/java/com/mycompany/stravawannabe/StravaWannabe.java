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

        scanner.nextLine(); // consume leftover newline

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

        String goalType = "";
        while (true) {
            System.out.print("Enter your goal type (Weight loss, Staying fit, Improving speed, Hobby): ");
            goalType = scanner.nextLine().trim();
            if (!goalType.isEmpty()) {
                break;
            } else {
                System.out.println("Goal type cannot be empty! Please try again.");
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

        // Step 3: Log Activities
        System.out.print("\nHow many activities do you want to log? ");
        int numActivities = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline

        for (int i = 1; i <= numActivities; i++) {
            System.out.println("\nActivity " + i + ":");

            String type = "";
            while (true) {
                System.out.print("Enter activity type (Running, Cycling, etc.): ");
                type = scanner.nextLine().trim();
                if (!type.isEmpty()) {
                    break;
                } else {
                    System.out.println("Activity type cannot be empty! Please try again.");
                }
            }

            System.out.print("Enter distance (km): ");
            double distance = scanner.nextDouble();

            System.out.print("Enter duration (minutes): ");
            double duration = scanner.nextDouble();

            scanner.nextLine(); // consume leftover newline

            Activity activity = new Activity(LocalDateTime.now(), distance, duration, type);
            tracker.addActivity(activity);

            System.out.println("Activity logged successfully!");
        }

        // Step 4: Display all activities
        System.out.println("\nAll logged activities:");
        tracker.listActivities(); // prints all activities

// Display summary stats
        System.out.println("\nUser Summary:");
        tracker.showSummary();
        tracker.saveToFile("activity_log.txt");

    }
}
