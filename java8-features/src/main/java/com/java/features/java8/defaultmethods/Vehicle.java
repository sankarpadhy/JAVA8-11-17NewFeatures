package com.java.features.java8.defaultmethods;

/**
 * An interface demonstrating default methods feature introduced in Java 8.
 * This interface defines common behavior for vehicles while providing default
 * implementations that can be overridden if needed.
 * 
 * Sample usage:
 * ```java
 * public class Car implements Vehicle {
 *     @Override
 *     public String getBrand() {
 *         return "Toyota";
 *     }
 *     // Other methods inherit default implementations
 * }
 * 
 * Car car = new Car();
 * car.getBrand();      // Returns: "Toyota"
 * car.startEngine();   // Returns: "Starting engine of Toyota"
 * car.getStatus();     // Returns: "Toyota is ready to go!"
 * Vehicle.getVehicleInfo(); // Returns: "This is a vehicle interface with default methods"
 * ```
 */
public interface Vehicle {
    
    /**
     * Returns the brand name of the vehicle. This is an abstract method that must be
     * implemented by all classes that implement the Vehicle interface.
     * 
     * Sample implementation:
     * ```java
     * @Override
     * public String getBrand() {
     *     return "Toyota";
     * }
     * ```
     * 
     * @return The brand name of the vehicle (e.g., "Toyota", "Tesla", "Ford")
     */
    String getBrand();

    /**
     * Returns a message indicating the engine is starting. This is a default method
     * that automatically uses the vehicle's brand name in the message.
     * 
     * Sample usage:
     * ```java
     * Vehicle car = new Car(); // Car implements Vehicle and getBrand() returns "Toyota"
     * car.startEngine(); // Returns: "Starting engine of Toyota"
     * ```
     * 
     * Can be overridden to provide custom behavior:
     * ```java
     * @Override
     * public String startEngine() {
     *     return "Silent start of " + getBrand() + " electric motor";
     * }
     * ```
     * 
     * @return A message indicating the engine is starting, including the brand name
     */
    default String startEngine() {
        return "Starting engine of " + getBrand();
    }

    /**
     * Returns the current status of the vehicle. This default method combines
     * the brand name with a ready status message.
     * 
     * Sample usage:
     * ```java
     * Vehicle car = new Car(); // Car implements Vehicle and getBrand() returns "Toyota"
     * car.getStatus(); // Returns: "Toyota is ready to go!"
     * ```
     * 
     * Can be overridden to provide more specific status information:
     * ```java
     * @Override
     * public String getStatus() {
     *     return getBrand() + " is charged and ready to drive!";
     * }
     * ```
     * 
     * @return A status message including the brand name
     */
    default String getStatus() {
        return getBrand() + " is ready to go!";
    }

    /**
     * Returns general information about the Vehicle interface. This is a static
     * method that can be called directly on the Vehicle interface without
     * needing an instance.
     * 
     * Sample usage:
     * ```java
     * String info = Vehicle.getVehicleInfo();
     * // Returns: "This is a vehicle interface with default methods"
     * ```
     * 
     * @return General information about the Vehicle interface
     */
    static String getVehicleInfo() {
        return "This is a vehicle interface with default methods";
    }
}
