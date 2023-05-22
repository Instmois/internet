package com.example.springboot;

import java.util.ArrayList;
import java.util.Random;

public class Dt {
    ArrayList<AutoSpecTechnic> technics;
    ArrayList<HoursAutoSpecTechnic> hours_info;
    ArrayList<FuelAutoSpecTechnic> fuel_info;
    ArrayList<PressureAutoSpecTechic> pressure_info;
    public static Random rnd = new Random();

    public Dt() {
        String[] brand = {"Caterpillar","Hitachi","Hyundai","JCB"};
        String[][] model = {{"320D","320","336d","330D2"},{"ZX330", "ZX200", "ZX240"},{"R210LC","R290LC", "R480", "R520LS"},{"JS220", "JS330", "JS305", "JS160"}};
        technics = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int i1 = rnd.nextInt(0, brand.length);
            int i2 = rnd.nextInt(0, model[i1].length);
            AutoSpecTechnic tech = new AutoSpecTechnic(brand[i1], model[i1][i2], i + (int)1e6);
            technics.add(tech);
        }
        hours_info = new ArrayList<>();
        fuel_info = new ArrayList<>();
        pressure_info = new ArrayList<>();

    }
}
