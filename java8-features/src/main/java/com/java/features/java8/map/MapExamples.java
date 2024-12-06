package com.java.features.java8.map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Demonstrates Java 8's Map interface enhancements.
 * This class showcases the new methods added to the Map interface in Java 8:
 * - getOrDefault
 * - putIfAbsent
 * - computeIfAbsent
 * - computeIfPresent
 * - compute
 * - merge
 * - forEach
 * - replaceAll
 */
public class MapExamples {

    /**
     * Demonstrates the getOrDefault method.
     * Returns the value for a key, or a default value if the key is not present.
     * This avoids explicit null checking.
     *
     * @param map The map to demonstrate with
     * @param key The key to look up
     * @return The result of the operation
     */
    public static String demonstrateGetOrDefault(Map<String, String> map, String key) {
        // Before Java 8
        String valueBefore = map.get(key);
        if (valueBefore == null) {
            valueBefore = "default";
        }

        // Java 8 way
        String valueJava8 = map.getOrDefault(key, "default");

        return String.format("Before Java 8: %s, Java 8 way: %s", 
                           valueBefore, valueJava8);
    }

    /**
     * Demonstrates the putIfAbsent method.
     * Puts a value only if the key is not already present.
     * Returns the previous value or null if none was present.
     *
     * @param map The map to demonstrate with
     * @param key The key to insert
     * @param value The value to insert
     * @return The result of the operation
     */
    public static String demonstratePutIfAbsent(Map<String, Integer> map, 
            String key, Integer value) {
        // Before Java 8
        Integer beforeValue = map.get(key);
        if (beforeValue == null) {
            map.put(key, value);
        }

        // Create new map for Java 8 demonstration
        Map<String, Integer> java8Map = new HashMap<>(map);
        Integer java8Value = java8Map.putIfAbsent(key, value);

        return String.format("Before: %s, After: %s", beforeValue, java8Value);
    }

    /**
     * Demonstrates the computeIfAbsent method.
     * Computes a value if the key is not present.
     * The computation is only performed if needed.
     *
     * @param map The map to demonstrate with
     * @param key The key to compute for
     * @return The computed or existing value
     */
    public static Integer demonstrateComputeIfAbsent(Map<String, Integer> map, 
            String key) {
        return map.computeIfAbsent(key, k -> k.length());
    }

    /**
     * Demonstrates the computeIfPresent method.
     * Computes a new value if the key is present.
     * Returns null if the computation returns null.
     *
     * @param map The map to demonstrate with
     * @param key The key to compute for
     * @return The computed value or null
     */
    public static Integer demonstrateComputeIfPresent(Map<String, Integer> map, 
            String key) {
        return map.computeIfPresent(key, (k, v) -> v * 2);
    }

    /**
     * Demonstrates the compute method.
     * Computes a new value for a given key.
     * Can be used to update or insert values.
     *
     * @param map The map to demonstrate with
     * @param key The key to compute for
     * @return The computed value
     */
    public static Integer demonstrateCompute(Map<String, Integer> map, String key) {
        return map.compute(key, (k, v) -> (v == null) ? 1 : v + 1);
    }

