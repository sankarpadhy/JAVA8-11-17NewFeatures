package com.java.features.java9.stream;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Optional;

/**
 * Demonstrates the Stream API enhancements introduced in Java 9.
 * 
 * What's New in Java 9 Stream API:
 * -----------------------------
 * 1. takeWhile() - Take elements while predicate is true
 * 2. dropWhile() - Drop elements while predicate is true
 * 3. iterate() overload - Iterate with predicate
 * 4. ofNullable() - Create stream with nullable element
 * 
 * Key Benefits:
 * -----------
 * 1. More precise stream processing
 * 2. Better handling of ordered data
 * 3. Improved iteration control
 * 4. Safer null handling
 * 
 * Use Cases:
 * ---------
 * 1. Processing sorted data
 * 2. Handling conditional streams
 * 3. Creating finite streams
 * 4. Working with nullable elements
 */
public class StreamEnhancementsExample {

    /**
     * Demonstrates takeWhile() method.
     * Takes elements from stream while predicate is true,
     * stops at first false element.
     */
    public void demonstrateTakeWhile() {
        System.out.println("=== takeWhile() demonstration ===");
        
        // With sorted data
        List<Integer> sortedNumbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> lessThan5 = sortedNumbers.stream()
            .takeWhile(n -> n < 5)
            .collect(Collectors.toList());
        System.out.println("Numbers less than 5: " + lessThan5);
        
        // With unsorted data
        List<Integer> unsortedNumbers = List.of(1, 4, 2, 3, 5, 6, 7, 8, 9, 10);
        List<Integer> takeUnsorted = unsortedNumbers.stream()
            .takeWhile(n -> n < 5)
            .collect(Collectors.toList());
        System.out.println("Take while < 5 (unsorted): " + takeUnsorted);
    }

    /**
     * Demonstrates dropWhile() method.
     * Drops elements while predicate is true,
     * keeps all elements once predicate becomes false.
     */
    public void demonstrateDropWhile() {
        System.out.println("\n=== dropWhile() demonstration ===");
        
        // With sorted data
        List<Integer> sortedNumbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> dropLessThan5 = sortedNumbers.stream()
            .dropWhile(n -> n < 5)
            .collect(Collectors.toList());
        System.out.println("Numbers after dropping < 5: " + dropLessThan5);
        
        // With string data
        List<String> words = List.of("a", "b", "c", "dog", "elephant", "fox");
        List<String> longWords = words.stream()
            .dropWhile(s -> s.length() <= 1)
            .collect(Collectors.toList());
        System.out.println("Words after dropping single letters: " + longWords);
    }

    /**
     * Demonstrates new iterate() overload.
     * Allows iteration with a predicate to create finite streams.
     */
    public void demonstrateIterate() {
        System.out.println("\n=== iterate() demonstration ===");
        
        // Old way (infinite stream)
        Stream.iterate(1, n -> n + 2)
            .limit(5)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // New way (finite stream with predicate)
        Stream.iterate(1, // seed
                      n -> n <= 10, // predicate
                      n -> n + 2)   // next value
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // Practical example: Generate date range
        Stream.iterate(java.time.LocalDate.now(),
                      date -> date.isBefore(java.time.LocalDate.now().plusDays(5)),
                      date -> date.plusDays(1))
            .forEach(System.out::println);
    }

    /**
     * Demonstrates ofNullable() method.
     * Creates a Stream of 0 or 1 elements safely handling null.
     */
    public void demonstrateOfNullable() {
        System.out.println("\n=== ofNullable() demonstration ===");
        
        // With non-null value
        String nonNull = "Hello";
        Stream<String> streamNonNull = Stream.ofNullable(nonNull);
        System.out.print("Stream of non-null: ");
        streamNonNull.forEach(System.out::println);
        
        // With null value
        String nullValue = null;
        Stream<String> streamNull = Stream.ofNullable(nullValue);
        System.out.print("Stream of null: ");
        streamNull.forEach(System.out::println);
        
        // Practical example: Processing optional values
        processNullableValue("Hello");
        processNullableValue(null);
    }

    /**
     * Helper method to demonstrate ofNullable() usage
     */
    private void processNullableValue(String value) {
        long count = Stream.ofNullable(value)
            .map(String::toUpperCase)
            .count();
        System.out.println("Processed elements: " + count);
    }

    /**
     * Demonstrates practical combinations of new stream features
     */
    public void demonstratePracticalUseCases() {
        System.out.println("\n=== Practical Use Cases ===");
        
        // Example 1: Process log entries until error
        List<LogEntry> logs = List.of(
            new LogEntry("INFO", "Starting application"),
            new LogEntry("INFO", "Processing data"),
            new LogEntry("ERROR", "Data corruption"),
            new LogEntry("INFO", "Cleanup")
        );
        
        System.out.println("Logs until error:");
        logs.stream()
            .takeWhile(log -> !log.level().equals("ERROR"))
            .forEach(System.out::println);
        
        // Example 2: Generate fibonacci sequence
        System.out.println("\nFibonacci sequence up to 100:");
        Stream.iterate(
            new int[]{0, 1},
            fib -> fib[1] <= 100,
            fib -> new int[]{fib[1], fib[0] + fib[1]}
        )
        .map(fib -> fib[0])
        .forEach(n -> System.out.print(n + " "));
    }

    /**
     * Helper class for log entry example
     */
    private static class LogEntry {
        private final String level;
        private final String message;

        public LogEntry(String level, String message) {
            this.level = level;
            this.message = message;
        }

        public String level() {
            return level;
        }

        public String message() {
            return message;
        }

        @Override
        public String toString() {
            return level + ": " + message;
        }
    }

    public static void main(String[] args) {
        StreamEnhancementsExample example = new StreamEnhancementsExample();
        
        example.demonstrateTakeWhile();
        example.demonstrateDropWhile();
        example.demonstrateIterate();
        example.demonstrateOfNullable();
        example.demonstratePracticalUseCases();
    }
}
