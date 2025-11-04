/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.stravawannabe;

import java.util.Scanner;

public class StravaWannabe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- USER SESSION ---
        UserManager userManager = new UserManager();

        // Load users from file at startup
        userManager.loadUsersFromFile("users.txt");

        SessionManager sessionManager = new SessionManager(userManager);
        User loggedInUser = sessionManager.startSession(scanner);

        // --- TRACKER AND MENU ---
        Tracker tracker = new Tracker(loggedInUser);
        System.out.println("\nTracker created for user: " + loggedInUser.getName());

        System.out.print("\nDo you want to load previous data from file? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            tracker.loadFromFile("activity_log.txt");
        }

        Menu menu = new Menu(scanner, tracker);
        menu.showMainMenu();

        System.out.println("\nSaving data before exit...");
        tracker.saveToFile("activity_log.txt"); // pass filename
        userManager.saveUsersToFile("users.txt"); // save user data
        System.out.println("Goodbye!");
    }
}
