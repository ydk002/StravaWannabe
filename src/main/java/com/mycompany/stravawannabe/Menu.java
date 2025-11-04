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

public class Menu {

    private final Scanner scanner;
    private final Tracker tracker;

    public Menu(Scanner scanner, Tracker tracker) {
        this.scanner = scanner;
        this.tracker = tracker;
    }

    public void showMainMenu() {
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
                    ActivityInput.addActivityInteractive(scanner, tracker);
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
    }
}
