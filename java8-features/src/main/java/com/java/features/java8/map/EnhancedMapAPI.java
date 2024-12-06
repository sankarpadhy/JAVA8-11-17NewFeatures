package com.java.features.java8.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Demonstrates the enhanced Map API features introduced in Java 8.
 * This class showcases the new methods added to the Map interface for more convenient
 * and functional-style operations on map entries.
 * 
 * Key improvements in Java 8 Map API:
 * 1. Default methods for common operations
 * 2. Compute methods for calculated values
 * 3. Merge operations for combining values
 * 4. Null-safe operations with getOrDefault
 * 5. Atomic operations with putIfAbsent
 * 6. Functional iteration with forEach
 * 7. Bulk operations with replaceAll
 * 8. Thread-safe operations in ConcurrentHashMap
 * 
 * Example usage pattern:
 * ```java
 * Map<String, Integer> map = new HashMap<>();
 * 
 * // Old way (pre-Java 8)
 * if (!map.containsKey("key")) {
 *     map.put("key", 1);
 * }
 * 
 * // New way (Java 8)
 * map.putIfAbsent("key", 1);
 * ```
 */
public class EnhancedMapAPI {

    /**
     * Demonstrates the putIfAbsent method which atomically adds a value only if
     * the key is not already present in the map.
     * 
     * Example usage:
     * ```java
     * Map<String, Integer> scores = new HashMap<>();
     * scores.putIfAbsent("John", 100);  // Sets score to 100
     * scores.putIfAbsent("John", 200);  // No effect, keeps 100
     * ```
     * 
     * Use cases:
     * - Initializing default values
     * - Thread-safe value initialization
     * - Caching scenarios
     */
    public static void demonstratePutIfAbsent() {
        Map<String, Integer> scores = new HashMap<>();
        
        scores.putIfAbsent("John", 100);
        scores.putIfAbsent("John", 200); // Won't change the value
        
        System.out.println("John's score: " + scores.get("John")); // Prints 100
    }

    /**
     * Demonstrates computeIfAbsent which calculates a value if the key is not present.
     * The computation is only performed if the key is absent, making it more efficient
     * than checking and computing separately.
     * 
     * Example usage:
     * ```java
     * Map<String, List<String>> groups = new HashMap<>();
     * groups.computeIfAbsent("A", key -> new ArrayList<>()).add("item");
     * 
     * Map<String, Integer> scores = new HashMap<>();
     * scores.computeIfAbsent("John", key -> key.length() * 10); // Sets to 40
     * ```
     * 
     * Use cases:
     * - Lazy initialization of complex values
     * - Building multimap structures
     * - Caching computed values
     */
    public static void demonstrateComputeIfAbsent() {
        Map<String, Integer> scores = new HashMap<>();
        
        scores.computeIfAbsent("John", key -> key.length() * 10);
        System.out.println("John's score: " + scores.get("John")); // Prints 40
    }

    /**
     * Demonstrates computeIfPresent which modifies a value if the key exists.
     * The computation is only performed if the key is present, providing a safe
     * way to update existing values.
     * 
     * Example usage:
     * ```java
     * Map<String, Integer> scores = new HashMap<>();
     * scores.put("John", 100);
     * scores.computeIfPresent("John", (key, value) -> value + 50); // Updates to 150
     * scores.computeIfPresent("Jane", (key, value) -> value + 50); // No effect
     * ```
     * 
     * Use cases:
     * - Updating counters
     * - Modifying existing values
     * - Conditional updates
     */
    public static void demonstrateComputeIfPresent() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("John", 100);
        
