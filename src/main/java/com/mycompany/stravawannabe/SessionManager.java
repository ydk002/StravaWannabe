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

public class SessionManager {

    private final UserManager userManager;

    public SessionManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public User startSession(Scanner scanner) {
        User loggedInUser = null;

        while (loggedInUser == null) {
            System.out.println("Welcome! Do you want to (1) Login or (2) Register?");
            String option = scanner.nextLine().trim();

            if (option.equals("1")) {
                if (!userManager.hasUsers()) {
                    System.out.println("No users are registered yet. Please register first!\n");
                    continue;
                }
                loggedInUser = userManager.loginUser(scanner);
            } else if (option.equals("2")) {
                loggedInUser = userManager.registerUser(scanner);
            } else {
                System.out.println("Invalid option. Please choose 1 or 2.");
            }

            if (loggedInUser == null) {
                System.out.println("Login or registration failed. Try again.\n");
            }
            
        }

        return loggedInUser;
    }
}
