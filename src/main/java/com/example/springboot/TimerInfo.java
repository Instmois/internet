package com.example.springboot;

import java.sql.SQLOutput;
import java.util.TimerTask;

public class TimerInfo extends TimerTask {
    @Override
    public void run() {
        long i = 0;
        while (true) {
            try {
                for (AutoSpecTechnic technic : Application.dt.technics) {
                    if (i % HoursAutoSpecTechnic.dtime == 0)
                        Application.dt.hours_info.add(technic.generate_hours_info());
                    if (i % FuelAutoSpecTechnic.dtime == 0)
                        Application.dt.fuel_info.add(technic.generate_fuel_info());
                    if (i % PressureAutoSpecTechic.dtime == 0)
                        Application.dt.pressure_info.add(technic.generate_pressure_info());
                }
                Thread.sleep(1000);
                i++;
            } catch (InterruptedException ex) {
                System.out.println(ex);
                break;
            }
        }
    }
}
