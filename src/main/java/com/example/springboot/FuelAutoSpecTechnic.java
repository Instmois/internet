package com.example.springboot;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "fuel", schema = "public")
@Component
public class FuelAutoSpecTechnic{
    public static final int dtime = 60; // minimum number seconds between measurements
    @Id
    @GeneratedValue
    @Column(name = "idf")
    long idf;
    @Column(name = "id_tech")
    int id_tech;
    @Column(name = "time")
    ZonedDateTime time;
    private static final double max_fc = 50; // litters per hour
    public static final double max_fuelc = max_fc / 3600 * dtime;
    @Column(name = "consumption")
    double fuel_consumption;

    public FuelAutoSpecTechnic() {
    }

    public FuelAutoSpecTechnic(int id_tech, ZonedDateTime time, double fuel_consumption) {
        this.id_tech = id_tech;
        this.time = time;
        this.fuel_consumption = fuel_consumption;
    }

}
