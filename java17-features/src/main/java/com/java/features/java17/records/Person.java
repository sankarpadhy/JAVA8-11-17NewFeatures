package com.java.features.java17.records;

import java.time.LocalDate;
import java.time.Period;

/**
 * Demonstrates a more complex record with validation, derived attributes, and custom methods.
 * Shows how records can be used for domain objects while maintaining immutability.
 */
public record Person(
    String firstName,
    String lastName,
    LocalDate dateOfBirth,
    String email
) {
    // Compact constructor for validation
    public Person {
        // Validate firstName
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        
        // Validate lastName
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        
        // Validate dateOfBirth
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth cannot be null");
        }
        if (dateOfBirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }
        
        // Validate email
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    // Derived attribute - calculated from dateOfBirth
    public int age() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    // Custom method to create display name
    public String fullName() {
        return firstName + " " + lastName;
    }

    // Static factory method
    public static Person of(String firstName, String lastName, LocalDate dateOfBirth, String email) {
        return new Person(firstName, lastName, dateOfBirth, email);
    }

    // Override toString to provide a custom string representation
    @Override
    public String toString() {
        return String.format("%s (%d years old) - %s", fullName(), age(), email);
    }
}
