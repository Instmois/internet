package com.example.springboot;

import java.time.ZonedDateTime;

public class HoursAutoSpecTechnic extends InfoAutoSpecTechnic {
    public static final int dtime = 600; // minimum number seconds between measurements
    double engine_hours;
    double actual_hours;
    double downtime;

    public HoursAutoSpecTechnic(int id_tech, ZonedDateTime time, double engine_hours, double actual_hours) {
        this.id_tech = id_tech;
        this.time = time;
        this.engine_hours = engine_hours;
        this.actual_hours = actual_hours;
        this.downtime = engine_hours - actual_hours;
    }
}
