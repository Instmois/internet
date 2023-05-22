package com.example.springboot;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.TimerTask;


public class AutoSpecTechnic {
    String brand;
    String model;
    int id_tech;

    public AutoSpecTechnic(String brand, String model, int id_tech) {
        this.brand = brand;
        this.model = model;
        this.id_tech = id_tech;
    }
    public HoursAutoSpecTechnic generate_hours_info(){
        double engine_hours = Dt.rnd.nextDouble() * (HoursAutoSpecTechnic.dtime - 0.0) / 3600;
        double actual_hours = Dt.rnd.nextDouble() * (engine_hours - 0.0);
        return new HoursAutoSpecTechnic(this.id_tech, ZonedDateTime.now(), engine_hours, actual_hours);
    }
    public PressureAutoSpecTechic generate_pressure_info(){
        double oil_pressure = Dt.rnd.nextDouble() * (PressureAutoSpecTechic.max_pressure - PressureAutoSpecTechic.min_pressure) + PressureAutoSpecTechic.min_pressure;
        return new PressureAutoSpecTechic(this.id_tech, ZonedDateTime.now(), oil_pressure);
    }
    public FuelAutoSpecTechnic generate_fuel_info(){
        double fuel_consumption = Dt.rnd.nextDouble() * (FuelAutoSpecTechnic.max_fuelc - 0.0);
        return new FuelAutoSpecTechnic(id_tech, ZonedDateTime.now(), fuel_consumption);
    }
}

