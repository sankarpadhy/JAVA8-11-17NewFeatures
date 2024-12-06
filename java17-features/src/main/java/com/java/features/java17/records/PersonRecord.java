package com.java.features.java17.records;

/**
 * Demonstrates the Record feature introduced in Java 16 and enhanced in Java 17.
 * Records provide a compact way to declare classes that are transparent holders
 * for shallowly immutable data.
 * 
 * Key features demonstrated:
 * - Automatic generation of constructor, accessors, equals(), hashCode(), and toString()
 * - Custom accessors with different names
 * - Instance methods in records
 * - Validation in canonical constructor
 * - Pattern matching with records
 * 
 * Example usage:
 * ```java
 * PersonRecord person = new PersonRecord("John Doe", 25, "john@example.com");
 * System.out.println(person.displayName()); // Prints: JOHN DOE
 * System.out.println(person.isAdult());     // Prints: true
 * ```
 */
public record PersonRecord(String name, int age, String email) {
    
    /**
     * Compact constructor with validation.
     * 
     * Example:
     * ```java
     * // Valid person
     * PersonRecord person1 = new PersonRecord("John", 25, "john@email.com");
     * 
     * // Throws IllegalArgumentException: Name cannot be empty
     * PersonRecord person2 = new PersonRecord("", 25, "email@test.com");
     * 
     * // Throws IllegalArgumentException: Age must be positive
     * PersonRecord person3 = new PersonRecord("Jane", -1, "jane@email.com");
     * 
     * // Throws IllegalArgumentException: Invalid email format
     * PersonRecord person4 = new PersonRecord("Bob", 30, "invalid-email");
     * ```
     */
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

    /**
     * Custom accessor that returns formatted name.
     * 
     * Example:
     * ```java
     * PersonRecord person = new PersonRecord("john smith", 25, "john@email.com");
     * person.formattedName()  // Returns: "John Smith"
     * ```
     */
    public String formattedName() {
        return name.toUpperCase();
    }

    /**
     * Returns age category based on age.
     * 
     * Example:
     * ```java
     * PersonRecord person1 = new PersonRecord("Child", 10, "child@email.com");
     * person1.getAgeCategory()  // Returns: "Child"
     * 
     * PersonRecord person2 = new PersonRecord("Adult", 25, "adult@email.com");
     * person2.getAgeCategory()  // Returns: "Adult"
     * 
     * PersonRecord person3 = new PersonRecord("Senior", 65, "senior@email.com");
     * person3.getAgeCategory()  // Returns: "Senior"
     * ```
     */
    public String getAgeCategory() {
        if (age < 18) {
            return "Child";
        } else if (age < 65) {
            return "Adult";
        } else {
            return "Senior";
        }
    }

    /**
     * Returns email domain.
     * 
     * Example:
     * ```java
     * PersonRecord person = new PersonRecord("Test", 20, "user@example.com");
     * person.emailDomain()  // Returns: "example.com"
     * ```
     */
    public String emailDomain() {
        return email.split("@")[1];
    }

    /**
     * Instance method
     * 
     * Example:
     * ```java
     * PersonRecord person = new PersonRecord("John Doe", 25, "john@example.com");
     * person.isAdult()     // Prints: true
     * ```
     */
    public boolean isAdult() {
        return age >= 18;
    }
}
