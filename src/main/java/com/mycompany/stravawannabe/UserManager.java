/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stravawannabe;

/**
 *
 * @author antal
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManager {

    private List<User> users;

    public boolean hasUsers() {
        return !users.isEmpty();
    }

    public UserManager() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    // Check if a username already exists
    public boolean userExists(String name) {
        for (User u : users) {
            if (u.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // Find a user by name
    public User getUserByName(String name) {
        for (User u : users) {
            if (u.getName().equalsIgnoreCase(name)) {
                return u;
            }
        }
        return null; // not found
    }

    // Interactive registration
    public User registerUser(Scanner scanner) {
        System.out.println("\n--- Register New User ---");
        String name;
        while (true) {
            System.out.print("Enter your name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty!");
            } else if (userExists(name)) {
                System.out.println("A user with this name already exists. Choose another.");
            } else {
                break;
            }
        }

        // Repeat similar loops for age, weight, height, gender, goalType
        // For simplicity, you can copy your existing input-validation code here
        int age = 0;
        while (true) {
            try {
                System.out.print("Enter age: ");
                age = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }

        double weight = 0;
        while (true) {
            try {
                System.out.print("Enter weight (kg): ");
                weight = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }

        double height = 0;
        while (true) {
            try {
                System.out.print("Enter height (cm): ");
                height = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }

        String gender;
        while (true) {
            System.out.print("Enter gender: ");
            gender = scanner.nextLine().trim();
            if (!gender.isEmpty()) {
                break;
            }
            System.out.println("Gender cannot be empty!");
        }

        // Example goalType validation (can use your validated list)
        String goalType;
        List<String> validGoals = List.of("Weight loss", "Staying fit", "Improving speed", "Hobby");
        while (true) {
            System.out.print("Enter goal type(Weight loss, Staying fit, Improving speed, Hobby): ");
            goalType = scanner.nextLine().trim();
            boolean match = false;
            for (String g : validGoals) {
                if (goalType.equalsIgnoreCase(g)) {
                    goalType = g; // normalize
                    match = true;
                    break;
                }
            }
            if (match) {
                break;
            }
            System.out.println("Invalid goal type! Choose one of: " + validGoals);
        }

        User newUser = new User(name, age, weight, height, gender, goalType);
        addUser(newUser);
        System.out.println(" User registered successfully!");
        return newUser;
    }

    // Interactive login
    public User loginUser(Scanner scanner) {
    System.out.println("\n--- Login ---");

    if (users.isEmpty()) {
        System.out.println("No registered users found. Please register first!");
        return null;
    }

    while (true) {
        System.out.print("Enter your name (or type 'exit' to go back): ");
        String name = scanner.nextLine().trim();

        if (name.equalsIgnoreCase("exit")) {
            return null; // go back to main menu
        }

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            continue;
        }

        User u = getUserByName(name);
        if (u != null) {
            System.out.println("Logged in as " + u.getName());
            return u;
        } else {
            System.out.println("User not found. Try again or type 'exit' to return.");
        }
    }
}
}
