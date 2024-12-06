package com.java.features.java9.optional;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Demonstrates the new Optional class features in Java 9
 */
public class OptionalEnhancements {

    public static void main(String[] args) {
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
    }

    // Example of using Optional in a method
    public static Optional<String> findUserName(int id) {
        // Simulating database lookup
        if (id > 0) {
            return Optional.of("User" + id);
        }
        return Optional.empty();
    }
}
