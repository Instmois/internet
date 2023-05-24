package com.example.springboot;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "pressure", schema = "public")
@Component
public class PressureAutoSpecTechnic {
    public static final int dtime = 1; // minimum number seconds between measurements
    @Id
    @GeneratedValue
    @Column(name = "idp")
    long idp;
    @Column(name = "id_tech")
    int id_tech;
    @Column(name = "time")
    ZonedDateTime time;
    public static final double min_pressure = 10.0; // MPascal
    public static final double max_pressure = 50.0; // MPascal
    @Column(name = "oil_pressure")
    double oil_pressure;

    public PressureAutoSpecTechnic() {
    }

    public PressureAutoSpecTechnic(int id_tech, ZonedDateTime time, double oil_pressure) {
        this.id_tech = id_tech;
        this.time = time;
        this.oil_pressure = oil_pressure;
    }

}
