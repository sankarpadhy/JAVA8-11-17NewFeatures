package com.java.features.java9.diamond;

/**
 * Demonstrates the Enhanced Diamond Operator in Java 9.
 * 
 * What's New in Java 9:
 * -------------------
 * The diamond operator (<>) can now be used with anonymous inner classes.
 * This was not possible in Java 7/8, where the diamond operator was first introduced.
 * 
 * Evolution of Diamond Operator:
 * ---------------------------
 * Java 7 (Original):
 * {@code
 *     // Before Java 7
 *     List<String> list = new ArrayList<String>();
 *     
 *     // Java 7 - Diamond operator introduced
 *     List<String> list = new ArrayList<>();
 * }
 * 
 * Java 9 Enhancement:
 * -----------------
 * Now works with anonymous inner classes:
 * {@code
 *     // This was invalid in Java 7/8, valid in Java 9+
 *     Handler<String> handler = new Handler<>() {
 *         @Override
 *         public void handle(String value) { }
 *     };
 * }
 * 
 * Benefits:
 * --------
 * 1. Less code verbosity
 * 2. Better type inference
 * 3. Improved readability
 * 4. Consistent with other diamond operator uses
 */
public class DiamondOperatorExample {

    /**
     * Generic interface to demonstrate diamond operator with anonymous classes
     */
    interface Handler<T> {
        void handle(T value);
    }

    /**
     * Generic class to demonstrate constructor usage
     */
    class Box<T> {
        private T value;

        public Box(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }

    /**
     * Demonstrates diamond operator usage before Java 9
     */
    public void demonstratePreJava9() {
        // Basic diamond operator (Java 7 feature)
        Box<String> stringBox = new Box<>("Hello");

        // Without diamond operator - verbose
        Handler<String> handlerVerbose = new Handler<String>() {
            @Override
            public void handle(String value) {
                System.out.println("Handling: " + value);
            }
        };

        handlerVerbose.handle("Test");
    }

    /**
     * Demonstrates enhanced diamond operator in Java 9
     */
    public void demonstrateJava9Diamond() {
        // Diamond operator with anonymous class (New in Java 9)
        Handler<String> handler = new Handler<>() {
            @Override
            public void handle(String value) {
                System.out.println("Java 9 Style Handling: " + value);
            }
        };

        // Using the handler
        handler.handle("Hello Java 9");

        // Another example with different type
        Handler<Integer> intHandler = new Handler<>() {
            @Override
            public void handle(Integer value) {
                System.out.println("Processing number: " + value);
            }
        };

        intHandler.handle(42);
    }

    /**
     * Demonstrates practical use cases of the enhanced diamond operator
     */
    public void demonstratePracticalUseCases() {
        // Example 1: Event handler with generics
        EventProcessor<String> processor = new EventProcessor<>() {
            @Override
            public void process(String event) {
                System.out.println("Processing event: " + event);
            }
        };

        // Example 2: Custom comparator
        Comparator<String> comparator = new Comparator<>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        };
    }

    /**
     * Generic interface for event processing
     */
    interface EventProcessor<E> {
        void process(E event);
    }

    /**
     * Generic interface for comparison
     */
    interface Comparator<T> {
        int compare(T o1, T o2);
    }

    public static void main(String[] args) {
        DiamondOperatorExample example = new DiamondOperatorExample();

        System.out.println("=== Pre-Java 9 Style ===");
        example.demonstratePreJava9();

        System.out.println("\n=== Java 9 Enhanced Diamond Operator ===");
        example.demonstrateJava9Diamond();

        System.out.println("\n=== Practical Use Cases ===");
        example.demonstratePracticalUseCases();
    }
}
