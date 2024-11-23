package com.java.features.java8.defaultmethods;

/**
 * Demonstrates default methods in interfaces introduced in Java 8
 */
public interface Vehicle {
    
    /**
     * Abstract method that must be implemented
     * @return The vehicle brand
     */
    String getBrand();

    /**
     * Default method with implementation
     * @return Standard start message
     */
    default String startEngine() {
        return "Starting engine of " + getBrand();
    }

    /**
     * Another default method that uses other interface methods
     * @return Status message
     */
    default String getStatus() {
        return getBrand() + " is ready to go!";
    }

    /**
     * Static method in interface
     * @return General vehicle info
     */
    static String getVehicleInfo() {
        return "This is a vehicle interface with default methods";
    }
}
