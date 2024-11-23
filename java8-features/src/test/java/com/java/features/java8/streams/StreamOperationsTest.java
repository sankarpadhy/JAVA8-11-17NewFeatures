package com.java.features.java8.streams;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StreamOperationsTest {

    @Test
    void testSquareEvenNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> result = StreamOperations.squareEvenNumbers(numbers);
        assertEquals(Arrays.asList(4, 16, 36), result);
    }

    @Test
    void testSumNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = StreamOperations.sumNumbers(numbers);
        assertEquals(15, sum);
    }

    @Test
    void testGroupByLength() {
        List<String> words = Arrays.asList("a", "bb", "ccc", "dd", "e");
        Map<Integer, List<String>> grouped = StreamOperations.groupByLength(words);
        
        assertEquals(2, grouped.get(1).size());
        assertEquals(2, grouped.get(2).size());
        assertEquals(1, grouped.get(3).size());
    }

    @Test
    void testFlattenAndSort() {
        List<List<Integer>> input = Arrays.asList(
            Arrays.asList(3, 1),
            Arrays.asList(2, 4),
            Arrays.asList(1, 3)
        );
        List<Integer> result = StreamOperations.flattenAndSort(input);
        assertEquals(Arrays.asList(1, 2, 3, 4), result);
    }

    @Test
    void testProcessInParallel() {
        List<Integer> numbers = Arrays.asList(5, 12, 15, 8, 20);
        List<Integer> result = StreamOperations.processInParallel(numbers);
        assertEquals(Arrays.asList(24, 30, 40), result);
    }

    @Test
    void testGetStatistics() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        DoubleSummaryStatistics stats = StreamOperations.getStatistics(numbers);
        
        assertEquals(1, stats.getMin());
        assertEquals(5, stats.getMax());
        assertEquals(3.0, stats.getAverage());
        assertEquals(15, stats.getSum());
        assertEquals(5, stats.getCount());
    }

    @Test
    void testGenerateFibonacci() {
        List<Long> fibonacci = StreamOperations.generateFibonacci(6);
        assertEquals(Arrays.asList(0L, 1L, 1L, 2L, 3L, 5L), fibonacci);
    }
}
