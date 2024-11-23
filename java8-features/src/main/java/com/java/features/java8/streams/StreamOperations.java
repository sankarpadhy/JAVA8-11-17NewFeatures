package com.java.features.java8.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Demonstrates various Stream API operations introduced in Java 8
 */
public class StreamOperations {

    /**
     * Demonstrates basic filtering and mapping operations
     * @param numbers List of integers
     * @return List of squared even numbers
     */
    public static List<Integer> squareEvenNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .collect(Collectors.toList());
    }

    /**
     * Demonstrates the reduce operation
     * @param numbers List of integers
     * @return Sum of all numbers
     */
    public static int sumNumbers(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);
    }

    /**
     * Demonstrates grouping by operation
     * @param words List of strings
     * @return Map of words grouped by their length
     */
    public static Map<Integer, List<String>> groupByLength(List<String> words) {
        return words.stream()
                .collect(Collectors.groupingBy(String::length));
    }

    /**
     * Demonstrates flatMap operation
     * @param listOfLists List of lists of integers
     * @return Flattened and sorted list of unique numbers
     */
    public static List<Integer> flattenAndSort(List<List<Integer>> listOfLists) {
        return listOfLists.stream()
                .flatMap(List::stream)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Demonstrates parallel stream processing
     * @param numbers List of integers
     * @return List of processed numbers
     */
    public static List<Integer> processInParallel(List<Integer> numbers) {
        return numbers.parallelStream()
                .filter(n -> n > 10)
                .map(n -> n * 2)
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Demonstrates collectors for statistics
     * @param numbers List of integers
     * @return Summary statistics
     */
    public static DoubleSummaryStatistics getStatistics(List<Integer> numbers) {
        return numbers.stream()
                .mapToDouble(Integer::doubleValue)
                .summaryStatistics();
    }

    /**
     * Demonstrates infinite stream with limit
     * @param n Limit
     * @return List of first n Fibonacci numbers
     */
    public static List<Long> generateFibonacci(int n) {
        return Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
                .limit(n)
                .map(f -> f[0])
                .collect(Collectors.toList());
    }
}
