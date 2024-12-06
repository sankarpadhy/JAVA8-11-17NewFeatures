package com.java.features.java9.collections;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Demonstrates the Collection Factory Methods introduced in Java 9.
 * 
 * What's New in Java 9:
 * --------------------
 * Java 9 introduced convenient factory methods for creating immutable collections:
 * - List.of()
 * - Set.of()
 * - Map.of() and Map.ofEntries()
 * 
 * Key Benefits:
 * ------------
 * 1. Concise Syntax: Create collections in a single line
 * 2. Immutability: Collections are immutable by default
 * 3. Null-Safety: Null elements are not allowed
 * 4. Memory Efficiency: Optimized internal representation
 * 
 * Before Java 9:
 * -------------
 * {@code
 *     List<String> list = new ArrayList<>();
 *     list.add("Java");
 *     list.add("Python");
 *     list = Collections.unmodifiableList(list);
 * }
 * 
 * After Java 9:
 * ------------
 * {@code
 *     List<String> list = List.of("Java", "Python");
 * }
 * 
 * Important Notes:
 * --------------
 * 1. All factory methods create immutable collections
 * 2. Null elements are not allowed
 * 3. For Map, there are overloads up to 10 key-value pairs
 * 4. For larger maps, use Map.ofEntries(Entry<K,V>...)
 */
public class CollectionFactoryMethods {

    public static void main(String[] args) {
        demonstrateListFactory();
        demonstrateSetFactory();
        demonstrateMapFactory();
        demonstrateImmutability();
    }

    /**
     * Demonstrates the List.of() factory method.
     * Creates immutable Lists with up to 10 elements directly.
     */
    private static void demonstrateListFactory() {
        // Empty immutable list
        List<String> emptyList = List.of();
        System.out.println("Empty List: " + emptyList);

        // Immutable list with elements
        List<String> languages = List.of("Java", "Python", "JavaScript");
        System.out.println("Languages List: " + languages);
    }

    /**
     * Demonstrates the Set.of() factory method.
     * Creates immutable Sets with distinct elements.
     * Trying to add duplicates will throw IllegalArgumentException.
     */
    private static void demonstrateSetFactory() {
        // Empty immutable set
        Set<Integer> emptySet = Set.of();
        System.out.println("Empty Set: " + emptySet);

        // Immutable set with elements
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5);
        System.out.println("Numbers Set: " + numbers);

        try {
            // This will throw IllegalArgumentException
            Set<Integer> duplicates = Set.of(1, 1, 2, 3);
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot create Set with duplicates: " + e.getMessage());
        }
    }

    /**
     * Demonstrates the Map.of() factory method.
     * Creates immutable Maps with up to 10 key-value pairs.
     * For larger maps, use Map.ofEntries().
     */
    private static void demonstrateMapFactory() {
        // Empty immutable map
        Map<String, Integer> emptyMap = Map.of();
        System.out.println("Empty Map: " + emptyMap);

        // Immutable map with key-value pairs
        Map<String, Integer> versions = Map.of(
            "Java", 9,
            "Python", 3,
            "JavaScript", 6
        );
        System.out.println("Version Map: " + versions);
    }

    /**
     * Demonstrates the immutability of collections created by factory methods.
     * Any attempt to modify these collections will throw UnsupportedOperationException.
     */
    private static void demonstrateImmutability() {
        List<String> list = List.of("Java", "Python");
        try {
            list.add("JavaScript"); // Will throw UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify immutable List: " + e.getMessage());
        }

        Set<Integer> set = Set.of(1, 2, 3);
        try {
            set.remove(1); // Will throw UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify immutable Set: " + e.getMessage());
        }

        Map<String, Integer> map = Map.of("Java", 9);
        try {
            map.put("Python", 3); // Will throw UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify immutable Map: " + e.getMessage());
        }
    }
}
