package com.java.features.java8.lambda;

import java.util.*;
import java.util.function.*;

/**
 * Demonstrates Java 8 Lambda expressions and functional interfaces.
 * This class provides examples of:
 * - Basic lambda syntax
 * - Method references
 * - Built-in functional interfaces
 * - Custom functional interfaces
 * - Lambda with collections
 * - Exception handling
 * - Variable capture
 */
public class LambdaBasics {

    /**
     * Demonstrates basic lambda syntax variations.
     * Shows different ways to write lambda expressions:
     * - No parameters
     * - Single parameter
     * - Multiple parameters
     * - Expression vs block bodies
     */
    public static void demonstrateLambdaSyntax() {
        // No parameters
        Runnable noParams = () -> System.out.println("No parameters");
        
        // Single parameter with type inference
        Consumer<String> singleParam = s -> System.out.println(s);
        
        // Multiple parameters with explicit types
        BiFunction<Integer, Integer, Integer> add = (Integer a, Integer b) -> a + b;
        
        // Block body with return statement
        Function<Integer, Integer> factorial = n -> {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
            return result;
        };

        // Execute the lambdas
        noParams.run();
        singleParam.accept("Hello Lambda!");
        System.out.println("Sum: " + add.apply(5, 3));
        System.out.println("Factorial of 5: " + factorial.apply(5));
    }

    /**
     * Demonstrates method references.
     * Shows four types of method references:
     * - Static method reference
     * - Instance method reference on specific object
     * - Instance method reference on arbitrary object of particular type
     * - Constructor reference
     */
    public static void demonstrateMethodReferences() {
        // Static method reference
        Function<String, Integer> parseInt = Integer::parseInt;
        
        // Instance method reference on specific object
        String prefix = "Hello, ";
        Function<String, String> addPrefix = prefix::concat;
        
        // Instance method reference on arbitrary object
        Comparator<String> compareString = String::compareTo;
        
        // Constructor reference
        Supplier<ArrayList<String>> listFactory = ArrayList::new;

        // Use the method references
        System.out.println("Parsed integer: " + parseInt.apply("42"));
        System.out.println("With prefix: " + addPrefix.apply("World"));
        System.out.println("Compare result: " + compareString.compare("a", "b"));
        System.out.println("New list: " + listFactory.get());
    }

    /**
     * Demonstrates built-in functional interfaces from java.util.function.
     * Shows usage of:
     * - Function<T,R>
     * - Predicate<T>
     * - Consumer<T>
     * - Supplier<T>
     */
    public static void demonstrateFunctionalInterfaces() {
        // Function
        Function<String, Integer> strLength = String::length;
        System.out.println("Length: " + strLength.apply("Hello"));

        // Predicate
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));

        // Consumer
        Consumer<String> printer = System.out::println;
        printer.accept("Hello from Consumer!");

        // Supplier
        Supplier<Double> random = Math::random;
        System.out.println("Random number: " + random.get());
    }

    /**
     * Demonstrates lambda expressions with collections.
     * Shows how to use lambdas for:
     * - Sorting
     * - Filtering
     * - Mapping
     * - Iteration
     */
    public static void demonstrateCollectionOperations() {
        List<String> words = Arrays.asList("banana", "apple", "pear");

        // Sorting
        words.sort((a, b) -> a.compareTo(b));
        System.out.println("Sorted: " + words);

        // Filtering
        List<String> longWords = new ArrayList<>();
        words.forEach(word -> {
            if (word.length() > 5) {
                longWords.add(word);
            }
        });
        System.out.println("Long words: " + longWords);
    }

    /**
     * Demonstrates function composition and chaining.
     * Shows how to:
     * - Compose functions
     * - Chain predicates
     * - Chain consumers
     */
    public static void demonstrateFunctionComposition() {
        // Function composition
        Function<Integer, Integer> times2 = x -> x * 2;
        Function<Integer, Integer> plus3 = x -> x + 3;
        Function<Integer, Integer> composed = times2.andThen(plus3);
        System.out.println("Composed result: " + composed.apply(5));

        // Predicate chaining
        Predicate<Integer> isPositive = x -> x > 0;
        Predicate<Integer> isEven = x -> x % 2 == 0;
        Predicate<Integer> isPositiveAndEven = isPositive.and(isEven);
        System.out.println("Is 4 positive and even? " + isPositiveAndEven.test(4));
    }

    /**
     * Demonstrates exception handling in lambda expressions.
     * Shows how to:
     * - Handle checked exceptions
     * - Handle runtime exceptions
     * - Create exception-safe wrappers
     */
    public static void demonstrateExceptionHandling() {
        // Safe integer parsing
        Function<String, Integer> safeParseInt = str -> {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                return 0;
            }
        };

        System.out.println("Parsed valid: " + safeParseInt.apply("42"));
        System.out.println("Parsed invalid: " + safeParseInt.apply("invalid"));
    }

    /**
     * Demonstrates variable capture in lambda expressions.
     * Shows:
     * - Effectively final variables
     * - Instance variable access
     * - Static variable access
     */
    public static void demonstrateVariableCapture() {
        final int multiplier = 2;
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        
        List<Integer> results = new ArrayList<>();
        numbers.forEach(n -> results.add(n * multiplier));
        
        System.out.println("Results: " + results);
    }

    /**
     * Custom functional interface for demonstration.
     * Shows how to:
     * - Define custom functional interfaces
     * - Use default methods
     * - Use static methods
     */
    @FunctionalInterface
    public interface MathOperation {
        double calculate(double x, double y);
        
        default double calculateAbs(double x, double y) {
            return Math.abs(calculate(x, y));
        }
        
        static MathOperation add() {
            return (x, y) -> x + y;
        }
    }

    /**
     * Demonstrates custom functional interfaces.
     * Shows how to:
     * - Implement custom functional interfaces
     * - Use default methods
     * - Use static methods
     */
    public static void demonstrateCustomFunctionalInterface() {
        // Basic implementation
        MathOperation multiply = (x, y) -> x * y;
        System.out.println("Multiply: " + multiply.calculate(4, 5));

        // Using default method
        System.out.println("Absolute diff: " + multiply.calculateAbs(3, -4));

        // Using static factory method
        MathOperation adder = MathOperation.add();
        System.out.println("Sum: " + adder.calculate(5, 3));
    }

    /**
     * Main method to demonstrate all lambda features.
     */
    public static void main(String[] args) {
        System.out.println("=== Basic Lambda Syntax ===");
        demonstrateLambdaSyntax();

        System.out.println("\n=== Method References ===");
        demonstrateMethodReferences();

        System.out.println("\n=== Functional Interfaces ===");
        demonstrateFunctionalInterfaces();

        System.out.println("\n=== Collection Operations ===");
        demonstrateCollectionOperations();

        System.out.println("\n=== Function Composition ===");
        demonstrateFunctionComposition();

        System.out.println("\n=== Exception Handling ===");
        demonstrateExceptionHandling();

        System.out.println("\n=== Variable Capture ===");
        demonstrateVariableCapture();

        System.out.println("\n=== Custom Functional Interface ===");
        demonstrateCustomFunctionalInterface();
    }
}
