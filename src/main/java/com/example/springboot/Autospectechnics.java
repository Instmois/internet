package com.example.springboot;

public class Autospectechnics {
    String brand;
    String model;
    int id_tech;

    public Autospectechnics(String brand, String model, int id_tech) {
        this.brand = brand;
        this.model = model;
        this.id_tech = id_tech;
    }

    double oil_pressure; // 10-40
    double engine_hours;
    double actual_hours;
    double downtime;
    double fuel_consumption;
    public double getOil_pressure() {
        return oil_pressure;
    }

    public void setOil_pressure(double oil_pressure) {
        this.oil_pressure = oil_pressure;
    }

    public double getEngine_hours() {
        return engine_hours;
    }

    public void setEngine_hours(double engine_hours) {
        this.engine_hours = engine_hours;
    }

    public double getActual_hours() {
        return actual_hours;
    }

    public void setActual_hours(double actual_hours) {
        this.actual_hours = actual_hours;
    }

    public double getDowntime() {
        return downtime;
    }

    public void setDowntime(double downtime) {
        this.downtime = downtime;
    }

    public double getFuel_consumption() {
        return fuel_consumption;
    }

    public void setFuel_consumption(double fuel_consumption) {
        this.fuel_consumption = fuel_consumption;
    }


}
