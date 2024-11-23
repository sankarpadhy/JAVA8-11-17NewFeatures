package com.java.features.java8.generics;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Demonstrates improved type inference features in Java 8
 */
public class ImprovedTypeInference {

    /**
     * Generic method with type inference
     */
    public static <T> List<T> makeList(T... elements) {
        return Arrays.asList(elements);
    }

    /**
     * Generic class with multiple type parameters
     */
    public static class Pair<T, U> {
        private T first;
        private U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        // Factory method with improved type inference
        public static <T, U> Pair<T, U> of(T first, U second) {
            return new Pair<>(first, second);
        }

        public T getFirst() { return first; }
        public U getSecond() { return second; }
    }

    /**
     * Generic method with nested generic parameters
     */
    public static <T> List<List<T>> groupItems(List<T> items, Function<T, String> classifier) {
        return items.stream()
                .collect(Collectors.groupingBy(classifier))
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    /**
     * Method demonstrating diamond operator with anonymous inner classes
     */
    public static <T> List<T> createSpecialList() {
        // Diamond operator with anonymous inner class
        return new ArrayList<>() {
            @Override
            public boolean add(T e) {
                System.out.println("Adding: " + e);
                return super.add(e);
            }
        };
    }

    /**
     * Generic method chain with type inference
     */
    public static <T, U, R> Function<T, R> compose(Function<T, U> first, Function<U, R> second) {
        return first.andThen(second);
    }

    /**
     * Nested generic type with inference
     */
    public static class Container<T> {
        private T value;

        public Container(T value) {
            this.value = value;
        }

        // Method with type inference in return type
        public <U> Container<U> map(Function<T, U> mapper) {
            return new Container<>(mapper.apply(value));
        }

        public T getValue() {
            return value;
        }
    }

    /**
     * Example usage demonstrating improved type inference
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
                String::substring // Using first letter as classifier
        );

        // Diamond operator with anonymous inner class
        List<String> specialList = createSpecialList();
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

        // Using var with generic types (Java 10+)
        var result = new ArrayList<Map<String, List<Integer>>>();
    }

    /**
     * Example of type inference with recursive generic types
     */
    public static class Tree<T> {
        private T value;
        private List<Tree<T>> children;

        public Tree(T value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        public void addChild(T value) {
            children.add(new Tree<>(value));
        }

        public List<Tree<T>> getChildren() {
            return children;
        }
    }

    /**
     * Example of type inference with intersection types
     */
    public static <T extends Comparable<T> & Cloneable> T max(List<T> list) {
        return list.stream()
                .max(Comparable::compareTo)
                .orElseThrow();
    }
}
