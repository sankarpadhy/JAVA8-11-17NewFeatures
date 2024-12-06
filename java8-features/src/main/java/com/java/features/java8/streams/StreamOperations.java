package com.java.features.java8.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Comprehensive demonstration of Java 8 Stream API operations and patterns.
 * This class provides examples of various stream operations, from basic
 * to advanced, showing best practices and common use cases.
 *
 * Key operations demonstrated:
 * - Filtering and mapping
 * - Reduction and collection
 * - Parallel processing
 * - Flattening nested structures
 * - Grouping and partitioning
 * - Infinite stream generation
 *
 * Stream Operation Categories:
 * 1. Intermediate Operations:
 *    - filter(): Filtering elements
 *    - map(): Transforming elements
 *    - flatMap(): Flattening nested streams
 *    - sorted(): Ordering elements
 *    - distinct(): Removing duplicates
 *    - peek(): Debugging operations
 *
 * 2. Terminal Operations:
 *    - collect(): Gathering results
 *    - reduce(): Combining elements
 *    - forEach(): Processing elements
 *    - count(): Counting elements
 *    - anyMatch()/allMatch(): Testing elements
 *
 * Performance Considerations:
 * - Use parallel streams for large datasets
 * - Consider operation order for optimization
 * - Be cautious with stateful operations
 * - Avoid unnecessary boxing/unboxing
 *
 * Example usage:
 * ```java
 * List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
 * 
 * // Basic pipeline
 * numbers.stream()
 *        .filter(n -> n % 2 == 0)
 *        .map(n -> n * 2)
 *        .collect(Collectors.toList());
 * 
 * // Parallel processing
 * numbers.parallelStream()
 *        .filter(n -> n > 3)
 *        .reduce(0, Integer::sum);
 * ```
 *
 * @see java.util.stream.Stream
 * @see java.util.stream.Collectors
 * @since Java 8
 */
public class StreamOperations {

