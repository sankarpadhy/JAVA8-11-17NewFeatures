package com.java.features.java9.interfaces;

/**
 * Demonstrates Private Interface Methods introduced in Java 9.
 * 
 * What's New in Java 9:
 * -------------------
 * 1. Private instance methods in interfaces
 * 2. Private static methods in interfaces
 * 
 * Interface Method Evolution:
 * ------------------------
 * Java 8:
 * - Default methods (public)
 * - Static methods (public)
 * 
 * Java 9 adds:
 * - Private methods (instance)
 * - Private static methods
 * 
 * Benefits:
 * --------
 * 1. Better code organization
 * 2. Avoid code duplication in default methods
 * 3. Hide implementation details
 * 4. Improve maintainability
 * 
 * Use Cases:
 * ---------
 * 1. Helper methods for default methods
 * 2. Common utility methods
 * 3. Complex implementation details
 */
public interface PrivateInterfaceMethodExample {

    /**
     * Regular abstract method - must be implemented by classes
     */
    void abstractMethod();

    /**
     * Default method that uses private helper methods
     */
    default void processData(String data) {
        // Validate input using private helper
        if (validateInput(data)) {
            // Process using private helper
            String processed = processInternal(data);
            System.out.println("Processed: " + processed);
        } else {
            System.out.println("Invalid input");
        }
    }

    /**
     * Another default method showing code reuse
     */
    default void processDataWithLogging(String data) {
        logMessage("Starting processing");
        processData(data);
        logMessage("Processing complete");
    }

    /**
     * Private helper method - can only be used within the interface
     * New in Java 9
     */
    private boolean validateInput(String data) {
        return data != null && !data.trim().isEmpty();
    }

    /**
     * Private method for internal processing
     * New in Java 9
     */
    private String processInternal(String data) {
        return data.toUpperCase();
    }

    /**
     * Private static utility method
     * New in Java 9
     */
    private static void logMessage(String message) {
        System.out.println("LOG: " + message);
    }

    /**
     * Public static method demonstrating usage of private static helper
     */
    static void printInfo(String info) {
        logMessage("Info request received");
        System.out.println("INFO: " + info);
    }
}

/**
 * Example implementation of the interface
 */
class DataProcessor implements PrivateInterfaceMethodExample {
    @Override
    public void abstractMethod() {
        System.out.println("Implementing abstract method");
    }

    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor();

        System.out.println("=== Testing Abstract Method ===");
        processor.abstractMethod();

        System.out.println("\n=== Testing Default Method with Private Helpers ===");
        processor.processData("test data");
        processor.processData("");  // Invalid input

        System.out.println("\n=== Testing Default Method with Logging ===");
        processor.processDataWithLogging("hello java 9");

        System.out.println("\n=== Testing Static Method ===");
        PrivateInterfaceMethodExample.printInfo("Static method call");
    }
}
