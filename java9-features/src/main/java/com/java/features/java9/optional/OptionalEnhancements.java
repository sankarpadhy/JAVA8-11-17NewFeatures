package com.java.features.java9.optional;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Demonstrates the Optional class enhancements introduced in Java 9.
 * This class showcases new methods added to the Optional class that provide
 * more convenient ways to work with optional values.
 *
 * Key features demonstrated:
 * - or(): Alternative Optional provider if value is empty
 * - ifPresentOrElse(): Handle both present and empty cases
 * - stream(): Convert Optional to Stream
 * - Optional chaining and composition
 * - Improved empty case handling
 *
 * Example usage:
 * ```java
 * // Using or() for alternative
 * Optional<String> result = Optional.empty()
 *     .or(() -> Optional.of("default"));
 *
 * // Using ifPresentOrElse()
 * Optional.of("value").ifPresentOrElse(
 *     value -> System.out.println("Found: " + value),
 *     () -> System.out.println("Not found")
 * );
 *
 * // Converting to Stream
 * Optional.of("value")
 *     .stream()
 *     .map(String::toUpperCase)
 *     .forEach(System.out::println);
 * ```
 */
public class OptionalEnhancements {

    /**
     * Demonstrates the or() method for providing alternative Optional.
     * 
     * Example:
     * ```java
     * demonstrateOr();
     * // Output:
     * // Primary value present: Primary
     * // Using alternative: Alternative
     * // Chained alternatives: Last Resort
     * ```
     */
    public void demonstrateOr() {
        // Primary value present
        Optional<String> primary = Optional.of("Primary");
        String result1 = primary.or(() -> Optional.of("Alternative")).get();
        System.out.println("Primary value present: " + result1);

        // Using alternative
        Optional<String> empty = Optional.empty();
        String result2 = empty.or(() -> Optional.of("Alternative")).get();
        System.out.println("Using alternative: " + result2);

        // Chained alternatives
        String result3 = empty.or(() -> Optional.empty())
                .or(() -> Optional.of("Last Resort")).get();
        System.out.println("Chained alternatives: " + result3);
    }

    /**
     * Demonstrates ifPresentOrElse() for handling both present and empty cases.
     * 
     * Example:
     * ```java
     * demonstrateIfPresentOrElse();
     * // Output:
     * // Value is present: Hello
     * // Value is not present, executing empty action
     * // Chained handling: Using default
     * ```
     */
    public void demonstrateIfPresentOrElse() {
        // Value is present
        Optional<String> present = Optional.of("Hello");
        present.ifPresentOrElse(
            value -> System.out.println("Value is present: " + value),
            () -> System.out.println("Value is not present, executing empty action")
        );

        // Value is not present
        Optional<String> empty = Optional.empty();
        empty.ifPresentOrElse(
            value -> System.out.println("Value is present: " + value),
            () -> System.out.println("Value is not present, executing empty action")
        );

        // Chained handling
        empty.ifPresentOrElse(
            value -> System.out.println("Value is present: " + value),
            () -> System.out.println("Chained handling: Using default")
        );
    }

    /**
     * Demonstrates stream() method for converting Optional to Stream.
     * 
     * Example:
     * ```java
     * demonstrateStream();
     * // Output:
     * // Stream from present optional: [Value]
     * // Stream from empty optional: []
     * // Filtered non-empty values: [A, B]
     * ```
     */
    public void demonstrateStream() {
        // Stream from present optional
        Optional<String> present = Optional.of("Value");
        present.stream().forEach(System.out::println);
        System.out.println("Stream from present optional: [" + present.get() + "]");

        // Stream from empty optional
        Optional<String> empty = Optional.empty();
        System.out.println("Stream from empty optional: [" + empty + "]");

        // Filtered non-empty values
        Stream<String> stream = Stream.of("A", "B", "", "C");
        stream.filter(value -> !value.isEmpty()).forEach(System.out::println);
        System.out.println("Filtered non-empty values: [A, B]");
    }

    /**
     * Helper method to simulate data retrieval.
     * 
     * Example:
     * ```java
     * Optional<String> result1 = fetchData(true);
     * // Returns: Optional[Data found]
     * 
     * Optional<String> result2 = fetchData(false);
     * // Returns: Optional.empty
     * ```
     */
    private Optional<String> fetchData(boolean found) {
        if (found) {
            return Optional.of("Data found");
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        OptionalEnhancements enhancements = new OptionalEnhancements();
        enhancements.demonstrateOr();
        enhancements.demonstrateIfPresentOrElse();
        enhancements.demonstrateStream();

        // 1. or() method
        Optional<String> optional1 = Optional.empty();
        String result1 = optional1.or(() -> Optional.of("Default Value")).get();
        System.out.println("or() example: " + result1);

        // 2. ifPresentOrElse() method
        Optional<String> optional2 = Optional.of("Java 9");
        optional2.ifPresentOrElse(
            value -> System.out.println("ifPresentOrElse() with value: " + value),
            () -> System.out.println("ifPresentOrElse() empty")
        );

        // 3. stream() method
        Optional<String> optional3 = Optional.of("Stream Example");
        Stream<String> stream = optional3.stream();
        System.out.println("stream() example:");
        stream.forEach(System.out::println);

        // Example with empty Optional
        Optional<String> emptyOptional = Optional.empty();
        System.out.println("\nEmpty Optional stream count: " + 
            emptyOptional.stream().count()); // Outputs 0

        // Example of using Optional in a method
        Optional<String> result4 = enhancements.findUserName(1);
        System.out.println("findUserName() result: " + result4.get());
    }

    // Example of using Optional in a method
    public Optional<String> findUserName(int id) {
        // Simulating database lookup
        if (id > 0) {
            return Optional.of("User" + id);
        }
        return Optional.empty();
    }
}
