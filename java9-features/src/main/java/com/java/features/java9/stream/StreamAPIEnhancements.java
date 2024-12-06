package com.java.features.java9.stream;

import java.util.List;
import java.util.stream.Stream;

/**
 * Demonstrates the Stream API enhancements introduced in Java 9.
 * This class showcases new methods added to the Stream interface that provide
 * more flexibility and control over stream processing.
 *
 * Key features demonstrated:
 * - takeWhile(): Takes elements while predicate is true
 * - dropWhile(): Drops elements while predicate is true
 * - iterate() with predicate: Finite streams with custom increment
 * - ofNullable(): Create stream with 0 or 1 element
 * - Optional.stream(): Convert Optional to Stream
 *
 * Example usage:
 * ```java
 * // takeWhile example
 * Stream.of(1, 2, 3, 4, 5)
 *       .takeWhile(n -> n < 4)
 *       .forEach(System.out::println); // Prints: 1, 2, 3
 *
 * // dropWhile example
 * Stream.of(1, 2, 3, 4, 5)
 *       .dropWhile(n -> n < 4)
 *       .forEach(System.out::println); // Prints: 4, 5
 * ```
 */
public class StreamAPIEnhancements {

    /**
     * Demonstrates the Stream API enhancements introduced in Java 9.
     */
    public static void main(String[] args) {
        StreamAPIEnhancements streamAPIEnhancements = new StreamAPIEnhancements();

        // 1. takeWhile() example
        System.out.println("takeWhile() example:");
        streamAPIEnhancements.demonstrateTakeWhile(List.of(1, 2, 3, 4, 5, 6, 7, 8));

        // 2. dropWhile() example
        System.out.println("\ndropWhile() example:");
        streamAPIEnhancements.demonstrateDropWhile(List.of(1, 2, 3, 4, 5, 6, 7, 8));

        // 3. iterate() with predicate
        System.out.println("\niterate() with predicate example:");
        streamAPIEnhancements.demonstrateIterate();

        // 4. ofNullable() example
        System.out.println("\nofNullable() example:");
        streamAPIEnhancements.demonstrateOfNullable(null);
        streamAPIEnhancements.demonstrateOfNullable("Java 9");
    }

    /**
     * Demonstrates takeWhile() operation.
     * Takes elements from stream while predicate is true.
     * 
     * Example:
     * ```java
     * List<Integer> numbers = List.of(2, 4, 6, 7, 8, 10);
     * demonstrateTakeWhile(numbers);
     * // Output:
     * // Numbers while even: [2, 4, 6]
     * ```
     */
    public void demonstrateTakeWhile(List<Integer> numbers) {
        System.out.println("Numbers while even: " + numbers.stream()
                .takeWhile(n -> n % 2 == 0)
                .toList());
    }

    /**
     * Demonstrates dropWhile() operation.
     * Drops elements while predicate is true.
     * 
     * Example:
     * ```java
     * List<Integer> numbers = List.of(2, 4, 6, 7, 8, 10);
     * demonstrateDropWhile(numbers);
     * // Output:
     * // Numbers after dropping evens: [7, 8, 10]
     * ```
     */
    public void demonstrateDropWhile(List<Integer> numbers) {
        System.out.println("Numbers after dropping evens: " + numbers.stream()
                .dropWhile(n -> n % 2 == 0)
                .toList());
    }

    /**
     * Demonstrates iterate() with predicate.
     * Creates finite streams with custom increment.
     * 
     * Example:
     * ```java
     * demonstrateIterate();
     * // Output:
     * // Counting to 5: [1, 2, 3, 4, 5]
     * // Even numbers to 10: [2, 4, 6, 8, 10]
     * ```
     */
    public void demonstrateIterate() {
        System.out.println("Counting to 5: " + Stream.iterate(1, i -> i <= 5, i -> i + 1)
                .toList());
        System.out.println("Even numbers to 10: " + Stream.iterate(2, i -> i <= 10, i -> i + 2)
                .toList());
    }

    /**
     * Demonstrates ofNullable() method.
     * Creates a Stream of 0 or 1 elements.
     * 
     * Example:
     * ```java
     * demonstrateOfNullable("Hello");
     * // Output: Stream contains: Hello
     * 
     * demonstrateOfNullable(null);
     * // Output: Stream is empty
     * ```
     */
    public void demonstrateOfNullable(String value) {
        Stream<String> stream = Stream.ofNullable(value);
        if (stream.findAny().isPresent()) {
            System.out.println("Stream contains: " + stream.findAny().get());
        } else {
            System.out.println("Stream is empty");
        }
    }
}
