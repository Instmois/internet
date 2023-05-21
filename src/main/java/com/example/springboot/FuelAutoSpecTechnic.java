package com.example.springboot;

import java.time.ZonedDateTime;

public class FuelAutoSpecTechnic extends InfoAutoSpecTechnic {
    public static final int dtime = 60; // minimum number seconds between measurements
    private static final double max_fc = 100; // litters per hour
    public static final double max_fuelc = max_fc / 3600 * dtime;
    double fuel_consumption;

    public FuelAutoSpecTechnic(int id_tech, ZonedDateTime time, double fuel_consumption) {
        this.id_tech = id_tech;
        this.time = time;
        this.fuel_consumption = fuel_consumption;
    }
}
