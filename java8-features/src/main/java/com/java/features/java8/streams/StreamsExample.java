package com.java.features.java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Comparator;

/**
 * Demonstrates the Stream API features introduced in Java 8.
 * This class provides examples of common stream operations including
 * filtering, mapping, reducing, and collecting results.
 * 
 * Sample usage:
 * ```java
 * StreamsExample streams = new StreamsExample();
 * 
 * // Filter and map numbers
 * List<Integer> doubled = streams.doubleEvenNumbers(Arrays.asList(1, 2, 3, 4, 5));
 * System.out.println(doubled); // Prints: [4, 8]
 * 
 * // Find average age
 * double avgAge = streams.calculateAverageAge();
 * System.out.println(avgAge); // Prints: 25.0
 * ```
 */
public class StreamsExample {

    /**
     * Demonstrates filtering and mapping operations on a stream.
     * Filters for even numbers and doubles them.
     * 
     * Sample usage:
     * ```java
     * List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
     * List<Integer> result = doubleEvenNumbers(numbers);
     * // Returns: [4, 8]
     * ```
     * 
     * @param numbers List of integers to process
     * @return List of doubled even numbers
     */
    public List<Integer> doubleEvenNumbers(List<Integer> numbers) {
        return numbers.stream()
                     .filter(n -> n % 2 == 0)     // Keep only even numbers
                     .map(n -> n * 2)             // Double each number
                     .collect(Collectors.toList());
    }

    /**
     * Demonstrates the use of flatMap to flatten nested collections.
     * 
     * Sample usage:
     * ```java
     * List<List<Integer>> nestedLists = Arrays.asList(
     *     Arrays.asList(1, 2),
     *     Arrays.asList(3, 4)
     * );
     * List<Integer> result = flattenLists(nestedLists);
     * // Returns: [1, 2, 3, 4]
     * ```
     * 
     * @param nestedLists List of lists to flatten
     * @return Flattened list of integers
     */
    public List<Integer> flattenLists(List<List<Integer>> nestedLists) {
        return nestedLists.stream()
                         .flatMap(List::stream)    // Flatten nested lists
                         .collect(Collectors.toList());
    }

    /**
     * Demonstrates reduction operations using reduce() to find the sum.
     * 
     * Sample usage:
     * ```java
     * List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
     * int sum = calculateSum(numbers);
     * // Returns: 15
     * ```
     * 
     * @param numbers List of integers to sum
     * @return Sum of all numbers
     */
    public int calculateSum(List<Integer> numbers) {
        return numbers.stream()
                     .reduce(0, Integer::sum);    // Sum all numbers
    }

    /**
     * Demonstrates collecting results into a Map.
     * Groups strings by their length.
     * 
     * Sample usage:
     * ```java
     * List<String> words = Arrays.asList("cat", "dog", "elephant");
     * Map<Integer, List<String>> result = groupByLength(words);
     * // Returns: {3=[cat, dog], 8=[elephant]}
     * ```
     * 
     * @param strings List of strings to group
     * @return Map of string length to list of strings
     */
    public Map<Integer, List<String>> groupByLength(List<String> strings) {
        return strings.stream()
                     .collect(Collectors.groupingBy(String::length));
    }

    /**
     * Demonstrates finding the maximum value using max().
     * 
     * Sample usage:
     * ```java
     * List<Integer> numbers = Arrays.asList(1, 5, 3, 8, 2);
     * Optional<Integer> max = findMax(numbers);
     * // Returns: Optional[8]
     * ```
     * 
     * @param numbers List of integers to search
     * @return Optional containing the maximum value, or empty if list is empty
     */
    public Optional<Integer> findMax(List<Integer> numbers) {
        return numbers.stream()
                     .max(Comparator.naturalOrder());
    }

    /**
     * Demonstrates parallel stream processing.
     * Calculates the sum of squares in parallel.
     * 
     * Sample usage:
     * ```java
     * List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
     * int result = calculateSumOfSquaresParallel(numbers);
     * // Returns: 30 (1 + 4 + 9 + 16)
     * ```
     * 
     * @param numbers List of integers to process
     * @return Sum of squares
     */
    public int calculateSumOfSquaresParallel(List<Integer> numbers) {
        return numbers.parallelStream()
                     .mapToInt(n -> n * n)
                     .sum();
    }

    /**
     * Demonstrates string joining using Collectors.joining().
     * 
     * Sample usage:
     * ```java
     * List<String> words = Arrays.asList("Java", "8", "Streams");
     * String result = joinStrings(words);
     * // Returns: "Java, 8, Streams"
     * ```
     * 
     * @param strings List of strings to join
     * @return Joined string with comma separator
     */
    public String joinStrings(List<String> strings) {
        return strings.stream()
                     .collect(Collectors.joining(", "));
    }

    /**
     * Demonstrates chaining multiple stream operations.
     * Finds the sum of squares of even numbers.
     * 
     * Sample usage:
     * ```java
     * List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
     * int result = sumOfSquaresOfEvens(numbers);
     * // Returns: 20 (4 + 16)
     * ```
     * 
     * @param numbers List of integers to process
     * @return Sum of squares of even numbers
     */
    public int sumOfSquaresOfEvens(List<Integer> numbers) {
        return numbers.stream()
                     .filter(n -> n % 2 == 0)    // Keep even numbers
                     .mapToInt(n -> n * n)       // Square each number
                     .sum();                     // Calculate sum
    }

    /**
     * Demonstrates creating a stream from multiple sources using Stream.concat().
     * 
     * Sample usage:
     * ```java
     * List<Integer> list1 = Arrays.asList(1, 2);
     * List<Integer> list2 = Arrays.asList(3, 4);
     * List<Integer> result = concatenateStreams(list1, list2);
     * // Returns: [1, 2, 3, 4]
     * ```
     * 
     * @param list1 First list of integers
     * @param list2 Second list of integers
     * @return Combined list of integers
     */
    public List<Integer> concatenateStreams(List<Integer> list1, List<Integer> list2) {
        return Stream.concat(list1.stream(), list2.stream())
                    .collect(Collectors.toList());
    }
}
