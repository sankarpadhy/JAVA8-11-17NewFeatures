package com.java.features.java8.defaultmethods;

/**
 * Implementation of Vehicle interface demonstrating default method usage
 */
public class Car implements Vehicle {
    private final String brand;

    public Car(String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    // Using default implementation of startEngine() and getStatus()
}
