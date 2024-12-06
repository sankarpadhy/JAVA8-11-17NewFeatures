package com.java.features.java8.streams;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Test class for demonstrating and verifying Stream API operations.
 * Tests cover various stream operations including:
 * - Filtering and mapping
 * - Reduction operations
 * - Collection operations
 * - Parallel processing
 * - Advanced stream operations
 */
public class StreamOperationsTest {

    /**
     * Tests basic filtering and mapping operations on streams.
     * Verifies that:
     * 1. Even numbers are correctly filtered
     * 2. Numbers are properly squared
     * 3. Results are collected in the correct order
     */
    @Test
    public void testSquareEvenNumbers() {
        // Setup test data
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        
        // Execute operation
        List<Integer> result = StreamOperations.squareEvenNumbers(numbers);
        
        // Verify results
        List<Integer> expected = Arrays.asList(4, 16, 36);
        assertEquals("Should square even numbers only", expected, result);
        
        // Test empty list
        List<Integer> emptyResult = StreamOperations.squareEvenNumbers(Collections.emptyList());
        assertTrue("Empty list should return empty result", emptyResult.isEmpty());
        
        // Test list with no even numbers
        List<Integer> oddNumbers = Arrays.asList(1, 3, 5, 7);
        List<Integer> oddResult = StreamOperations.squareEvenNumbers(oddNumbers);
        assertTrue("List with no even numbers should return empty result", oddResult.isEmpty());
    }

    /**
     * Tests the reduce operation for combining elements.
     * Verifies:
     * 1. Correct sum calculation
     * 2. Handling of empty lists
     * 3. Handling of single element lists
     */
    @Test
    public void testSumNumbers() {
        // Test normal case
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = StreamOperations.sumNumbers(numbers);
        assertEquals("Sum should be 15", 15, sum);
        
        // Test empty list
        List<Integer> emptyList = Collections.emptyList();
        assertEquals("Empty list should sum to 0", 0, StreamOperations.sumNumbers(emptyList));
        
        // Test single element
        List<Integer> singleElement = Collections.singletonList(5);
        assertEquals("Single element should sum to itself", 5, StreamOperations.sumNumbers(singleElement));
        
        // Test negative numbers
        List<Integer> mixedNumbers = Arrays.asList(-1, -2, 3, 4);
        assertEquals("Should handle negative numbers", 4, StreamOperations.sumNumbers(mixedNumbers));
    }

    /**
     * Tests grouping operations using collectors.
     * Verifies:
     * 1. Correct grouping by length
     * 2. Handling of empty strings
     * 3. Handling of duplicate lengths
     */
    @Test
    public void testGroupByLength() {
        // Test normal case
        List<String> words = Arrays.asList("cat", "dog", "bird", "elephant");
        Map<Integer, List<String>> groups = StreamOperations.groupByLength(words);
        
        assertEquals("Should have 3 different lengths", 3, groups.size());
        assertTrue("Should contain words of length 3", groups.get(3).containsAll(Arrays.asList("cat", "dog")));
        assertTrue("Should contain word of length 4", groups.get(4).contains("bird"));
        assertTrue("Should contain word of length 8", groups.get(8).contains("elephant"));
        
        // Test empty list
        Map<Integer, List<String>> emptyResult = StreamOperations.groupByLength(Collections.emptyList());
        assertTrue("Empty list should produce empty map", emptyResult.isEmpty());
        
        // Test list with empty string
        List<String> withEmpty = Arrays.asList("", "a", "bb");
        Map<Integer, List<String>> withEmptyResult = StreamOperations.groupByLength(withEmpty);
        assertTrue("Should handle empty string", withEmptyResult.containsKey(0));
        assertEquals("Should group empty string correctly", 1, withEmptyResult.get(0).size());
    }

