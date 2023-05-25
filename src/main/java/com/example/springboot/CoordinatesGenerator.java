package com.example.springboot;

import java.util.Random;

public class CoordinatesGenerator {
    private static final double MIN_LATITUDE = -90.0;
    private static final double MAX_LATITUDE = 90.0;
    private static final double MIN_LONGITUDE = -180.0;
    private static final double MAX_LONGITUDE = 180.0;
    public double[] coordLong;
    public double[] coordLat;

    CoordinatesGenerator() {
        coordLat = generateCoordinates(10, MIN_LATITUDE, MAX_LATITUDE);
        coordLong = generateCoordinates(10, MIN_LONGITUDE, MAX_LONGITUDE);
    }

    private double[] generateCoordinates(int count, double min, double max) {
        double[] coordinates = new double[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            coordinates[i] = min + (max - min) * random.nextDouble();
        }
        return coordinates;
    }
}
