package com.java.features.java8.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Demonstrates the enhanced Map API features introduced in Java 8
 */
public class EnhancedMapAPI {

    /**
     * Demonstrates putIfAbsent method
     */
    public static void demonstratePutIfAbsent() {
        Map<String, Integer> scores = new HashMap<>();
        
        // Only puts if key doesn't exist
        scores.putIfAbsent("John", 100);
        scores.putIfAbsent("John", 200); // Won't change the value
        
        System.out.println("John's score: " + scores.get("John")); // Prints 100
    }

    /**
     * Demonstrates computeIfAbsent method
     */
    public static void demonstrateComputeIfAbsent() {
        Map<String, Integer> scores = new HashMap<>();
        
        // Computes value if key is absent
        scores.computeIfAbsent("John", key -> key.length() * 10);
        System.out.println("John's score: " + scores.get("John")); // Prints 40
    }

    /**
     * Demonstrates computeIfPresent method
     */
    public static void demonstrateComputeIfPresent() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("John", 100);
        
        // Computes new value if key is present
        scores.computeIfPresent("John", (key, value) -> value + 50);
        System.out.println("John's updated score: " + scores.get("John")); // Prints 150
    }

    /**
     * Demonstrates compute method
     */
    public static void demonstrateCompute() {
        Map<String, Integer> scores = new HashMap<>();
        
        // Computes value regardless of key presence
        scores.compute("John", (key, value) -> (value == null) ? 100 : value + 50);
        System.out.println("John's computed score: " + scores.get("John")); // Prints 100
    }

    /**
     * Demonstrates merge method
     */
    public static void demonstrateMerge() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("John", 100);
        
        // Merges value with existing value
        scores.merge("John", 50, Integer::sum);
        System.out.println("John's merged score: " + scores.get("John")); // Prints 150
    }

    /**
     * Demonstrates forEach method
     */
    public static void demonstrateForEach() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("John", 100);
        scores.put("Jane", 150);
        
        // Iterates over entries
        scores.forEach((key, value) -> 
            System.out.println(key + " scored " + value));
    }

    /**
     * Demonstrates replaceAll method
     */
    public static void demonstrateReplaceAll() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("John", 100);
        scores.put("Jane", 150);
        
        // Replaces all values
        scores.replaceAll((key, value) -> value + 10);
    }

    /**
     * Demonstrates getOrDefault method
     */
    public static void demonstrateGetOrDefault() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("John", 100);
        
        // Gets value or returns default if key not present
        int janeScore = scores.getOrDefault("Jane", 0);
        System.out.println("Jane's score: " + janeScore); // Prints 0
    }

    /**
     * Demonstrates remove with value method
     */
    public static void demonstrateRemoveWithValue() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("John", 100);
        
        // Removes entry only if key maps to specified value
        boolean removed = scores.remove("John", 100);
        System.out.println("Entry removed: " + removed); // Prints true
    }

    /**
     * Demonstrates replace methods
     */
    public static void demonstrateReplace() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("John", 100);
        
        // Replace value for key
        scores.replace("John", 150);
        
        // Replace value for key only if currently mapped to specific value
        scores.replace("John", 150, 200);
    }

    /**
     * Demonstrates ConcurrentHashMap specific features
     */
    public static void demonstrateConcurrentMapFeatures() {
        ConcurrentHashMap<String, Integer> concurrentScores = new ConcurrentHashMap<>();
        concurrentScores.put("John", 100);
        concurrentScores.put("Jane", 150);
        
        // Search
        Integer result = concurrentScores.search(2, (key, value) -> 
            value > 120 ? value : null);
        
        // Reduce
        Integer sum = concurrentScores.reduce(2,
            (key, value) -> value,
            Integer::sum);
            
        // ForEach with parallelism threshold
        concurrentScores.forEach(2,
            (key, value) -> System.out.println(key + ": " + value));
    }
}
