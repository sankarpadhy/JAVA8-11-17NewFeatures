package com.java.features.java9.trywithresources;

import java.io.*;

/**
 * Demonstrates the Try-With-Resources enhancements introduced in Java 9.
 * 
 * What's New in Java 9:
 * -------------------
 * 1. Ability to use effectively final variables in try-with-resources
 * 2. More concise syntax for resource management
 * 3. Better handling of AutoCloseable resources
 * 
 * Evolution:
 * ---------
 * Java 7: Initial try-with-resources introduction
 * Java 9: Enhanced to allow effectively final variables
 * 
 * Benefits:
 * --------
 * 1. More concise code
 * 2. Better resource management
 * 3. Improved readability
 * 4. Automatic resource closing
 */
public class TryWithResourcesExample {

    /**
     * Demonstrates the old way (Java 7) of using try-with-resources
     */
    public void demonstrateJava7Style() {
        System.out.println("=== Java 7 Style ===");
        
        try (BufferedReader reader = new BufferedReader(
                new FileReader(createTempFile("Hello Java 7!")))) {
            
            System.out.println("Reading file (Java 7 style):");
            System.out.println(reader.readLine());
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Demonstrates the new way (Java 9) of using try-with-resources
     * with effectively final variables
     */
    public void demonstrateJava9Style() {
        System.out.println("\n=== Java 9 Style ===");
        
        // Create resource outside try block
        BufferedReader reader;
        try {
            reader = new BufferedReader(
                new FileReader(createTempFile("Hello Java 9!")));
        } catch (IOException e) {
            System.out.println("Error creating reader: " + e.getMessage());
            return;
        }
        
        // Use existing resource in try-with-resources
        try (reader) {  // More concise syntax!
            System.out.println("Reading file (Java 9 style):");
            System.out.println(reader.readLine());
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Demonstrates using multiple resources with the new syntax
     */
    public void demonstrateMultipleResources() {
        System.out.println("\n=== Multiple Resources ===");
        
        // Create resources
        BufferedReader reader1;
        BufferedReader reader2;
        try {
            reader1 = new BufferedReader(
                new FileReader(createTempFile("First file")));
            reader2 = new BufferedReader(
                new FileReader(createTempFile("Second file")));
        } catch (IOException e) {
            System.out.println("Error creating readers: " + e.getMessage());
            return;
        }
        
        // Use both resources in try-with-resources
        try (reader1; reader2) {  // Multiple resources!
            System.out.println("Reading multiple files:");
            System.out.println("File 1: " + reader1.readLine());
            System.out.println("File 2: " + reader2.readLine());
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Demonstrates practical usage with custom AutoCloseable resources
     */
    public void demonstrateCustomResources() {
        System.out.println("\n=== Custom Resources ===");
        
        // Create custom resources
        DatabaseConnection connection = new DatabaseConnection();
        TransactionManager transaction = new TransactionManager();
        
        // Use custom resources
        try (connection; transaction) {
            System.out.println("Performing database operation:");
            connection.executeQuery("SELECT * FROM users");
            transaction.commit();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Helper method to create a temporary file with content
     */
    private File createTempFile(String content) throws IOException {
        File tempFile = File.createTempFile("java9demo", ".txt");
        tempFile.deleteOnExit();
        
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(content);
        }
        
        return tempFile;
    }

    /**
     * Custom AutoCloseable resource example
     */
    private class DatabaseConnection implements AutoCloseable {
        public DatabaseConnection() {
            System.out.println("Database connection opened");
        }

        public void executeQuery(String query) {
            System.out.println("Executing query: " + query);
        }

        @Override
        public void close() {
            System.out.println("Database connection closed");
        }
    }

    /**
     * Another custom AutoCloseable resource example
     */
    private class TransactionManager implements AutoCloseable {
        public TransactionManager() {
            System.out.println("Transaction started");
        }

        public void commit() {
            System.out.println("Transaction committed");
        }

        @Override
        public void close() {
            System.out.println("Transaction closed");
        }
    }

    public static void main(String[] args) {
        TryWithResourcesExample example = new TryWithResourcesExample();
        
        example.demonstrateJava7Style();
        example.demonstrateJava9Style();
        example.demonstrateMultipleResources();
        example.demonstrateCustomResources();
    }
}
