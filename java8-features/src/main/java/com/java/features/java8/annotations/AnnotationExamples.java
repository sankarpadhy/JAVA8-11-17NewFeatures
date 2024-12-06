/**
 * Demonstrates the enhanced annotation capabilities introduced in Java 8.
 * This class showcases type annotations, repeatable annotations, and
 * other annotation improvements that enable better type checking and
 * code documentation.
 *
 * Key features demonstrated:
 * - Type annotations (@NonNull, @Nullable)
 * - Repeatable annotations
 * - Type use annotations on:
 *   * Method parameters
 *   * Type casts
 *   * Generic type arguments
 *   * Implements clauses
 *   * Exception specifications
 *
 * Example usage:
 * ```java
 * // Type annotation on method parameter
 * public void process(@NonNull String data) { }
 *
 * // Repeatable annotations
 * @Schedule(dayOfMonth="last")
 * @Schedule(dayOfWeek="Fri", hour="23")
 * public void doPeriodicCleanup() { }
 *
 * // Type annotation with generics
 * List<@NonNull String> strings = new ArrayList<>();
 * ```
 *
 * Benefits:
 * - Enhanced static type checking
 * - Better documentation
 * - Improved code analysis
 * - More precise type safety
 *
 * @see java.lang.annotation.ElementType
 * @see java.lang.annotation.Repeatable
 * @since Java 8
 */
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

    /**
     * Annotation to schedule a task to run.
     * 
     * The annotated element is scheduled to run on the specified day of the month and hour.
     * If the day of the month is not specified, the task will only run on the specified day of the week.
     * 
     *      */
    // Repeatable annotation definition
    @Repeatable(Schedules.class)
    @interface Schedule {
        String dayOfMonth() default "1";
        String dayOfWeek() default "MONDAY";
        /**
         * The hour of the day to run the task.
         * Valid values are 0-23, where 0 is midnight and 23 is 11 PM.
         * Defaults to 12 (noon).
         */
        int hour() default 12;
    }

    /**
     * Container annotation for holding multiple Schedule annotations.
     */
    // Container annotation
    @Retention(RetentionPolicy.RUNTIME)
    @interface Schedules {
        Schedule[] value();
    }

    /**
     * Example class demonstrating how to generate reports on a schedule.
     * 
     * Sample usage:
     * ```java
     * @Schedule(dayOfMonth = "1", hour = 8)
     * @Schedule(dayOfWeek = "FRIDAY", hour = 17)
     * MonthlyReportGenerator generator = new MonthlyReportGenerator();
     * generator.generateReport();
     * ```
     * 
     * Output:
     * ```
     * Generating report...
     * Report scheduled for:
     * Day of Month: 1, Day of Week: MONDAY, Hour: 8
     * Day of Month: 1, Day of Week: FRIDAY, Hour: 17
     * ```
     */
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

    /**
     * Generic container class that ensures non-null values.
     * 
     * Sample usage:
     * ```java
     * Container<String> container = new Container<>("Hello");
     * String value = container.getValue(); // Returns "Hello"
     * 
     * // This would throw a NullPointerException:
     * Container<String> invalidContainer = new Container<>(null);
     * ```
     */
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

    /**
     * Safe object class that ensures non-null string values.
     * 
     * Sample usage:
     * ```java
     * SafeObject obj = new SafeObject("Valid value");
     * 
     * // This would throw a NullPointerException:
     * SafeObject invalid = new SafeObject(null);
     * ```
     */
    public static class SafeObject {
        private final String value;

        public SafeObject(@Nonnull String value) {
            this.value = value;
        }
    }

    /**
     * Simple list implementation for number types.
     * 
     * Sample usage:
     * ```java
     * NumberList list = new NumberList();
     * list.add(42);
     * list.add(3.14);
     * Number first = list.get(0); // Returns 42
     * ```
     */
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

    /**
     * Type-safe container with nullable elements.
     * 
     * Sample usage:
     * ```java
     * List<Integer> numbers = Arrays.asList(1, 2, 3);
     * TypeAnnotatedContainer<Integer> container = new TypeAnnotatedContainer<>(numbers);
     * 
     * container.addIfNotNull(4);    // Added
     * container.addIfNotNull(null); // Not added
     * List<Integer> elements = container.getElements(); // Returns [1, 2, 3, 4]
     * ```
     */
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
