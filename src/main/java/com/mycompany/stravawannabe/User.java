/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stravawannabe;

/**
 *
 * @author antal
 */
public class User {
    private String name;
    private int age;
    private double weight;
    private double height; // in cm
    private String gender; // "Male", "Female", etc.
    private String goalType; // "Weight loss", "Staying fit", "Improving speed", "Hobby", etc.

    // Constructor
    public User(String name, int age, double weight, double height, String gender, String goalType) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.goalType = goalType;
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getWeight() { return weight; }
    public double getHeight() { return height; }
    public String getGender() { return gender; }
    public String getGoalType() { return goalType; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setHeight(double height) { this.height = height; }
    public void setGender(String gender) { this.gender = gender; }
    public void setGoalType(String goalType) { this.goalType = goalType; }

    // Optional: toString method to display user info
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

