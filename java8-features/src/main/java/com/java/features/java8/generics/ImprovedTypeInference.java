package com.java.features.java8.generics;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Demonstrates the improved type inference features introduced in Java 8.
 * This class showcases various scenarios where type inference has been enhanced,
 * including the diamond operator, method references, and lambda expressions.
 * 
 * Key improvements in Java 8 type inference:
 * 1. Diamond operator with anonymous inner classes
 * 2. Method reference type inference
 * 3. Lambda expression type inference
 * 4. Generic method type inference
 * 5. Nested generic type inference
 * 
 * Sample usage:
 * ```java
 * // Diamond operator
 * Map<String, List<Integer>> map = new HashMap<>();
 * 
 * // Method reference type inference
 * List<String> strings = Arrays.asList("a", "b", "c");
 * strings.stream().map(String::length);
 * 
 * // Lambda type inference
 * Function<String, Integer> func = s -> s.length();
 * ```
 */
public class ImprovedTypeInference {

    /**
     * Demonstrates type inference with varargs in generic methods.
     * Java 8 improves type inference when using varargs with generics.
     * 
     * Sample usage:
     * ```java
     * List<String> strings = makeList("a", "b", "c");
     * List<Integer> numbers = makeList(1, 2, 3);
     * 
     * // Mixed types (inferred as Object)
     * List<Object> mixed = makeList("a", 1, true);
     * ```
     * 
     * @param <T> The type of elements in the list
     * @param elements Varargs array of elements
     * @return List containing the provided elements
     */
    public static <T> List<T> makeList(T... elements) {
        return Arrays.asList(elements);
    }

    /**
     * Generic class demonstrating improved type inference with multiple type parameters.
     * Shows how Java 8 handles type inference in factory methods and constructors.
     * 
     * @param <T> Type of the first element
     * @param <U> Type of the second element
     */
    public static class Pair<T, U> {
        private T first;
        private U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        /**
         * Factory method demonstrating improved type inference in Java 8.
         * The compiler can now better infer types from the method arguments.
         * 
         * Sample usage:
         * ```java
         * // Types are inferred from arguments
         * Pair<String, Integer> pair = Pair.of("Hello", 42);
         * 
         * // Complex type inference
         * Pair<List<String>, Map<Integer, String>> complex = 
         *     Pair.of(Arrays.asList("a", "b"), new HashMap<>());
         * ```
         * 
         * @param <T> Type of first element
         * @param <U> Type of second element
         * @param first First element
         * @param second Second element
         * @return New Pair instance with inferred types
         */
        public static <T, U> Pair<T, U> of(T first, U second) {
            return new Pair<>(first, second);
        }

        public T getFirst() { return first; }
        public U getSecond() { return second; }
    }

