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
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        System.out.print("Enter your weight (kg): ");
        double weight = scanner.nextDouble();

        System.out.print("Enter your height (cm): ");
        double height = scanner.nextDouble();

        scanner.nextLine(); // consume leftover newline

        System.out.print("Enter your gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter your goal type (Weight loss, Staying fit, Improving speed, Hobby): ");
        String goalType = scanner.nextLine();

        User user = new User(name, age, weight, height, gender, goalType);
        System.out.println("\nUser created:");
        System.out.println(user);

        // Step 2: Create Tracker
        Tracker tracker = new Tracker(user);
        System.out.println("\nTracker created for user: " + user.getName());

        // Step 3: Log Activities
        System.out.print("\nHow many activities do you want to log? ");
        int numActivities = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline

        for (int i = 1; i <= numActivities; i++) {
            System.out.println("\nActivity " + i + ":");

            System.out.print("Enter activity type (Running, Cycling, etc.): ");
            String type = scanner.nextLine();

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

    }
}
