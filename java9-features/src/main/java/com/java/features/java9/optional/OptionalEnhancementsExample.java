package com.java.features.java9.optional;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Demonstrates the Optional class enhancements introduced in Java 9.
 * 
 * What's New in Java 9 Optional:
 * ---------------------------
 * 1. stream() method
 * 2. ifPresentOrElse()
 * 3. or()
 * 
 * Evolution of Optional:
 * -------------------
 * Java 8:
 * - Basic Optional with map(), filter(), ifPresent()
 * - Limited integration with Stream API
 * 
 * Java 9 Additions:
 * - Better Stream integration
 * - More fluent error handling
 * - Improved empty case handling
 * 
 * Key Benefits:
 * -----------
 * 1. More expressive code
 * 2. Better null handling
 * 3. Improved integration with streams
 * 4. Cleaner fallback handling
 */
public class OptionalEnhancementsExample {

    /**
     * Demonstrates the new stream() method.
     * Converts Optional to Stream (0 or 1 element)
     */
    public void demonstrateStream() {
        System.out.println("=== stream() demonstration ===");
        
        // Creating some optional values
        Optional<String> optional1 = Optional.of("Value 1");
        Optional<String> optional2 = Optional.empty();
        
        // Using stream() to process optionals
        Stream<String> stream1 = optional1.stream();
        Stream<String> stream2 = optional2.stream();
        
        System.out.println("Stream from non-empty optional:");
        stream1.forEach(System.out::println);  // Prints: Value 1
        
        System.out.println("Stream from empty optional:");
        stream2.forEach(System.out::println);  // Prints nothing
        
        // Practical use case: filtering non-empty values
        Stream.of(
            Optional.of("A"),
            Optional.empty(),
            Optional.of("B")
        )
        .flatMap(Optional::stream)  // New in Java 9
        .forEach(System.out::println);  // Prints: A, B
    }

    /**
     * Demonstrates the new ifPresentOrElse() method.
     * Provides action for both present and empty cases.
     */
    public void demonstrateIfPresentOrElse() {
        System.out.println("\n=== ifPresentOrElse() demonstration ===");
        
        // With present value
        Optional<String> optional1 = Optional.of("Hello");
        optional1.ifPresentOrElse(
            value -> System.out.println("Value is present: " + value),
            () -> System.out.println("Value is not present")
        );
        
        // With empty optional
        Optional<String> optional2 = Optional.empty();
        optional2.ifPresentOrElse(
            value -> System.out.println("Value is present: " + value),
            () -> System.out.println("Value is not present")
        );
    }

    /**
     * Demonstrates the new or() method.
     * Provides alternative Optional if the first one is empty.
     */
    public void demonstrateOr() {
        System.out.println("\n=== or() demonstration ===");
        
        // First optional is present
        Optional<String> optional1 = Optional.of("First Choice");
        String result1 = optional1
            .or(() -> Optional.of("Second Choice"))
            .get();
        System.out.println("Result 1: " + result1);  // Prints: First Choice
        
        // First optional is empty
        Optional<String> optional2 = Optional.empty();
        String result2 = optional2
            .or(() -> Optional.of("Second Choice"))
            .get();
        System.out.println("Result 2: " + result2);  // Prints: Second Choice
    }

    /**
     * Demonstrates practical use cases combining multiple Optional enhancements.
     */
    public void demonstratePracticalUseCases() {
        System.out.println("\n=== Practical Use Cases ===");
        
        // Example: User preferences with fallback
        UserPreference userPref = new UserPreference();
        
        // Case 1: Primary preference exists
        String theme = userPref.getPrimaryTheme()
            .or(() -> userPref.getSecondaryTheme())
            .or(() -> userPref.getDefaultTheme())
            .get();
        System.out.println("Selected theme: " + theme);
        
        // Case 2: Processing multiple optional values
        Stream.of(
            userPref.getPrimaryTheme(),
            userPref.getSecondaryTheme(),
            userPref.getDefaultTheme()
        )
        .flatMap(Optional::stream)
        .findFirst()
        .ifPresentOrElse(
            value -> System.out.println("Found theme: " + value),
            () -> System.out.println("No theme found")
        );
    }

    /**
     * Helper class for demonstrating practical use cases
     */
    private class UserPreference {
        public Optional<String> getPrimaryTheme() {
            return Optional.empty();  // Simulating no primary theme
        }
        
        public Optional<String> getSecondaryTheme() {
            return Optional.empty();  // Simulating no secondary theme
        }
        
        public Optional<String> getDefaultTheme() {
            return Optional.of("Default Dark");  // Fallback theme
        }
    }

    public static void main(String[] args) {
        OptionalEnhancementsExample example = new OptionalEnhancementsExample();
        
        // Demonstrate all new features
        example.demonstrateStream();
        example.demonstrateIfPresentOrElse();
        example.demonstrateOr();
        example.demonstratePracticalUseCases();
    }
}
