package com.java.features.java8.defaultmethods;

/**
 * An electric car implementation that demonstrates overriding default methods
 * from the Vehicle interface. Unlike the basic Car class, ElectricCar provides
 * custom implementations for both startEngine() and getStatus() to reflect its
 * electric nature.
 * 
 * Sample usage:
 * ```java
 * ElectricCar tesla = new ElectricCar("Tesla");
 * 
 * tesla.getBrand();      // Returns: "Tesla"
 * tesla.startEngine();   // Returns: "Silently starting electric engine of Tesla"
 * tesla.getStatus();     // Returns: "Tesla is charged and ready to go!"
 * 
 * // Compare with regular Car:
 * Car toyota = new Car("Toyota");
 * toyota.startEngine();  // Returns: "Starting engine of Toyota"
 * toyota.getStatus();    // Returns: "Toyota is ready to go!"
 * ```
 */
public class ElectricCar implements Vehicle {
    private final String brand;

    /**
     * Creates a new ElectricCar with the specified brand name.
     * 
     * Sample usage:
     * ```java
     * ElectricCar tesla = new ElectricCar("Tesla");
     * ElectricCar rivian = new ElectricCar("Rivian");
     * ```
     * 
     * @param brand The brand name of the electric car (e.g., "Tesla", "Rivian")
     */
    public ElectricCar(String brand) {
        this.brand = brand;
    }

    /**
     * Returns the brand name of the electric car. This implements the abstract
     * method from the Vehicle interface.
     * 
     * Sample usage:
     * ```java
     * ElectricCar car = new ElectricCar("Tesla");
     * String brand = car.getBrand(); // Returns: "Tesla"
     * ```
     * 
     * @return The brand name of the electric car
     */
    @Override
    public String getBrand() {
        return brand;
    }

    /**
     * Provides a custom implementation for starting an electric car's engine,
     * emphasizing its silent operation. This overrides the default implementation
     * from the Vehicle interface.
     * 
     * Sample usage:
     * ```java
     * ElectricCar car = new ElectricCar("Tesla");
     * String message = car.startEngine();
     * // Returns: "Silently starting electric engine of Tesla"
     * ```
     * 
     * @return A message indicating the electric engine is starting
     */
    @Override
    public String startEngine() {
        return "Silently starting electric engine of " + getBrand();
    }

    /**
     * Provides a custom implementation for the vehicle status, mentioning the
     * car's charged state. This overrides the default implementation from the
     * Vehicle interface.
     * 
     * Sample usage:
     * ```java
     * ElectricCar car = new ElectricCar("Tesla");
     * String status = car.getStatus();
     * // Returns: "Tesla is charged and ready to go!"
     * ```
     * 
     * @return A status message indicating the electric car is charged
     */
    @Override
    public String getStatus() {
        return getBrand() + " is charged and ready to go!";
    }
}