    /**
     * Tests flatMap operations for flattening nested collections.
     * Verifies:
     * 1. Correct flattening of nested lists
     * 2. Removal of duplicates
     * 3. Proper sorting
     */
    @Test
    public void testFlattenAndSort() {
        // Test normal case
        List<List<Integer>> nested = Arrays.asList(
            Arrays.asList(3, 1),
            Arrays.asList(2, 4),
            Arrays.asList(1, 3)
        );
        
        List<Integer> result = StreamOperations.flattenAndSort(nested);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4);
        
        assertEquals("Should flatten, remove duplicates, and sort", expected, result);
        
        // Test empty outer list
        List<List<Integer>> emptyOuter = Collections.emptyList();
        assertTrue("Empty outer list should produce empty result", 
                StreamOperations.flattenAndSort(emptyOuter).isEmpty());
        
        // Test empty inner lists
        List<List<Integer>> emptyInner = Arrays.asList(
            Collections.emptyList(),
            Collections.emptyList()
        );
        assertTrue("Empty inner lists should produce empty result", 
                StreamOperations.flattenAndSort(emptyInner).isEmpty());
    }

    /**
     * Tests parallel stream processing.
     * Verifies:
     * 1. Correct filtering (> 10)
     * 2. Proper transformation (* 2)
     * 3. Correct ordering of results
     */
    @Test
    public void testProcessInParallel() {
        // Test normal case
        List<Integer> numbers = Arrays.asList(5, 15, 25, 35, 45);
        List<Integer> result = StreamOperations.processInParallel(numbers);
        
        List<Integer> expected = Arrays.asList(30, 50, 70, 90);
        assertEquals("Should filter, transform, and sort correctly", expected, result);
        
        // Test empty list
        assertTrue("Empty list should produce empty result",
                StreamOperations.processInParallel(Collections.emptyList()).isEmpty());
        
        // Test list with no qualifying numbers
        List<Integer> small = Arrays.asList(1, 5, 8, 10);
        assertTrue("Numbers <= 10 should produce empty result",
                StreamOperations.processInParallel(small).isEmpty());
    }

    /**
     * Tests statistical operations using collectors.
     * Verifies correct calculation of:
     * 1. Count
     * 2. Sum
     * 3. Average
     * 4. Min/Max values
     */
    @Test
    public void testGetStatistics() {
        // Test normal case
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        DoubleSummaryStatistics stats = StreamOperations.getStatistics(numbers);
        
        assertEquals("Should count 5 numbers", 5, stats.getCount());
        assertEquals("Sum should be 15", 15.0, stats.getSum(), 0.001);
        assertEquals("Average should be 3.0", 3.0, stats.getAverage(), 0.001);
        assertEquals("Min should be 1", 1.0, stats.getMin(), 0.001);
        assertEquals("Max should be 5", 5.0, stats.getMax(), 0.001);
        
        // Test empty list
        DoubleSummaryStatistics emptyStats = StreamOperations.getStatistics(Collections.emptyList());
        assertEquals("Empty list should have count 0", 0, emptyStats.getCount());
        assertEquals("Empty list should have sum 0", 0.0, emptyStats.getSum(), 0.001);
    }

    /**
     * Tests generation of Fibonacci sequence using infinite streams.
     * Verifies:
     * 1. Correct Fibonacci numbers
     * 2. Proper sequence length
     * 3. Handling of different sequence lengths
     */
    @Test
    public void testGenerateFibonacci() {
        // Test first 5 Fibonacci numbers
        List<Long> fib5 = StreamOperations.generateFibonacci(5);
        List<Long> expected5 = Arrays.asList(0L, 1L, 1L, 2L, 3L);
        assertEquals("Should generate first 5 Fibonacci numbers", expected5, fib5);
        
        // Test first 10 Fibonacci numbers
        List<Long> fib10 = StreamOperations.generateFibonacci(10);
        List<Long> expected10 = Arrays.asList(0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L);
        assertEquals("Should generate first 10 Fibonacci numbers", expected10, fib10);
        
        // Test edge cases
        assertTrue("Zero elements should produce empty list",
                StreamOperations.generateFibonacci(0).isEmpty());
        assertEquals("One element should produce [0]",
                Collections.singletonList(0L), StreamOperations.generateFibonacci(1));
    }
}
