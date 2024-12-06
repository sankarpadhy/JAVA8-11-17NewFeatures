package com.java.features.java8.lambda;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Demonstrates basic lambda expressions and functional interfaces in Java 8.
 */
public class LambdaBasics {

    /**
     * Demonstrates a simple Predicate usage
     * 
     * @param number Number to test
     * @return true if number is positive
     */
    public static boolean isPositive(Integer number) {
        Predicate<Integer> isPositive = n -> n > 0;
        return isPositive.test(number);
    }

    /**
     * Demonstrates Function interface usage
     * 
     * @param str Input string
     * @return Length of the string
     */
    public static Integer getStringLength(String str) {
        Function<String, Integer> length = String::length;
        return length.apply(str);
    }

    /**
     * Demonstrates Consumer interface usage
     * 
     * @param list List to process
     */
    public static void processElements(List<String> list) {
        Consumer<String> printer = System.out::println;
        list.forEach(printer);
    }

    /**
     * Demonstrates Supplier interface usage
     * 
     * @return Random number between 1 and 100
     */
    public static Integer getRandomNumber() {
        Supplier<Integer> randomSupplier = () -> (int) (Math.random() * 100) + 1;
        return randomSupplier.get();
    }

    /**
     * Demonstrates chaining of predicates
     * 
     * @param number Number to test
     * @return true if number is positive and even
     */
    public static boolean isPositiveAndEven(Integer number) {
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEven = n -> n % 2 == 0;
        return isPositive.and(isEven).test(number);
    }

    /**
     * Example of method reference usage
     * 
     * @param strings List of strings to sort
     * @return Sorted list
     */
    public static List<String> sortStrings(List<String> strings) {
        strings.sort(String::compareToIgnoreCase);
        return strings;
    }
}