    /**
     * Demonstrates the merge method.
     * Combines the current value with a new value if the key exists,
     * or puts the new value if the key doesn't exist.
     *
     * @param map The map to demonstrate with
     * @param key The key to merge for
     * @param value The value to merge with
     * @return The result of the merge
     */
    public static String demonstrateMerge(Map<String, String> map, 
            String key, String value) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }
        return map.merge(key, value, (oldValue, newValue) -> 
                oldValue + ", " + newValue);
    }

    /**
     * Demonstrates the forEach method.
     * Performs an action for each entry in the map.
     * The action can access both key and value.
     *
     * @param map The map to demonstrate with
     * @return A string representation of the map contents
     */
    public static String demonstrateForEach(Map<String, Integer> map) {
        StringBuilder result = new StringBuilder();
        map.forEach((key, value) -> 
                result.append(String.format("%s=%d, ", key, value)));
        return result.toString();
    }

    /**
     * Demonstrates the replaceAll method.
     * Replaces all values with the results of a function.
     * The function can use both key and value.
     *
     * @param map The map to demonstrate with
     * @return A string representation of the modified map
     */
    public static String demonstrateReplaceAll(Map<String, String> map) {
        map.replaceAll((key, value) -> value.toUpperCase());
        return map.toString();
    }

    /**
     * Demonstrates ConcurrentHashMap's new methods.
     * Shows atomic operations and parallel processing features:
     * - search
     * - reduce
     * - forEach with parallelism threshold
     *
     * @param map The concurrent map to demonstrate with
     * @return Results of the operations
     */
    public static String demonstrateConcurrentMapFeatures(
            ConcurrentHashMap<String, Integer> map) {
        StringBuilder result = new StringBuilder();

        // Search for first value > 10
        Integer searchResult = map.search(1, (key, value) -> 
                value > 10 ? value : null);
        result.append("Search result: ").append(searchResult).append("\n");

        // Reduce to find sum of all values
        Integer reduceResult = map.reduce(1,
                (key, value) -> value,
                Integer::sum);
        result.append("Reduce result: ").append(reduceResult).append("\n");

        // Parallel forEach with threshold
        map.forEach(1, (key, value) -> 
                result.append(String.format("%s=%d, ", key, value)));

        return result.toString();
    }

    /**
     * Demonstrates practical use cases of Map enhancements.
     * Shows common patterns and their implementation using Java 8 features.
     */
    public static void demonstratePracticalUseCases() {
        // Counter map
        Map<String, Integer> wordCount = new HashMap<>();
        String[] words = "the quick brown fox jumps over the lazy dog"
                .split(" ");

        // Count word occurrences
        for (String word : words) {
            wordCount.compute(word, (key, value) -> (value == null) ? 1 : value + 1);
        }

        // Value transformation
        Map<String, List<String>> groupedWords = new HashMap<>();
        Arrays.stream(words).forEach(word -> 
                groupedWords.computeIfAbsent(
                    String.valueOf(word.charAt(0)), 
                    k -> new ArrayList<>()
                ).add(word));

        // Merge values
        Map<String, String> preferences = new HashMap<>();
        preferences.merge("theme", "dark", (old, newVal) -> 
                String.join(", ", old, newVal));
    }

    /**
     * Main method to run all demonstrations.
     */
    public static void main(String[] args) {
        // Initialize test maps
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("key1", "value1");
        
        Map<String, Integer> intMap = new HashMap<>();
        intMap.put("one", 1);
        intMap.put("two", 2);

        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("a", 5);
        concurrentMap.put("b", 15);
        concurrentMap.put("c", 10);

        // Run demonstrations
        System.out.println("GetOrDefault: " + 
                demonstrateGetOrDefault(stringMap, "key2"));
        
        System.out.println("PutIfAbsent: " + 
                demonstratePutIfAbsent(intMap, "three", 3));
        
        System.out.println("ComputeIfAbsent: " + 
                demonstrateComputeIfAbsent(intMap, "four"));
        
        System.out.println("ComputeIfPresent: " + 
                demonstrateComputeIfPresent(intMap, "one"));
        
        System.out.println("Compute: " + 
                demonstrateCompute(intMap, "counter"));
        
        System.out.println("Merge: " + 
                demonstrateMerge(stringMap, "key1", "newValue"));
        
        System.out.println("ForEach: " + 
                demonstrateForEach(intMap));
        
        System.out.println("ReplaceAll: " + 
                demonstrateReplaceAll(stringMap));
        
        System.out.println("ConcurrentMap Features:\n" + 
                demonstrateConcurrentMapFeatures(concurrentMap));
    }
}
