/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stravawannabe;

/**
 *
 * @author antal
 */
import java.time.LocalDateTime;

public class WalkingActivity extends Activity {

    public WalkingActivity(LocalDateTime dateTime, double distance, double duration, String location, double userWeight) {
        super(dateTime, distance, duration, "Walking", location, userWeight);
    }

    @Override
    protected double calculateCalories(double weight, double distance) {
        // Walking burns less than running/cycling
        // Approx formula: 0.5 * weight * distance
        return 0.5 * weight * distance;
    }
}