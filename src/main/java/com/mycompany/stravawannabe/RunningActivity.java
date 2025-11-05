/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stravawannabe;
import java.time.LocalDateTime;
/**
 *
 * @author antal
 */
public class RunningActivity extends Activity implements Shareable {

    public RunningActivity(LocalDateTime dateTime, double distance, double duration, String location, double userWeight) {
        super(dateTime, distance, duration, "Running", location, userWeight);
    }

    // Running calorie formula:
    // approx: 1 kcal burned per kg per km
    @Override
    protected double calculateCalories(double weight, double distance) {
        return weight * distance;
    }

    @Override
    public void share() {
        System.out.println("Sharing Running Activity...");
        System.out.println(this.toString()); // prints formatted activity data
    }
}
