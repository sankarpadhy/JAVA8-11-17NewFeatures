package com.java.features.java11.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Demonstrates the new Predicate.not() method introduced in Java 11.
 * This method creates a predicate that is the negation of the supplied predicate.
 */
public class PredicateNotExample {

    /**
     * Demonstrates filtering using Predicate.not() with strings.
     * 
     * Example:
     * ```java
     * List<String> input = Arrays.asList("", "Hello", "", "World", "  ");
     * demonstrateStringPredicateNot(input);
     * // Output:
     * // Non-blank strings: [Hello, World]
     * // Non-empty strings: [Hello, World, "  "]
     * ```
     * 
     * @param strings list of strings to filter
     */
    public void demonstrateStringPredicateNot(List<String> strings) {
        // Filter out blank strings
        List<String> nonBlank = strings.stream()
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());
        System.out.println("Non-blank strings: " + nonBlank);

        // Filter out empty strings
        List<String> nonEmpty = strings.stream()
                .filter(Predicate.not(String::isEmpty))
                .collect(Collectors.toList());
        System.out.println("Non-empty strings: " + nonEmpty);
    }

    /**
     * Demonstrates filtering using Predicate.not() with numbers.
     * 
     * Example:
     * ```java
     * List<Integer> numbers = Arrays.asList(1, -2, 3, -4, 5, 0);
     * demonstrateNumberPredicateNot(numbers);
     * // Output:
     * // Non-negative numbers: [1, 3, 5, 0]
     * // Non-zero numbers: [1, -2, 3, -4, 5]
     * ```
     * 
     * @param numbers list of numbers to filter
     */
    public void demonstrateNumberPredicateNot(List<Integer> numbers) {
        // Filter out negative numbers
        List<Integer> nonNegative = numbers.stream()
                .filter(Predicate.not(n -> n < 0))
                .collect(Collectors.toList());
        System.out.println("Non-negative numbers: " + nonNegative);

        // Filter out zeros
        List<Integer> nonZero = numbers.stream()
                .filter(Predicate.not(n -> n == 0))
                .collect(Collectors.toList());
        System.out.println("Non-zero numbers: " + nonZero);
    }

    /**
     * Demonstrates combining Predicate.not() with other predicates.
     * 
     * Example:
     * ```java
     * List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
     * demonstrateCombinedPredicates(words);
     * // Output:
     * // Words not starting with 'a' and length > 5: [banana, cherry]
     * ```
     * 
     * @param words list of words to filter
     */
    public void demonstrateCombinedPredicates(List<String> words) {
        // Complex predicate: not starting with 'a' AND length > 5
        List<String> filtered = words.stream()
                .filter(Predicate.not(s -> s.startsWith("a"))
                        .and(s -> s.length() > 5))
                .collect(Collectors.toList());
        System.out.println("Words not starting with 'a' and length > 5: " + filtered);
    }

    public static void main(String[] args) {
        PredicateNotExample demo = new PredicateNotExample();

        System.out.println("=== String Predicate.not() Demo ===");
        List<String> strings = Arrays.asList("", "Hello", "", "World", "  ");
        demo.demonstrateStringPredicateNot(strings);

        System.out.println("\n=== Number Predicate.not() Demo ===");
        List<Integer> numbers = Arrays.asList(1, -2, 3, -4, 5, 0);
        demo.demonstrateNumberPredicateNot(numbers);

        System.out.println("\n=== Combined Predicates Demo ===");
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
        demo.demonstrateCombinedPredicates(words);
    }
}
