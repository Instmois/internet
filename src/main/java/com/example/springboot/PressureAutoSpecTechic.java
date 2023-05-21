package com.example.springboot;

import java.time.ZonedDateTime;

public class PressureAutoSpecTechic extends InfoAutoSpecTechnic{
    public static final int dtime = 1; // minimum number seconds between measurements
    public static final double min_pressure = 10.0; // MPascal
    public static final double max_pressure = 50.0; // MPascal
    double oil_pressure;

    public PressureAutoSpecTechic(int id_tech, ZonedDateTime time, double oil_pressure) {
        this.id_tech = id_tech;
        this.time = time;
        this.oil_pressure = oil_pressure;
    }
}