    /**
     * Demonstrates type inference with nested generic parameters and Stream API.
     * Shows how Java 8 handles complex generic method calls with lambda expressions.
     * 
     * Sample usage:
     * ```java
     * List<String> words = Arrays.asList("apple", "banana", "cherry");
     * List<List<String>> grouped = groupItems(words, String::length);
     * // Groups: [[apple], [banana, cherry]]
     * ```
     * 
     * @param <T> Type of items to group
     * @param items List of items to group
     * @param classifier Function to determine the grouping key
     * @return List of grouped items
     */
    public static <T> List<List<T>> groupItems(List<T> items, Function<T, Integer> classifier) {
        return items.stream()
                .collect(Collectors.groupingBy(classifier))
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    /**
     * Demonstrates the diamond operator with anonymous inner classes in Java 8.
     * Prior to Java 8, the diamond operator couldn't be used with anonymous classes.
     * 
     * Sample usage:
     * ```java
     * List<String> list = createSpecialList();
     * list.add("test"); // Prints: "Adding: test"
     * ```
     * 
     * @param <T> Type of elements in the list
     * @return Special ArrayList with logging capability
     */
    public static <T> List<T> createSpecialList() {
        return new ArrayList<T>() {
            @Override
            public boolean add(T e) {
                System.out.println("Adding: " + e);
                return super.add(e);
            }
        };
    }

    /**
     * Demonstrates type inference in method chaining with function composition.
     * Shows how Java 8 maintains type safety through complex method chains.
     * 
     * Sample usage:
     * ```java
     * Function<String, Integer> lengthFunc = compose(
     *     String::toUpperCase,
     *     String::length
     * );
     * Integer result = lengthFunc.apply("hello"); // Returns: 5
     * ```
     * 
     * @param <T> Input type
     * @param <U> Intermediate type
     * @param <R> Result type
     * @param first First transformation
     * @param second Second transformation
     * @return Composed function
     */
    public static <T, U, R> Function<T, R> compose(Function<T, U> first, Function<U, R> second) {
        return first.andThen(second);
    }

    /**
     * Generic container class demonstrating type inference in method returns.
     * Shows how Java 8 handles type inference in fluent interfaces.
     * 
     * @param <T> Type of contained value
     */
    public static class Container<T> {
        private T value;

        public Container(T value) {
            this.value = value;
        }

        /**
         * Demonstrates type inference in method return types.
         * 
         * Sample usage:
         * ```java
         * Container<String> strContainer = new Container<>("Hello");
         * Container<Integer> intContainer = strContainer.map(String::length);
         * // intContainer.getValue() returns: 5
         * ```
         * 
         * @param <U> Target type
         * @param mapper Transformation function
         * @return New container with transformed value
         */
        public <U> Container<U> map(Function<T, U> mapper) {
            return new Container<>(mapper.apply(value));
        }

        public T getValue() {
            return value;
        }
    }

    /**
     * Example of type inference with recursive generic types.
     * Demonstrates how Java 8 handles complex nested generic structures.
     * 
     * @param <T> Type of values in the tree
     */
    public static class Tree<T> {
        private T value;
        private List<Tree<T>> children;

        public Tree(T value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        /**
         * Adds a child node to the tree.
         * 
         * Sample usage:
         * ```java
         * Tree<String> tree = new Tree<>("root");
         * tree.addChild("child1");
         * tree.addChild("child2");
         * ```
         */
        public void addChild(T value) {
            children.add(new Tree<>(value));
        }

        public List<Tree<T>> getChildren() {
            return children;
        }
    }

    /**
     * Demonstrates type inference with intersection types.
     * Shows how Java 8 handles complex type bounds.
     * 
     * Sample usage:
     * ```java
     * class ComparableString implements Comparable<ComparableString>, Cloneable {
     *     // implementation
     * }
     * List<ComparableString> list = // ... initialize list
     * ComparableString maxValue = max(list);
     * ```
     * 
     * @param <T> Type that must be both Comparable and Cloneable
     * @param list List of elements to find maximum from
     * @return Maximum element in the list
     * @throws RuntimeException if list is empty
     */
    public static <T extends Comparable<T> & Cloneable> T max(List<T> list) {
        return list.stream()
                .max(Comparable::compareTo)
                .orElseThrow(RuntimeException::new);
    }

    /**
     * Main method demonstrating various type inference improvements in Java 8.
     * Contains examples of all major type inference enhancements.
     */
    public static void main(String[] args) {
        // Diamond operator with constructor
        Map<String, List<Integer>> map = new HashMap<>();

        // Type inference in generic method calls
        List<String> strings = makeList("a", "b", "c");
        List<Integer> numbers = makeList(1, 2, 3);

        // Type inference with method chaining
        List<Integer> transformed = numbers.stream()
                .map(x -> x * 2)
                .collect(Collectors.toList());

        // Type inference with lambda expressions
        Function<String, Integer> lengthFunction = String::length;
        numbers = strings.stream()
                .map(lengthFunction)
                .collect(Collectors.toList());

        // Type inference with generic constructor
        Pair<String, Integer> pair = Pair.of("Hello", 42);

        // Type inference with nested generics
        List<List<String>> grouped = groupItems(
                Arrays.asList("apple", "banana", "cherry"),
                s -> s.length()
        );

        // Diamond operator with anonymous inner class
        List<String> specialList = new ArrayList<String>() {
            @Override
            public boolean add(String e) {
                System.out.println("Adding: " + e);
                return super.add(e);
            }
        };
        specialList.add("test");

        // Complex type inference with nested generics and method chaining
        Stream<List<Integer>> streamOfLists = Stream.of(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6)
        );

        List<Integer> flattened = streamOfLists
                .flatMap(List::stream)
                .collect(Collectors.toList());

        // Type inference with nested containers
        Container<String> container = new Container<>("Hello");
        Container<Integer> mapped = container.map(String::length);

        // Type inference with method references
        Function<String, String> upperCase = String::toUpperCase;
        Function<String, Integer> composed = compose(upperCase, String::length);

        // Using explicit type instead of var
        List<Map<String, List<Integer>>> result = new ArrayList<>();
    }
}
