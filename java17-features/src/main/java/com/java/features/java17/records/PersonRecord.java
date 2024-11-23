package com.java.features.java17.records;

/**
 * Demonstrates Record feature introduced in Java 16 (preview) and finalized in Java 17
 */
public record PersonRecord(String name, int age, String email) {
    
    // Compact constructor
    public PersonRecord {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    // Additional static field (allowed in records)
    private static final String UNKNOWN = "Unknown";

    // Static factory method
    public static PersonRecord unknown() {
        return new PersonRecord(UNKNOWN, 0, "unknown@example.com");
    }

    // Instance method
    public boolean isAdult() {
        return age >= 18;
    }

    // Custom accessor with different name
    public String displayName() {
        return name.toUpperCase();
    }
}
