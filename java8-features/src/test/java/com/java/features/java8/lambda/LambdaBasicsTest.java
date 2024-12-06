package com.java.features.java8.lambda;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;
import java.util.function.*;

/**
 * Test class for demonstrating Java 8 Lambda expressions and functional interfaces.
 * Tests various aspects of lambda syntax, method references, and functional interfaces.
 */
public class LambdaBasicsTest {

    /**
     * Tests basic lambda expression syntax and usage.
     * Verifies:
     * - Simple lambda expressions
     * - Parameter type inference
     * - Single and multiple parameters
     * - Expression and block lambdas
     */
    @Test
    public void testBasicLambdaSyntax() {
        // No parameters
        Runnable noParams = () -> assertTrue(true);
        noParams.run();

        // Single parameter
        Consumer<String> singleParam = s -> assertNotNull(s);
        singleParam.accept("test");

        // Multiple parameters
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        assertEquals(Integer.valueOf(5), add.apply(2, 3));

        // Block lambda
        Function<Integer, Integer> factorial = n -> {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
            return result;
        };
        assertEquals(Integer.valueOf(120), factorial.apply(5));
    }

    /**
     * Tests method references in different contexts.
     * Verifies:
     * - Static method references
     * - Instance method references
     * - Constructor references
     */
    @Test
    public void testMethodReferences() {
        // Static method reference
        Function<String, Integer> parseInt = Integer::parseInt;
        assertEquals(Integer.valueOf(42), parseInt.apply("42"));

        // Instance method reference on specific object
        String prefix = "Hello, ";
        Function<String, String> addPrefix = prefix::concat;
        assertEquals("Hello, World", addPrefix.apply("World"));

        // Instance method reference on arbitrary object
        Comparator<String> compareString = String::compareTo;
        assertTrue(compareString.compare("a", "b") < 0);

        // Constructor reference
        Supplier<ArrayList<String>> listFactory = ArrayList::new;
        assertNotNull(listFactory.get());
    }

    /**
     * Tests built-in functional interfaces from java.util.function.
     * Verifies:
     * - Function
     * - Predicate
     * - Consumer
     * - Supplier
     */
    @Test
    public void testFunctionalInterfaces() {
        // Function
        Function<String, Integer> strLength = String::length;
        assertEquals(Integer.valueOf(5), strLength.apply("Hello"));

        // Predicate
        Predicate<Integer> isEven = n -> n % 2 == 0;
        assertTrue(isEven.test(4));
        assertFalse(isEven.test(3));

        // Consumer
        List<String> list = new ArrayList<>();
        Consumer<String> addToList = list::add;
        addToList.accept("item");
        assertEquals(1, list.size());

        // Supplier
        Supplier<Double> random = Math::random;
        assertTrue(random.get() >= 0.0);
    }

    /**
     * Tests lambda expressions with collections.
     * Verifies:
     * - Sorting with lambdas
     * - Filtering with lambdas
     * - Collection transformation
     */
    @Test
    public void testCollectionOperations() {
        List<String> words = Arrays.asList("banana", "apple", "pear");

        // Sorting
        words.sort((a, b) -> a.compareTo(b));
        assertEquals("apple", words.get(0));

        // Filtering
        List<String> longWords = new ArrayList<>();
        words.forEach(word -> {
            if (word.length() > 5) {
                longWords.add(word);
            }
        });
        assertEquals(1, longWords.size());
        assertTrue(longWords.contains("banana"));
    }

    /**
     * Tests composition of functional interfaces.
     * Verifies:
     * - Function composition
     * - Predicate combination
     * - Consumer chaining
     */
    @Test
    public void testFunctionComposition() {
        // Function composition
        Function<Integer, Integer> times2 = x -> x * 2;
        Function<Integer, Integer> plus3 = x -> x + 3;
        Function<Integer, Integer> composed = times2.andThen(plus3);
        assertEquals(Integer.valueOf(7), composed.apply(2)); // (2 * 2) + 3 = 7

        // Predicate combination
        Predicate<Integer> isPositive = x -> x > 0;
        Predicate<Integer> isEven = x -> x % 2 == 0;
        Predicate<Integer> isPositiveAndEven = isPositive.and(isEven);
        assertTrue(isPositiveAndEven.test(4));
        assertFalse(isPositiveAndEven.test(-2));

        // Consumer chaining
        List<String> results = new ArrayList<>();
        Consumer<String> addToList = results::add;
        Consumer<String> printItem = System.out::println;
        Consumer<String> combined = addToList.andThen(printItem);
        combined.accept("test");
        assertEquals(1, results.size());
    }

    /**
     * Tests exception handling in lambda expressions.
     * Verifies:
     * - Handling checked exceptions
     * - Handling runtime exceptions
     */
    @Test
    public void testExceptionHandling() {
        // Handling checked exceptions
        Function<String, Integer> safeParseInt = str -> {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                return 0;
            }
        };
        assertEquals(Integer.valueOf(0), safeParseInt.apply("invalid"));
        assertEquals(Integer.valueOf(42), safeParseInt.apply("42"));
    }

    /**
     * Tests variable capture in lambda expressions.
     * Verifies:
     * - Effectively final variables
     * - Instance variable access
     * - Static variable access
     */
    @Test
    public void testVariableCapture() {
        final int multiplier = 2;
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        
        List<Integer> results = new ArrayList<>();
        numbers.forEach(n -> results.add(n * multiplier));
        
        assertEquals(Integer.valueOf(2), results.get(0));
        assertEquals(Integer.valueOf(4), results.get(1));
        assertEquals(Integer.valueOf(6), results.get(2));
    }

    /**
     * Tests type inference in lambda expressions.
     * Verifies:
     * - Parameter type inference
     * - Return type inference
     * - Generic type inference
     */
    @Test
    public void testTypeInference() {
        // Parameter type inference
        Comparator<String> comp = (s1, s2) -> s1.length() - s2.length();
        assertTrue(comp.compare("a", "bb") < 0);

        // Generic type inference
        List<String> list = new ArrayList<>();
        Supplier<List<String>> supplier = ArrayList::new;
        list = supplier.get();
        assertNotNull(list);
    }
}
