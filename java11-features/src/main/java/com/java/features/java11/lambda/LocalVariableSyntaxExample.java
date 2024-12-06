package com.java.features.java11.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Demonstrates Local Variable Syntax (var) for Lambda Parameters in Java 11.
 * This feature allows using 'var' in lambda parameters for better readability
 * and consistency with local variable syntax.
 */
public class LocalVariableSyntaxExample {

    /**
     * Demonstrates basic var usage in lambda parameters.
     * 
     * Example:
     * ```java
     * demonstrateBasicVarUsage();
     * // Output:
     * // Processed strings: [HELLO, WORLD]
     * // Numbers squared: [1, 4, 9, 16, 25]
     * ```
     */
    public void demonstrateBasicVarUsage() {
        // Using var in simple lambda
        List<String> strings = Arrays.asList("hello", "world");
        List<String> processed = strings.stream()
                .map((var s) -> s.toUpperCase())
                .collect(Collectors.toList());
        System.out.println("Processed strings: " + processed);

        // Using var with multiple parameters
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream()
                .map((var n) -> n * n)
                .collect(Collectors.toList());
        System.out.println("Numbers squared: " + squares);
    }

    /**
     * Demonstrates var with annotations in lambda parameters.
     * 
     * Example:
     * ```java
     * demonstrateVarWithAnnotations();
     * // Output:
     * // Filtered non-null strings: [hello, world]
     * // Processed positive numbers: [1, 2, 3]
     * ```
     */
    public void demonstrateVarWithAnnotations() {
        // Using var with @NonNull annotation
        List<String> strings = Arrays.asList("hello", null, "world");
        List<String> nonNull = strings.stream()
                .filter((@NonNull var s) -> s != null)
                .collect(Collectors.toList());
        System.out.println("Filtered non-null strings: " + nonNull);

        // Using var with @Positive annotation
        List<Integer> numbers = Arrays.asList(1, -2, 3, -4, 2);
        List<Integer> positive = numbers.stream()
                .filter((@Positive var n) -> n > 0)
                .collect(Collectors.toList());
        System.out.println("Processed positive numbers: " + positive);
    }

    /**
     * Demonstrates var in complex lambda expressions.
     * 
     * Example:
     * ```java
     * demonstrateComplexLambdas();
     * // Output:
     * // Grouped by length: {3=[foo, bar], 5=[hello, world]}
     * // Transformed map: {FOO=3, BAR=3, HELLO=5, WORLD=5}
     * ```
     */
    public void demonstrateComplexLambdas() {
        List<String> words = Arrays.asList("hello", "world", "foo", "bar");

        // Using var in groupingBy collector
        Map<Integer, List<String>> groupedByLength = words.stream()
                .collect(Collectors.groupingBy(
                        (var s) -> s.length()
                ));
        System.out.println("Grouped by length: " + groupedByLength);

        // Using var in complex transformation
        Map<String, Integer> lengthMap = words.stream()
                .collect(Collectors.toMap(
                        (var s) -> s.toUpperCase(),
                        (var s) -> s.length()
                ));
        System.out.println("Transformed map: " + lengthMap);
    }

    /**
     * Custom annotation for demonstration purposes
     */
    @interface NonNull {}

    /**
     * Custom annotation for demonstration purposes
     */
    @interface Positive {}

    public static void main(String[] args) {
        LocalVariableSyntaxExample demo = new LocalVariableSyntaxExample();

        System.out.println("=== Basic Var Usage Demo ===");
        demo.demonstrateBasicVarUsage();

        System.out.println("\n=== Var with Annotations Demo ===");
        demo.demonstrateVarWithAnnotations();

        System.out.println("\n=== Complex Lambda Demo ===");
        demo.demonstrateComplexLambdas();
    }
}
