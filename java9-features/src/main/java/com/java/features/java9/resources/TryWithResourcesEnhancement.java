package com.java.features.java9.resources;

import java.io.BufferedReader;
import java.io.StringReader;

/**
 * Demonstrates the enhanced Try-With-Resources feature introduced in Java 9.
 * This class showcases how Java 9 improves the try-with-resources statement
 * by allowing effectively final variables to be used directly in the try block.
 * 
 * Key features demonstrated:
 * - Using effectively final variables in try-with-resources
 * - Improved readability with less code duplication
 * - Proper resource management and automatic closing
 * - Custom AutoCloseable resources
 * 
 * Example usage:
 * ```java
 * BufferedReader reader = new BufferedReader(new StringReader("Hello"));
 * // Java 9 style - can use reader directly
 * try (reader) {
 *     String line = reader.readLine();
 *     System.out.println(line);
 * }
 * ```
 */
public class TryWithResourcesEnhancement {

    public static void main(String[] args) {
        // Create a resource
        BufferedReader reader = new BufferedReader(
            new StringReader("Hello Java 9!")
        );

        // Pre-Java 9 style
        try (BufferedReader r = reader) {
            System.out.println(r.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create another resource
        BufferedReader reader2 = new BufferedReader(
            new StringReader("Hello Enhanced Try-With-Resources!")
        );

        // Java 9 style - can use the effective final variable directly
        try (reader2) {
            System.out.println(reader2.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Example with multiple resources
    public static void multipleResources() {
        CustomResource resource1 = new CustomResource("Resource 1");
        CustomResource resource2 = new CustomResource("Resource 2");

        // Java 9 style with multiple resources
        try (resource1; resource2) {
            resource1.doWork();
            resource2.doWork();
        }
    }
}

// Custom resource class for demonstration
class CustomResource implements AutoCloseable {
    private final String name;

    public CustomResource(String name) {
        this.name = name;
        System.out.println(name + " created");
    }

    public void doWork() {
        System.out.println(name + " doing work");
    }

    @Override
    public void close() {
        System.out.println(name + " closed");
    }
}
