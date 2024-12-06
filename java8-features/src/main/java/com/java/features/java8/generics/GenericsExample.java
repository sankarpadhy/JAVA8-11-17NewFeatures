package com.java.features.java8.generics;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Demonstrates Java 8's improvements in generics and type inference.
 * This class showcases better type inference in method references,
 * lambda expressions, and generic method calls.
 * 
 * Sample usage:
 * ```java
 * GenericsExample example = new GenericsExample();
 * 
 * // Using type inference with method reference
 * List<String> names = example.createList(ArrayList::new);
 * 
 * // Using generic methods with lambda
 * List<Integer> numbers = Arrays.asList(1, 2, 3);
 * List<String> converted = example.transformList(numbers, n -> n.toString());
 * ```
 */
public class GenericsExample {

    /**
     * Demonstrates type inference with constructor references.
     * 
     * Sample usage:
     * ```java
     * List<String> list = createList(ArrayList::new);
     * List<Integer> numbers = createList(LinkedList::new);
     * ```
     * 
     * @param <T> The type of list elements
     * @param <L> The type of list
     * @param supplier The list constructor reference
     * @return A new list instance
     */
    public <T, L extends List<T>> L createList(Supplier<L> supplier) {
        return supplier.get();
    }

    /**
     * Demonstrates type inference with method references in transformations.
     * 
     * Sample usage:
     * ```java
     * List<Integer> numbers = Arrays.asList(1, 2, 3);
     * List<String> strings = transformList(numbers, Object::toString);
     * // Returns: ["1", "2", "3"]
     * ```
     * 
     * @param <T> The input type
     * @param <R> The result type
     * @param input The input list
     * @param function The transformation function
     * @return List of transformed elements
     */
    public <T, R> List<R> transformList(List<T> input, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T element : input) {
            result.add(function.apply(element));
        }
        return result;
    }

    /**
     * Demonstrates improved type inference with nested generics.
     * 
     * Sample usage:
     * ```java
     * Map<String, List<Integer>> map = createNestedMap();
     * map.put("numbers", Arrays.asList(1, 2, 3));
     * ```
     * 
     * @param <K> The key type
     * @param <V> The value type
     * @return A new map with nested generic type
     */
    public <K, V> Map<K, List<V>> createNestedMap() {
        return new HashMap<>();
    }

    /**
     * Generic class demonstrating type inference improvements.
     * 
     * @param <T> The type of value to wrap
     */
    public static class Wrapper<T> {
        private final T value;

        public Wrapper(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        /**
         * Demonstrates type inference in static methods.
         * 
         * Sample usage:
         * ```java
         * Wrapper<String> wrapper = Wrapper.of("test");
         * String value = wrapper.getValue(); // Returns: "test"
         * ```
         */
        public static <T> Wrapper<T> of(T value) {
            return new Wrapper<>(value);
        }
    }

    /**
     * Demonstrates type inference with intersection types.
     * 
     * Sample usage:
     * ```java
     * List<String> list = Arrays.asList("a", "b", "c");
     * List<String> result = mergeAndSort(list, Arrays.asList("d", "e"));
     * // Returns: ["a", "b", "c", "d", "e"]
     * ```
     * 
     * @param <T> The type that extends Comparable
     * @param list1 First list to merge
     * @param list2 Second list to merge
     * @return Merged and sorted list
     */
    public <T extends Comparable<? super T>> List<T> mergeAndSort(List<T> list1, List<T> list2) {
        List<T> result = new ArrayList<>(list1);
        result.addAll(list2);
        Collections.sort(result);
        return result;
    }

    /**
     * Demonstrates bounded type parameters with multiple bounds.
     * 
     * Sample usage:
     * ```java
     * class NumericValue implements Comparable<NumericValue>, Cloneable {
     *     // implementation
     * }
     * List<NumericValue> values = // ... initialize list
     * Optional<NumericValue> max = findMax(values);
     * ```
     * 
     * @param <T> Type that is both Comparable and Cloneable
     * @param list List of elements to search
     * @return Optional containing the maximum element
     */
    public <T extends Comparable<T> & Cloneable> Optional<T> findMax(List<T> list) {
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(Collections.max(list));
    }

    /**
     * Demonstrates generic method with varargs.
     * 
     * Sample usage:
     * ```java
     * List<String> result = toList("a", "b", "c");
     * // Returns: ["a", "b", "c"]
     * ```
     * 
     * @param <T> The type of elements
     * @param elements Varargs of elements
     * @return List containing all elements
     */
    @SafeVarargs
    public final <T> List<T> toList(T... elements) {
        return Arrays.asList(elements);
    }

    /**
     * Demonstrates generic wildcards with type bounds.
     * 
     * Sample usage:
     * ```java
     * List<Integer> numbers = Arrays.asList(1, 2, 3);
     * List<Double> doubles = Arrays.asList(1.0, 2.0, 3.0);
     * double sum1 = sumNumbers(numbers); // Returns: 6.0
     * double sum2 = sumNumbers(doubles); // Returns: 6.0
     * ```
     * 
     * @param numbers List of numbers
     * @return Sum of all numbers
     */
    public double sumNumbers(List<? extends Number> numbers) {
        double sum = 0.0;
        for (Number number : numbers) {
            sum += number.doubleValue();
        }
        return sum;
    }

    /**
     * Demonstrates generic method with type inference in return type.
     * 
     * Sample usage:
     * ```java
     * Map<String, Integer> map = new HashMap<>();
     * map.put("one", 1);
     * map.put("two", 2);
     * List<Map.Entry<String, Integer>> entries = getEntries(map);
     * ```
     * 
     * @param <K> Key type
     * @param <V> Value type
     * @param map Input map
     * @return List of map entries
     */
    public <K, V> List<Map.Entry<K, V>> getEntries(Map<K, V> map) {
        return new ArrayList<>(map.entrySet());
    }
}
