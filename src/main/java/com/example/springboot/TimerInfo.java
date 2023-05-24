package com.example.springboot;

import Services.FuelServices;
import Services.HourServices;
import Services.PressureServices;

import java.util.TimerTask;

public class TimerInfo extends TimerTask {
    private final HourServices hourServices;
    private final FuelServices fuelServices;
    private final PressureServices pressureServices;
    public TimerInfo(HourServices hourServices, FuelServices fuelServices, PressureServices pressureServices) {
        this.hourServices = hourServices;
        this.fuelServices = fuelServices;
        this.pressureServices = pressureServices;
    }
    @Override
    public void run() {
        long i = 0;
        while (true) {
            try {
                for (AutoSpecTechnic technic : Application.dt.technics) {
                    if (i % HoursAutoSpecTechnic.dtime == 0) {
                        HoursAutoSpecTechnic ht = technic.generate_hours_info();
                        Application.dt.hours_info.add(ht);
                        hourServices.saveHour(ht);
                    }
                    if (i % FuelAutoSpecTechnic.dtime == 0) {
                        FuelAutoSpecTechnic ft = technic.generate_fuel_info();
                        Application.dt.fuel_info.add(ft);
                        fuelServices.saveFuel(ft);
                    }
                    if (i % PressureAutoSpecTechnic.dtime == 0) {
                        PressureAutoSpecTechnic pt = technic.generate_pressure_info();
                        Application.dt.pressure_info.add(pt);
                        pressureServices.savePressure(pt);
                    }
                    if (i % 3600 == 0){
                        // TODO: Automatic delete old pressure data
                    }
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
