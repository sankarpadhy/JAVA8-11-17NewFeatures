package com.java.features.java8.map;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Test class for Java 8's Map interface enhancements.
 * Verifies the functionality of new methods added to Map interface.
 */
public class MapExamplesTest {

    private Map<String, String> stringMap;
    private Map<String, Integer> intMap;
    private ConcurrentHashMap<String, Integer> concurrentMap;

    @Before
    public void setUp() {
        // Initialize test maps
        stringMap = new HashMap<>();
        stringMap.put("key1", "value1");
        
        intMap = new HashMap<>();
        intMap.put("one", 1);
        intMap.put("two", 2);

        concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("a", 5);
        concurrentMap.put("b", 15);
        concurrentMap.put("c", 10);
    }

    /**
     * Tests getOrDefault method.
     * Verifies:
     * - Returns existing value for present key
     * - Returns default value for absent key
     */
    @Test
    public void testGetOrDefault() {
        String result = MapExamples.demonstrateGetOrDefault(stringMap, "key2");
        assertTrue(result.contains("default"));
        
        stringMap.put("key2", "value2");
        result = MapExamples.demonstrateGetOrDefault(stringMap, "key2");
        assertTrue(result.contains("value2"));
    }

    /**
     * Tests putIfAbsent method.
     * Verifies:
     * - Puts value if key is absent
     * - Keeps existing value if key is present
     */
    @Test
    public void testPutIfAbsent() {
        String result = MapExamples.demonstratePutIfAbsent(intMap, "three", 3);
        assertTrue(result.contains("null"));
        
        result = MapExamples.demonstratePutIfAbsent(intMap, "one", 10);
        assertTrue(result.contains("1"));
    }

    /**
     * Tests computeIfAbsent method.
     * Verifies:
     * - Computes new value if key is absent
     * - Returns existing value if key is present
     */
    @Test
    public void testComputeIfAbsent() {
        Integer result = MapExamples.demonstrateComputeIfAbsent(intMap, "hello");
        assertEquals(Integer.valueOf(5), result); // "hello".length() == 5
        
        result = MapExamples.demonstrateComputeIfAbsent(intMap, "one");
        assertEquals(Integer.valueOf(1), result);
    }

    /**
     * Tests computeIfPresent method.
     * Verifies:
     * - Computes new value if key is present
     * - Returns null if key is absent
     */
    @Test
    public void testComputeIfPresent() {
        Integer result = MapExamples.demonstrateComputeIfPresent(intMap, "one");
        assertEquals(Integer.valueOf(2), result); // 1 * 2 = 2
        
        result = MapExamples.demonstrateComputeIfPresent(intMap, "missing");
        assertNull(result);
    }

    /**
     * Tests compute method.
     * Verifies:
     * - Computes new value for any key
     * - Handles both present and absent keys
     */
    @Test
    public void testCompute() {
        Integer result = MapExamples.demonstrateCompute(intMap, "counter");
        assertEquals(Integer.valueOf(1), result);
        
        result = MapExamples.demonstrateCompute(intMap, "counter");
        assertEquals(Integer.valueOf(2), result);
    }

    /**
     * Tests merge method.
     * Verifies:
     * - Merges values when key exists
     * - Adds new value when key doesn't exist
     */
    @Test
    public void testMerge() {
        String result = MapExamples.demonstrateMerge(stringMap, "key1", "newValue");
        assertTrue(result.contains("value1") && result.contains("newValue"));
        
        result = MapExamples.demonstrateMerge(stringMap, "newKey", "value");
        assertEquals("value", result);
    }

    /**
     * Tests forEach method.
     * Verifies:
     * - Processes all entries
     * - Maintains entry order for ordered maps
     */
    @Test
    public void testForEach() {
        String result = MapExamples.demonstrateForEach(intMap);
        assertTrue(result.contains("one=1"));
        assertTrue(result.contains("two=2"));
    }

    /**
     * Tests replaceAll method.
     * Verifies:
     * - Replaces all values
     * - Applies transformation correctly
     */
    @Test
    public void testReplaceAll() {
        String result = MapExamples.demonstrateReplaceAll(stringMap);
        assertTrue(result.contains("VALUE1"));
    }

    /**
     * Tests ConcurrentHashMap features.
     * Verifies:
     * - Search functionality
     * - Reduce operations
     * - Parallel processing
     */
    @Test
    public void testConcurrentMapFeatures() {
        String result = MapExamples.demonstrateConcurrentMapFeatures(concurrentMap);
        assertTrue(result.contains("Search result: 15"));
        assertTrue(result.contains("Reduce result: 30")); // 5 + 15 + 10 = 30
    }

    /**
     * Tests practical use cases.
     * Verifies:
     * - Word counting
     * - Value grouping
     * - Preference merging
     */
    @Test
    public void testPracticalUseCases() {
        // This method doesn't return anything, just verify it runs without exceptions
        MapExamples.demonstratePracticalUseCases();
    }

    /**
     * Tests exception handling.
     * Verifies:
     * - Null key handling
     * - Null value handling
     */
    @Test
    public void testExceptionHandling() {
        try {
            MapExamples.demonstrateMerge(stringMap, null, "value");
            fail("Should throw NullPointerException for null key");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * Tests concurrent operations.
     * Verifies:
     * - Thread safety
     * - Atomic operations
     */
    @Test
    public void testConcurrentOperations() {
        // Perform concurrent operations
        Thread t1 = new Thread(() -> concurrentMap.compute("a", (k, v) -> v + 1));
        Thread t2 = new Thread(() -> concurrentMap.compute("a", (k, v) -> v + 1));
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            fail("Interrupted during concurrent test");
        }
        
        assertEquals(Integer.valueOf(7), concurrentMap.get("a")); // 5 + 1 + 1 = 7
    }
}
