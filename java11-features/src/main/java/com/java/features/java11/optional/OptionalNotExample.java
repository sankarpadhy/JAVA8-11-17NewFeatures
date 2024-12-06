package com.java.features.java11.optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Demonstrates the Optional.isEmpty() method introduced in Java 11.
 * This method complements isPresent() and provides more readable code
 * when checking for empty optionals.
 */
public class OptionalNotExample {

    /**
     * Demonstrates basic usage of isEmpty() method.
     * 
     * Example:
     * ```java
     * demonstrateIsEmpty();
     * // Output:
     * // Empty optional is empty: true
     * // Present optional is empty: false
     * // Filtered optional is empty: true
     * ```
     */
    public void demonstrateIsEmpty() {
        // Empty optional
        Optional<String> empty = Optional.empty();
        System.out.println("Empty optional is empty: " + empty.isEmpty());

        // Present optional
        Optional<String> present = Optional.of("Hello");
        System.out.println("Present optional is empty: " + present.isEmpty());

        // Filtered to empty
        Optional<String> filtered = Optional.of("").filter(s -> s.length() > 0);
        System.out.println("Filtered optional is empty: " + filtered.isEmpty());
    }

    /**
     * Demonstrates using isEmpty() in stream operations.
     * 
     * Example:
     * ```java
     * List<Optional<String>> optionals = Arrays.asList(
     *     Optional.empty(),
     *     Optional.of("Hello"),
     *     Optional.empty(),
     *     Optional.of("World")
     * );
     * demonstrateStreamFiltering(optionals);
     * // Output:
     * // Empty optionals count: 2
     * // Non-empty values: [Hello, World]
     * ```
     */
    public void demonstrateStreamFiltering(List<Optional<String>> optionals) {
        // Count empty optionals
        long emptyCount = optionals.stream()
                .filter(Optional::isEmpty)
                .count();
        System.out.println("Empty optionals count: " + emptyCount);

        // Get non-empty values
        List<String> values = optionals.stream()
                .filter(opt -> !opt.isEmpty())
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println("Non-empty values: " + values);
    }

    /**
     * Demonstrates combining isEmpty() with other Optional methods.
     * 
     * Example:
     * ```java
     * demonstrateCombinedUsage();
     * // Output:
     * // Default for empty: default value
     * // Mapped non-empty: HELLO
     * // Empty after filter: true
     * ```
     */
    public void demonstrateCombinedUsage() {
        // Using isEmpty() with orElse
        Optional<String> empty = Optional.empty();
        String defaultValue = empty.isEmpty() ? "default value" : empty.get();
        System.out.println("Default for empty: " + defaultValue);

        // Using isEmpty() with map
        Optional<String> hello = Optional.of("hello");
        String mapped = hello.isEmpty() ? "" : hello.map(String::toUpperCase).get();
        System.out.println("Mapped non-empty: " + mapped);

        // Using isEmpty() with filter
        Optional<String> filtered = hello.filter(s -> s.length() > 10);
        System.out.println("Empty after filter: " + filtered.isEmpty());
    }

    /**
     * Demonstrates practical use cases of isEmpty().
     * 
     * Example:
     * ```java
     * demonstratePracticalUseCases();
     * // Output:
     * // Processing result: Value processed
     * // Validation result: Invalid input
     * // Cache status: Cache miss
     * ```
     */
    public void demonstratePracticalUseCases() {
        // Processing with validation
        Optional<String> input = Optional.of("value");
        if (!input.isEmpty() && isValid(input.get())) {
            System.out.println("Processing result: Value processed");
        } else {
            System.out.println("Processing result: Invalid input");
        }

        // Caching example
        Optional<String> cachedValue = getCachedValue("key");
        System.out.println("Cache status: " + 
            (cachedValue.isEmpty() ? "Cache miss" : "Cache hit"));
    }

    // Helper methods
    private boolean isValid(String value) {
        return value != null && !value.isEmpty();
    }

    private Optional<String> getCachedValue(String key) {
        // Simulated cache lookup
        return Optional.empty();
    }

    public static void main(String[] args) {
        OptionalNotExample demo = new OptionalNotExample();

        System.out.println("=== Basic isEmpty() Demo ===");
        demo.demonstrateIsEmpty();

        System.out.println("\n=== Stream Filtering Demo ===");
        List<Optional<String>> optionals = Arrays.asList(
            Optional.empty(),
            Optional.of("Hello"),
            Optional.empty(),
            Optional.of("World")
        );
        demo.demonstrateStreamFiltering(optionals);

        System.out.println("\n=== Combined Usage Demo ===");
        demo.demonstrateCombinedUsage();

        System.out.println("\n=== Practical Use Cases Demo ===");
        demo.demonstratePracticalUseCases();
    }
}