        scores.computeIfPresent("John", (key, value) -> value + 50);
        System.out.println("John's updated score: " + scores.get("John")); // Prints 150
    }

    /**
     * Demonstrates the compute method which always attempts to compute a new value.
     * This method is useful when you want to update a value regardless of whether
     * the key exists or not.
     * 
     * Example usage:
     * ```java
     * Map<String, Integer> scores = new HashMap<>();
     * 
     * // Initialize or update
     * scores.compute("John", (key, value) -> 
     *     (value == null) ? 100 : value + 50);
     * 
     * // Remove if computation returns null
     * scores.compute("John", (key, value) -> null); // Removes entry
     * ```
     * 
     * Use cases:
     * - Atomic update-or-initialize operations
     * - Complex value calculations
     * - Conditional removal (by returning null)
     */
    public static void demonstrateCompute() {
        Map<String, Integer> scores = new HashMap<>();
        
        scores.compute("John", (key, value) -> (value == null) ? 100 : value + 50);
        System.out.println("John's computed score: " + scores.get("John")); // Prints 100
    }

    /**
     * Demonstrates the merge method which combines the existing and new values.
     * This method provides a way to combine values when the key might or might not exist.
     * 
     * Example usage:
     * ```java
     * Map<String, Integer> scores = new HashMap<>();
     * scores.put("John", 100);
     * 
     * // Add values
     * scores.merge("John", 50, Integer::sum);  // 150
     * 
     * // Remove if result is null
     * scores.merge("John", 50, (old, new) -> null);  // Removes entry
     * ```
     * 
     * Use cases:
     * - Combining values (e.g., summing counters)
     * - Concatenating strings
     * - Implementing map-reduce patterns
     */
    public static void demonstrateMerge() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("John", 100);
        
        scores.merge("John", 50, Integer::sum);
        System.out.println("John's merged score: " + scores.get("John")); // Prints 150
    }

    /**
     * Demonstrates the forEach method for iterating over map entries.
     * Provides a functional approach to map iteration using BiConsumer.
     * 
     * Example usage:
     * ```java
     * Map<String, Integer> scores = new HashMap<>();
     * scores.put("John", 100);
     * scores.put("Jane", 150);
     * 
     * // Print entries
     * scores.forEach((key, value) -> 
     *     System.out.printf("%s: %d%n", key, value));
     * 
     * // Update values
     * scores.forEach((key, value) -> 
     *     scores.compute(key, (k, v) -> v + 10));
     * ```
     * 
     * Use cases:
     * - Bulk processing of entries
     * - Logging/debugging
     * - Data transformation
     */
    public static void demonstrateForEach() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("John", 100);
        scores.put("Jane", 150);
        
        scores.forEach((key, value) -> 
            System.out.println(key + " scored " + value));
    }

    /**
     * Demonstrates the replaceAll method for bulk updates of map values.
     * Provides a way to transform all values in the map using a BiFunction.
     * 
     * Example usage:
     * ```java
     * Map<String, Integer> scores = new HashMap<>();
     * scores.put("John", 100);
     * scores.put("Jane", 150);
     * 
     * // Add bonus points
     * scores.replaceAll((key, value) -> value + 10);
     * 
     * // Apply percentage increase
     * scores.replaceAll((key, value) -> (int)(value * 1.1));
     * ```
     * 
     * Use cases:
     * - Bulk value updates
     * - Data normalization
     * - Applying transformations
     */
    public static void demonstrateReplaceAll() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("John", 100);
        scores.put("Jane", 150);
        
        scores.replaceAll((key, value) -> value + 10);
    }

    /**
     * Demonstrates the getOrDefault method for safe value retrieval.
     * Returns a default value when the key is not found, avoiding null checks.
     * 
     * Example usage:
     * ```java
     * Map<String, Integer> scores = new HashMap<>();
     * scores.put("John", 100);
     * 
     * int johnScore = scores.getOrDefault("John", 0);  // Returns 100
     * int janeScore = scores.getOrDefault("Jane", 0);  // Returns 0
     * ```
     * 
     * Use cases:
     * - Default value handling
     * - Null-safe operations
     * - Configuration parameters
     */
    public static void demonstrateGetOrDefault() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("John", 100);
        
        int janeScore = scores.getOrDefault("Jane", 0);
        System.out.println("Jane's score: " + janeScore); // Prints 0
    }

    /**
     * Demonstrates the remove method with value matching.
     * Removes the entry only if the key is mapped to the specified value.
     * 
     * Example usage:
     * ```java
     * Map<String, Integer> scores = new HashMap<>();
     * scores.put("John", 100);
     * 
     * boolean removed1 = scores.remove("John", 100);  // Returns true
     * boolean removed2 = scores.remove("John", 200);  // Returns false
     * ```
     * 
     * Use cases:
     * - Conditional removal
     * - Atomic operations
     * - Version control (remove only if value matches expected)
     */
    public static void demonstrateRemoveWithValue() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("John", 100);
        
        boolean removed = scores.remove("John", 100);
        System.out.println("Entry removed: " + removed); // Prints true
    }

    /**
     * Demonstrates the replace methods for updating existing entries.
     * Provides both unconditional and conditional replacement options.
     * 
     * Example usage:
     * ```java
     * Map<String, Integer> scores = new HashMap<>();
     * scores.put("John", 100);
     * 
     * // Unconditional replace
     * scores.replace("John", 150);  // Updates to 150
     * 
     * // Conditional replace
     * boolean updated = scores.replace("John", 150, 200);  // Returns true
     * ```
     * 
     * Use cases:
     * - Atomic updates
     * - Compare-and-set operations
     * - Value versioning
     */
    public static void demonstrateReplace() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("John", 100);
        
        scores.replace("John", 150);
        scores.replace("John", 150, 200);
    }

    /**
     * Demonstrates ConcurrentHashMap-specific features for parallel operations.
     * These methods are optimized for concurrent access and bulk operations.
     * 
     * Example usage:
     * ```java
     * ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
     * map.put("John", 100);
     * map.put("Jane", 150);
     * 
     * // Parallel search
     * Integer highScore = map.search(2, (key, value) -> 
     *     value > 120 ? value : null);
     * 
     * // Parallel reduce
     * Integer total = map.reduce(2,
     *     (key, value) -> value,
     *     Integer::sum);
     * ```
     * 
     * Use cases:
     * - Parallel processing
     * - Concurrent updates
     * - High-performance computing
     */
    public static void demonstrateConcurrentMapFeatures() {
        ConcurrentHashMap<String, Integer> concurrentScores = new ConcurrentHashMap<>();
        concurrentScores.put("John", 100);
        concurrentScores.put("Jane", 150);
        
        // Search with parallelism threshold
        Integer result = concurrentScores.search(2, (key, value) -> 
            value > 120 ? value : null);
        
        // Reduce with parallelism threshold
        Integer sum = concurrentScores.reduce(2,
            (key, value) -> value,
            Integer::sum);
            
        // ForEach with parallelism threshold
        concurrentScores.forEach(2,
            (key, value) -> System.out.println(key + ": " + value));
    }
}
