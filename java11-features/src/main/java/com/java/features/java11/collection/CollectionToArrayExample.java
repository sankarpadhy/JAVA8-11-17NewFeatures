package com.java.features.java11.collection;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Demonstrates the new Collection.toArray(IntFunction) method introduced in Java 11.
 * This method provides a more concise way to convert collections to arrays.
 */
public class CollectionToArrayExample {

    /**
     * Demonstrates converting List to array using the new toArray method.
     * 
     * Example:
     * ```java
     * List<String> fruits = List.of("apple", "banana", "cherry");
     * String[] array = demonstrateListToArray(fruits);
     * // Output:
     * // Original list: [apple, banana, cherry]
     * // Converted array: [apple, banana, cherry]
     * ```
     * 
     * @param list the list to convert
     * @return array containing the list elements
     */
    public String[] demonstrateListToArray(List<String> list) {
        System.out.println("Original list: " + list);
        
        // New way in Java 11
        String[] array = list.toArray(String[]::new);
        System.out.println("Converted array: " + Arrays.toString(array));
        
        return array;
    }

    /**
     * Demonstrates converting Set to array using the new toArray method.
     * 
     * Example:
     * ```java
     * Set<Integer> numbers = Set.of(1, 2, 3, 4, 5);
     * Integer[] array = demonstrateSetToArray(numbers);
     * // Output:
     * // Original set: [1, 2, 3, 4, 5]
     * // Converted array: [1, 2, 3, 4, 5]
     * ```
     * 
     * @param set the set to convert
     * @return array containing the set elements
     */
    public Integer[] demonstrateSetToArray(Set<Integer> set) {
        System.out.println("Original set: " + set);
        
        // New way in Java 11
        Integer[] array = set.toArray(Integer[]::new);
        System.out.println("Converted array: " + Arrays.toString(array));
        
        return array;
    }

    /**
     * Demonstrates converting List to array with custom object type.
     * 
     * Example:
     * ```java
     * List<Person> people = List.of(
     *     new Person("John", 25),
     *     new Person("Jane", 30)
     * );
     * Person[] array = demonstrateCustomObjectToArray(people);
     * // Output:
     * // Original list: [Person{name=John, age=25}, Person{name=Jane, age=30}]
     * // Converted array: [Person{name=John, age=25}, Person{name=Jane, age=30}]
     * ```
     * 
     * @param list the list of custom objects to convert
     * @return array containing the custom objects
     */
    public Person[] demonstrateCustomObjectToArray(List<Person> list) {
        System.out.println("Original list: " + list);
        
        // New way in Java 11
        Person[] array = list.toArray(Person[]::new);
        System.out.println("Converted array: " + Arrays.toString(array));
        
        return array;
    }

    // Example custom class
    public static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{name=" + name + ", age=" + age + "}";
        }
    }

    public static void main(String[] args) {
        CollectionToArrayExample demo = new CollectionToArrayExample();

        System.out.println("=== List to Array Demo ===");
        List<String> fruits = List.of("apple", "banana", "cherry");
        demo.demonstrateListToArray(fruits);

        System.out.println("\n=== Set to Array Demo ===");
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5);
        demo.demonstrateSetToArray(numbers);

        System.out.println("\n=== Custom Object to Array Demo ===");
        List<Person> people = List.of(
            new Person("John", 25),
            new Person("Jane", 30)
        );
        demo.demonstrateCustomObjectToArray(people);
    }
}
