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

    // Type annotation for class type parameter
    public static class Container<@Nonnull T> {
        private T value;

        public Container(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }

    // Type annotation on method type parameter
    public static <@Nonnull T> void processValue(T value) {
        System.out.println("Processing value: " + value);
    }

    // Type annotation on constructor
    public static class SafeObject {
        private final String value;

        public SafeObject(@Nonnull String value) {
            this.value = value;
        }
    }

    // Type annotation on type cast
    public static String convertObject(Object obj) {
        return (@Nonnull String) obj;
    }

    // Type annotation on implements clause
    public static class NumberList implements @Nonnull List<@Nonnull Number> {
        // Implementation details omitted for brevity
        // This is just to demonstrate type annotation usage
    }

    // Type annotation on throws clause
    public static void riskyOperation() throws @Nonnull Exception {
        throw new Exception("Operation failed");
    }

    // Type annotation on array type
    public static @Nonnull String @Nonnull [] createArray() {
        return new String[]{"Hello", "World"};
    }

    // Type annotation on receiver parameter
    public void receiverExample(@Nonnull AnnotationExamples this) {
        System.out.println("Method with annotated receiver parameter");
    }

    // Type annotation on wildcard bound
    public static void processList(List<? extends @Nonnull Number> numbers) {
        numbers.forEach(System.out::println);
    }

    // Combining type annotations with generics
    public static class TypeAnnotatedContainer<@Nonnull T extends @Nonnull Object> {
        private final List<@Nonnull T> elements;

        public TypeAnnotatedContainer(List<@Nonnull T> elements) {
            this.elements = elements;
        }

        public void addIfNotNull(@Nullable T element) {
            if (element != null) {
                elements.add(element);
            }
        }

        public @Nonnull List<@Nonnull T> getElements() {
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
