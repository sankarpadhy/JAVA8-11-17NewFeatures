package com.java.features.java8.defaultmethods;

/**
 * A basic car implementation that demonstrates the usage of default methods
 * from the Vehicle interface. This class only implements the required abstract
 * method getBrand() and inherits the default implementations of startEngine()
 * and getStatus().
 * 
 * Sample usage:
 * ```java
 * Car car = new Car("Toyota");
 * 
 * car.getBrand();      // Returns: "Toyota"
 * car.startEngine();   // Returns: "Starting engine of Toyota"
 * car.getStatus();     // Returns: "Toyota is ready to go!"
 * ```
 */
public class Car implements Vehicle {
    private final String brand;

    /**
     * Creates a new Car with the specified brand name.
     * 
     * Sample usage:
     * ```java
     * Car toyota = new Car("Toyota");
     * Car honda = new Car("Honda");
     * ```
     * 
     * @param brand The brand name of the car (e.g., "Toyota", "Honda")
     */
    public Car(String brand) {
        this.brand = brand;
    }

    /**
     * Returns the brand name of the car. This implements the abstract method
     * from the Vehicle interface.
     * 
     * Sample usage:
     * ```java
     * Car car = new Car("Toyota");
     * String brand = car.getBrand(); // Returns: "Toyota"
     * ```
     * 
     * @return The brand name of the car
     */
    @Override
    public String getBrand() {
        return brand;
    }

    // Note: This class inherits default implementations of startEngine() and getStatus()
    // from the Vehicle interface. These methods can be called on Car instances:
    //
    // car.startEngine() - Returns: "Starting engine of [brand]"
    // car.getStatus()   - Returns: "[brand] is ready to go!"
}