    /**
     * Demonstrates basic filtering and mapping operations on a stream.
     * Shows how to chain multiple operations to transform data.
     * 
     * Stream operations used:
     * - filter: Keeps only elements matching the predicate
     * - map: Transforms each element
     * - collect: Gathers results into a collection
     * 
     * Example usage:
     * ```java
     * List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
     * List<Integer> result = squareEvenNumbers(numbers);
     * // Result: [4, 16, 36]
     * ```
     * 
     * @param numbers List of integers to process
     * @return List containing squares of even numbers
     */
    public static List<Integer> squareEvenNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 == 0)    // Keep only even numbers
                .map(n -> n * n)            // Square each number
                .collect(Collectors.toList());
    }

    /**
     * Demonstrates the reduce operation for combining elements.
     * Reduce is a terminal operation that combines stream elements
     * into a single result.
     * 
     * Example usage:
     * ```java
     * List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
     * int sum = sumNumbers(numbers);  // Returns 15
     * 
     * // Other reduce examples:
     * Optional<Integer> max = numbers.stream()
     *     .reduce(Integer::max);
     * String concat = strings.stream()
     *     .reduce("", String::concat);
     * ```
     * 
     * @param numbers List of integers to sum
     * @return Sum of all numbers in the list
     */
    public static int sumNumbers(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);  // Identity: 0, Accumulator: Integer::sum
    }

    /**
     * Demonstrates the groupingBy collector for creating maps.
     * Groups elements based on a classification function.
     * 
     * Example usage:
     * ```java
     * List<String> words = Arrays.asList("cat", "dog", "bird", "elephant");
     * Map<Integer, List<String>> groups = groupByLength(words);
     * // Result: {3=[cat, dog], 4=[bird], 8=[elephant]}
     * 
     * // Other grouping examples:
     * Map<Boolean, List<String>> evenOdd = words.stream()
     *     .collect(Collectors.groupingBy(w -> w.length() % 2 == 0));
     * ```
     * 
     * @param words List of strings to group
     * @return Map where key is string length and value is list of strings of that length
     */
    public static Map<Integer, List<String>> groupByLength(List<String> words) {
        return words.stream()
                .collect(Collectors.groupingBy(String::length));
    }

    /**
     * Demonstrates the flatMap operation for flattening nested collections.
     * FlatMap transforms each element into a stream and then flattens all
     * streams into a single stream.
     * 
     * Example usage:
     * ```java
     * List<List<Integer>> nested = Arrays.asList(
     *     Arrays.asList(1, 2),
     *     Arrays.asList(3, 4),
     *     Arrays.asList(1, 4)
     * );
     * List<Integer> flat = flattenAndSort(nested);
     * // Result: [1, 2, 3, 4]
     * 
     * // Other flatMap examples:
     * Stream<String> words = Stream.of("Hello", "World");
     * Stream<Character> letters = words.flatMap(w -> 
     *     w.chars().mapToObj(c -> (char)c));
     * ```
     * 
     * @param listOfLists Nested list structure to flatten
     * @return Sorted list of unique integers from all nested lists
     */
    public static List<Integer> flattenAndSort(List<List<Integer>> listOfLists) {
        return listOfLists.stream()
                .flatMap(List::stream)    // Flatten nested lists
                .distinct()               // Remove duplicates
                .sorted()                 // Sort in natural order
                .collect(Collectors.toList());
    }

    /**
     * Demonstrates parallel stream processing for concurrent operations.
     * Parallel streams can improve performance for large datasets by
     * utilizing multiple threads.
     * 
     * Example usage:
     * ```java
     * List<Integer> numbers = Arrays.asList(5, 15, 25, 35, 45);
     * List<Integer> result = processInParallel(numbers);
     * // Result: [30, 50, 70, 90]
     * 
     * // When to use parallel streams:
     * // 1. Large data sets
     * // 2. Independent operations
     * // 3. Expensive computations
     * // 4. Multiple CPU cores available
     * ```
     * 
     * Note: Parallel streams may not always be faster due to:
     * - Overhead of splitting/merging
     * - Thread coordination
     * - Non-thread-safe operations
     * 
     * @param numbers List of integers to process in parallel
     * @return Processed list of integers
     */
    public static List<Integer> processInParallel(List<Integer> numbers) {
        return numbers.parallelStream()
                .filter(n -> n > 10)     // Filter in parallel
                .map(n -> n * 2)         // Transform in parallel
                .sorted()                // Sort results
                .collect(Collectors.toList());
    }

    /**
     * Demonstrates collectors for gathering statistical information.
     * DoubleSummaryStatistics provides count, sum, min, max, and average.
     * 
     * Example usage:
     * ```java
     * List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
     * DoubleSummaryStatistics stats = getStatistics(numbers);
     * 
     * System.out.println("Count: " + stats.getCount());     // 5
     * System.out.println("Sum: " + stats.getSum());         // 15.0
     * System.out.println("Min: " + stats.getMin());         // 1.0
     * System.out.println("Max: " + stats.getMax());         // 5.0
     * System.out.println("Average: " + stats.getAverage()); // 3.0
     * ```
     * 
     * @param numbers List of integers to analyze
     * @return Statistical summary of the numbers
     */
    public static DoubleSummaryStatistics getStatistics(List<Integer> numbers) {
        return numbers.stream()
                .mapToDouble(Integer::doubleValue)
                .summaryStatistics();
    }

    /**
     * Demonstrates infinite stream generation with limit.
     * Shows how to create and process infinite sequences using Stream.iterate.
     * 
     * Example usage:
     * ```java
     * List<Long> fib5 = generateFibonacci(5);
     * // Result: [0, 1, 1, 2, 3]
     * 
     * List<Long> fib10 = generateFibonacci(10);
     * // Result: [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
     * ```
     * 
     * Other infinite stream examples:
     * ```java
     * // Generate powers of 2
     * Stream.iterate(1, n -> n * 2).limit(10);
     * 
     * // Generate random numbers
     * Stream.generate(Math::random).limit(10);
     * ```
     * 
     * @param n Number of Fibonacci numbers to generate
     * @return List of first n Fibonacci numbers
     */
    public static List<Long> generateFibonacci(int n) {
        return Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
                .limit(n)                // Take only n elements
                .map(f -> f[0])         // Extract first number of pair
                .collect(Collectors.toList());
    }
}
