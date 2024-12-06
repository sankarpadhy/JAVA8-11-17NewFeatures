package com.java.features.java8.annotations;

import java.lang.annotation.*;
import java.util.List;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Demonstrates Repeating Annotations and Type Annotations features in Java 8
 */
public class AnnotationExamples {

    // Repeatable annotation definition
    @Repeatable(Schedules.class)
    @interface Schedule {
        String dayOfMonth() default "1";
        String dayOfWeek() default "MONDAY";
        int hour() default 12;
    }

    // Container annotation
    @Retention(RetentionPolicy.RUNTIME)
    @interface Schedules {
        Schedule[] value();
    }

    // Example of repeating annotations usage
    @Schedule(dayOfMonth = "1", hour = 8)
    @Schedule(dayOfMonth = "15", hour = 13)
    @Schedule(dayOfWeek = "FRIDAY", hour = 17)
    public static class MonthlyReportGenerator {
        public void generateReport() {
            System.out.println("Generating report...");
            // Get all Schedule annotations
            Schedule[] schedules = MonthlyReportGenerator.class.getAnnotationsByType(Schedule.class);
            System.out.println("Report scheduled for:");
            Arrays.stream(schedules).forEach(schedule -> 
                System.out.printf("Day of Month: %s, Day of Week: %s, Hour: %d%n",
                    schedule.dayOfMonth(), schedule.dayOfWeek(), schedule.hour()));
        }
    }

    // Type Annotations examples

    // Generic class with nullable type parameter
    public static class Container<T> {
        private final T value;

        public Container(@Nonnull T value) {
            this.value = value;
        }

        @Nonnull
        public T getValue() {
            return value;
        }
    }

    // Generic method with nullable parameter
    public static <T> void processValue(@Nonnull T value) {
        System.out.println("Processing value: " + value);
    }

    // Constructor with nullable parameter
    public static class SafeObject {
        private final String value;

        public SafeObject(@Nonnull String value) {
            this.value = value;
        }
    }

    // Method with nullable return type
    @Nonnull
    public static String convertObject(Object obj) {
        return (String) obj;
    }

    // Simple list implementation
    public static class NumberList {
        private final List<Number> numbers = Arrays.asList();
        
        public void add(Number n) {
            numbers.add(n);
        }
        
        public Number get(int index) {
            return numbers.get(index);
        }
    }

    // Method with throws clause
    public static void riskyOperation() throws Exception {
        throw new Exception("Operation failed");
    }

    // Array creation with nonnull elements
    @Nonnull
    public static String[] createArray() {
        return new String[]{"Hello", "World"};
    }

    // Method with nonnull parameter
    public void receiverExample() {
        System.out.println("Method with annotated receiver parameter");
    }

    // Method with generic parameter
    public static void processList(@Nonnull List<? extends Number> numbers) {
        numbers.forEach(System.out::println);
    }

    // Container with nullable elements
    public static class TypeAnnotatedContainer<T> {
        private final List<T> elements;

        public TypeAnnotatedContainer(@Nonnull List<T> elements) {
            this.elements = elements;
        }

        public void addIfNotNull(@Nullable T element) {
            if (element != null) {
                elements.add(element);
            }
        }

        @Nonnull
        public List<T> getElements() {
            return elements;
        }
    }

    // Example usage
    public static void main(String[] args) {
        // Repeating annotations example
        MonthlyReportGenerator generator = new MonthlyReportGenerator();
        generator.generateReport();

        // Type annotations example
        Container<String> container = new Container<>("Hello");
        processValue(42);
        SafeObject safeObject = new SafeObject("Safe value");
        String[] array = createArray();
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        processList(numbers);
    }
}
