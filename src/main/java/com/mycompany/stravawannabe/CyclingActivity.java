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

public class CyclingActivity extends Activity {

    public CyclingActivity(LocalDateTime dateTime, double distance, double duration, String location, double userWeight) {
        super(dateTime, distance, duration, "Cycling", location, userWeight);
    }

    // Approx formula:
    // Cycling burns around 0.3 * weight * distance (rough average)
    @Override
    protected double calculateCalories(double weight, double distance) {
        return 0.3 * weight * distance;
    }
}
