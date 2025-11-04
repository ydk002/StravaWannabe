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
import java.io.*;

public class UserManager {

    private List<User> users;

    public UserManager() {
        users = new ArrayList<>();
    }

    public boolean hasUsers() {
        return !users.isEmpty();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean userExists(String name) {
        for (User u : users) {
            if (u.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public User getUserByName(String name) {
        for (User u : users) {
            if (u.getName().equalsIgnoreCase(name)) {
                return u;
            }
        }
        return null;
    }

    // -------------------
    // Interactive registration
    // -------------------
    public User registerUser(Scanner scanner) {
        System.out.println("\n--- Register New User ---");

        // Name
        String name;
        while (true) {
            System.out.print("Enter your name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty!");
            } else if (userExists(name)) {
                System.out.println("A user with this name already exists.");
            } else {
                break;
            }
        }

        // Age
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

        // Weight
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

        // Height
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

        // Gender
        String gender;
        while (true) {
            System.out.print("Enter gender: ");
            gender = scanner.nextLine().trim();
            if (!gender.isEmpty()) {
                break;
            }
            System.out.println("Gender cannot be empty!");
        }

        // Goal Type
        String goalType;
        List<String> validGoals = List.of("Weight loss", "Staying fit", "Improving speed", "Hobby");
        while (true) {
            System.out.print("Enter goal type " + validGoals + ": ");
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

        // Password
        String password;
        while (true) {
            System.out.print("Enter password: ");
            password = scanner.nextLine().trim();
            if (!password.isEmpty()) {
                break;
            }
            System.out.println("Password cannot be empty!");
        }

        User newUser = new User(name, age, weight, height, gender, goalType, password);
        addUser(newUser);
        System.out.println("User registered successfully!");
        return newUser;
    }

    // -------------------
    // Interactive login
    // -------------------
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
                return null;
            }

            if (name.isEmpty()) {
                System.out.println("Name cannot be empty!");
                continue;
            }

            User u = getUserByName(name);
            if (u != null) {
                // Ask for password
                System.out.print("Enter password: ");
                String password = scanner.nextLine().trim();
                if (u.checkPassword(password)) {
                    System.out.println("Logged in as " + u.getName());
                    return u;
                } else {
                    System.out.println("Incorrect password. Try again.");
                }
            } else {
                System.out.println("User not found. Try again or type 'exit' to return.");
            }
        }
    }

    // -------------------
    // File persistence
    // -------------------
    public void loadUsersFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            users.clear();
            while ((line = reader.readLine()) != null) {
                // parse line into User object, e.g. split by commas
                // example: name,age,weight,height,gender,goal,passwordHash
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    User u = new User(parts[0], Integer.parseInt(parts[1]), Double.parseDouble(parts[2]),
                            Double.parseDouble(parts[3]), parts[4], parts[5], parts[6], true);
                    users.add(u);
                }
            }
            System.out.println("Users loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    public void saveUsersToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (User u : users) {
                writer.write(String.join(",", u.getName(), String.valueOf(u.getAge()), String.valueOf(u.getWeight()),
                        String.valueOf(u.getHeight()), u.getGender(), u.getGoalType(),
                        u.getPasswordHash()));
                writer.newLine();
            }
            System.out.println("Users saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
}
