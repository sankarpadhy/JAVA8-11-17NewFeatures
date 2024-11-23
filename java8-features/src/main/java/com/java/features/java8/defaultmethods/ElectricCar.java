package com.java.features.java8.defaultmethods;

/**
 * Another implementation of Vehicle interface with custom implementation
 */
public class ElectricCar implements Vehicle {
    private final String brand;

    public ElectricCar(String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String startEngine() {
        return "Silently starting electric engine of " + getBrand();
    }

    @Override
    public String getStatus() {
        return getBrand() + " is charged and ready to go!";
    }
}
