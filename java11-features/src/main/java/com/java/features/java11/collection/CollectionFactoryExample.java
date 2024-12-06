package com.java.features.java11.collection;

import java.util.*;

/**
 * Demonstrates Collection Factory Methods enhancements in Java 11.
 * Shows how to create immutable collections using convenient factory methods.
 */
public class CollectionFactoryExample {

    /**
     * Demonstrates creating immutable lists using List.of().
     * 
     * Example:
     * ```java
     * demonstrateListFactory();
     * // Output:
     * // Empty list: []
     * // Single element: [One]
     * // Multiple elements: [One, Two, Three]
     * // Attempting to modify: UnsupportedOperationException
     * ```
     */
    public void demonstrateListFactory() {
        // Empty list
        List<String> empty = List.of();
        System.out.println("Empty list: " + empty);

        // Single element
        List<String> single = List.of("One");
        System.out.println("Single element: " + single);

        // Multiple elements
        List<String> multiple = List.of("One", "Two", "Three");
        System.out.println("Multiple elements: " + multiple);

        try {
            multiple.add("Four"); // Will throw UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Attempting to modify: UnsupportedOperationException");
        }
    }

    /**
     * Demonstrates creating immutable sets using Set.of().
     * 
     * Example:
     * ```java
     * demonstrateSetFactory();
     * // Output:
     * // Empty set: []
     * // Single element: [1]
     * // Multiple elements: [1, 2, 3]
     * // Duplicate element: IllegalArgumentException
     * ```
     */
    public void demonstrateSetFactory() {
        // Empty set
        Set<Integer> empty = Set.of();
        System.out.println("Empty set: " + empty);

        // Single element
        Set<Integer> single = Set.of(1);
        System.out.println("Single element: " + single);

        // Multiple elements
        Set<Integer> multiple = Set.of(1, 2, 3);
        System.out.println("Multiple elements: " + multiple);

        try {
            Set<Integer> duplicate = Set.of(1, 1); // Will throw IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Duplicate element: IllegalArgumentException");
        }
    }

    /**
     * Demonstrates creating immutable maps using Map.of().
     * 
     * Example:
     * ```java
     * demonstrateMapFactory();
     * // Output:
     * // Empty map: {}
     * // Single entry: {key1=value1}
     * // Multiple entries: {key1=value1, key2=value2}
     * // Map entries: {A=1, B=2, C=3}
     * ```
     */
    public void demonstrateMapFactory() {
        // Empty map
        Map<String, String> empty = Map.of();
        System.out.println("Empty map: " + empty);

        // Single entry
        Map<String, String> single = Map.of("key1", "value1");
        System.out.println("Single entry: " + single);

        // Multiple entries
        Map<String, String> multiple = Map.of(
            "key1", "value1",
            "key2", "value2"
        );
        System.out.println("Multiple entries: " + multiple);

        // Using Map.ofEntries
        Map<String, Integer> entries = Map.ofEntries(
            Map.entry("A", 1),
            Map.entry("B", 2),
            Map.entry("C", 3)
        );
        System.out.println("Map entries: " + entries);
    }

    /**
     * Demonstrates performance considerations and best practices.
     * 
     * Example:
     * ```java
     * demonstrateBestPractices();
     * // Output:
     * // Original list: [1, 2, 3]
     * // Copied list: [1, 2, 3]
     * // Filtered elements: [2, 3]
     * ```
     */
    public void demonstrateBestPractices() {
        // Creating from existing collection
        List<Integer> original = Arrays.asList(1, 2, 3);
        List<Integer> copy = List.copyOf(original);
        System.out.println("Original list: " + original);
        System.out.println("Copied list: " + copy);

        // Using with streams
        List<Integer> filtered = original.stream()
            .filter(n -> n > 1)
            .collect(Collectors.toUnmodifiableList());
        System.out.println("Filtered elements: " + filtered);
    }

    public static void main(String[] args) {
        CollectionFactoryExample demo = new CollectionFactoryExample();

        System.out.println("=== List Factory Demo ===");
        demo.demonstrateListFactory();

        System.out.println("\n=== Set Factory Demo ===");
        demo.demonstrateSetFactory();

        System.out.println("\n=== Map Factory Demo ===");
        demo.demonstrateMapFactory();

        System.out.println("\n=== Best Practices Demo ===");
        demo.demonstrateBestPractices();
    }
}
