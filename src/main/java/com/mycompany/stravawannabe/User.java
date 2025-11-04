/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stravawannabe;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author antal
 */
public class User {
    private String name;
    private int age;
    private double weight;
    private double height;
    private String gender;
    private String goalType;
    private String passwordHash; // new field

    // Constructor
    public User(String name, int age, double weight, double height, String gender, String goalType, String password) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.goalType = goalType;
        this.passwordHash = hashPassword(password);
    }
    
    public User(String name, int age, double weight, double height, String gender, String goalType, String passwordHash, boolean isHashed) {
    this.name = name;
    this.age = age;
    this.weight = weight;
    this.height = height;
    this.gender = gender;
    this.goalType = goalType;
    this.passwordHash = passwordHash;
}

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getWeight() { return weight; }
    public double getHeight() { return height; }
    public String getGender() { return gender; }
    public String getGoalType() { return goalType; }

    // --- Password Handling ---
    public boolean checkPassword(String password) {
        return passwordHash.equals(hashPassword(password));
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    // --- Utility to hash password ---
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "User{" +
               "name='" + name + '\'' +
               ", age=" + age +
               ", weight=" + weight +
               ", height=" + height +
               ", gender='" + gender + '\'' +
               ", goalType='" + goalType + '\'' +
               '}';
    }
}

