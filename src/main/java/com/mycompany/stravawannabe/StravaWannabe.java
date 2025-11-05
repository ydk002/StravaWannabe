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
        userManager.loadUsersFromFile("users.txt"); // load all users

        SessionManager sessionManager = new SessionManager(userManager);
        User loggedInUser = sessionManager.startSession(scanner);

        // --- TRACKER AND MENU ---
        Tracker tracker = new Tracker(loggedInUser);
        System.out.println("\nTracker created for user: " + loggedInUser.getName());

        String userActivityFile = "activities_" + loggedInUser.getName() + ".txt";

        System.out.print("\nDo you want to load previous data from your activities file? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            tracker.loadFromFile(userActivityFile);
        }

        Menu menu = new Menu(scanner, tracker, userActivityFile); // pass filename to menu
        menu.showMainMenu();

        System.out.println("\nSaving data before exit...");
        tracker.saveToFile(userActivityFile); // save per-user activities
        userManager.saveUsersToFile("users.txt"); // save all users
        System.out.println("Goodbye!");
    }
}
