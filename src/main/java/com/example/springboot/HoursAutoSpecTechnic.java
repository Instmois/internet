package com.example.springboot;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "hours", schema = "public")
@Component
public class HoursAutoSpecTechnic{
    public static final int dtime = 600; // minimum number seconds between measurements
    @Id
    @GeneratedValue
    @Column(name = "idh")
    long idh;
    @Column(name = "id_tech")
    int id_tech;
    @Column(name = "time")
    ZonedDateTime time;
    double engine_hours;
    double actual_hours;
    double downtime;

    public HoursAutoSpecTechnic() {
    }

    public HoursAutoSpecTechnic(int id_tech, ZonedDateTime time, double engine_hours, double actual_hours) {
        this.id_tech = id_tech;
        this.time = time;
        this.engine_hours = engine_hours;
        this.actual_hours = actual_hours;
        this.downtime = engine_hours - actual_hours;
    }

}
