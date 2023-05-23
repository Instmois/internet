package com.example.springboot;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "users", schema = "public")
@Component
public class AutoSpecTechnic {

    @Column(name = "brand")
    String brand;
    @Column(name = "model")
    String model;
    @Id
    @Column(name = "id_tech")
    int id;
    public HoursAutoSpecTechnic generate_hours_info(){
        double engine_hours = Dt.rnd.nextDouble() * (HoursAutoSpecTechnic.dtime - 0.0) / 3600;
        double actual_hours = Dt.rnd.nextDouble() * (engine_hours - 0.0);
        return new HoursAutoSpecTechnic(this.id, ZonedDateTime.now(), engine_hours, actual_hours);
    }
    public PressureAutoSpecTechic generate_pressure_info(){
        double oil_pressure = Dt.rnd.nextDouble() * (PressureAutoSpecTechic.max_pressure - PressureAutoSpecTechic.min_pressure) + PressureAutoSpecTechic.min_pressure;
        return new PressureAutoSpecTechic(this.id, ZonedDateTime.now(), oil_pressure);
    }
    public FuelAutoSpecTechnic generate_fuel_info(){
        double fuel_consumption = Dt.rnd.nextDouble() * (FuelAutoSpecTechnic.max_fuelc - 0.0);
        return new FuelAutoSpecTechnic(id, ZonedDateTime.now(), fuel_consumption);
    }
}

