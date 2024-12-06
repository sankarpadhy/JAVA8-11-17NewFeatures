package com.java.features.java9.diamond;

/**
 * Demonstrates the enhanced diamond operator with anonymous classes in Java 9
 */
public class DiamondOperatorEnhancement {

    // Generic abstract class
    abstract static class GenericHandler<T> {
        abstract void handle(T item);
    }

    public static void main(String[] args) {
        // Pre-Java 9: Required explicit type declaration
        GenericHandler<String> preJava9Handler = new GenericHandler<String>() {
            @Override
            void handle(String item) {
                System.out.println("Pre-Java 9 handling: " + item);
            }
        };

        // Java 9: Diamond operator with anonymous class
        GenericHandler<String> java9Handler = new GenericHandler<>() {
            @Override
            void handle(String item) {
                System.out.println("Java 9 handling: " + item);
            }
        };

        // Using the handlers
        preJava9Handler.handle("Hello");
        java9Handler.handle("Java 9");

        // More complex example with multiple type parameters
        MultipleTypeHandler<String, Integer> handler = new MultipleTypeHandler<>() {
            @Override
            void handle(String first, Integer second) {
                System.out.println("Handling " + first + " and " + second);
            }
        };

        handler.handle("Test", 9);
    }

    // Example with multiple type parameters
    abstract static class MultipleTypeHandler<T, U> {
        abstract void handle(T first, U second);
    }
}
