package com.java.features.java9.stream;

import java.util.List;
import java.util.stream.Stream;

/**
 * Demonstrates the new Stream API features introduced in Java 9
 */
public class StreamAPIEnhancements {

    public static void main(String[] args) {
        // 1. takeWhile() example
        System.out.println("takeWhile() example:");
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
              .takeWhile(n -> n < 5)
              .forEach(System.out::println);

        // 2. dropWhile() example
        System.out.println("\ndropWhile() example:");
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
              .dropWhile(n -> n < 5)
              .forEach(System.out::println);

        // 3. iterate() with predicate
        System.out.println("\niterate() with predicate example:");
        Stream.iterate(1, i -> i <= 10, i -> i + 1)
              .forEach(System.out::println);

        // 4. ofNullable() example
        System.out.println("\nofNullable() example:");
        Stream.ofNullable(null).forEach(System.out::println); // Empty stream
        Stream.ofNullable("Java 9").forEach(System.out::println);
    }
}
