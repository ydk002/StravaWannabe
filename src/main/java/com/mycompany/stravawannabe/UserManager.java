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

        // Goal type validation using a classic loop
        List<String> validGoals = new ArrayList<>();
        validGoals.add("Weight loss");
        validGoals.add("Staying fit");
        validGoals.add("Improving speed");
        validGoals.add("Hobby");

        String goalType;
        while (true) {
            System.out.print("Enter goal type (Weight loss, Staying fit, Improving speed, Hobby): ");
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

        String password;  // <-- declare first
        while (true) {
            System.out.print("Enter password: ");
            password = scanner.nextLine().trim();  // <-- assign inside loop
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

    // Interactive login
    public User loginUser(Scanner scanner) {
        System.out.println("\n--- Login ---");

        if (users.isEmpty()) {
            System.out.println("No registered users found. Please register first!");
            return null;
        }

        while (true) {
            System.out.print("Enter your name (or 'exit' to go back): ");
            String name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("exit")) {
                return null;
            }

            User u = getUserByName(name);
            if (u == null) {
                System.out.println("User not found. Try again or type 'exit'.");
                continue;
            }

            // Ask for password
            System.out.print("Enter your password: ");
            String password = scanner.nextLine().trim();

            if (u.checkPassword(password)) {
                System.out.println("✅ Logged in as " + u.getName());
                return u;
            } else {
                System.out.println("Incorrect password! Try again.");
            }
        }
    }
    
      public void loadUsersFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            users.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                // Expected format: name|age|weight|height|gender|goalType|passwordHash
                String[] parts = line.split("\\|");
                if (parts.length == 7) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    double weight = Double.parseDouble(parts[2]);
                    double height = Double.parseDouble(parts[3]);
                    String gender = parts[4];
                    String goalType = parts[5];
                    String passwordHash = parts[6];

                    User user = new User(name, age, weight, height, gender, goalType, passwordHash, true);
                    users.add(user);
                }
            }
        } catch (FileNotFoundException e) {
            // File may not exist on first run – ignore
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    public void saveUsersToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (User u : users) {
                // Save in same format as above
                writer.write(String.join("|",
                        u.getName(),
                        String.valueOf(u.getAge()),
                        String.valueOf(u.getWeight()),
                        String.valueOf(u.getHeight()),
                        u.getGender(),
                        u.getGoalType(),
                        u.getPasswordHash()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
}

