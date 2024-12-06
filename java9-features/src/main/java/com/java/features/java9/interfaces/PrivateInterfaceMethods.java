package com.java.features.java9.interfaces;

/**
 * Demonstrates private methods in interfaces introduced in Java 9
 */
public interface PrivateInterfaceMethods {
    
    // Public abstract method
    void displayMessage(String message);
    
    // Default method using private methods
    default void displayFormattedMessage(String message) {
        String formatted = addHeader(message);
        formatted = addFooter(formatted);
        displayMessage(formatted);
    }
    
    // Private method
    private String addHeader(String message) {
        return "=== Header ===\n" + message;
    }
    
    // Private static method
    private static String addFooter(String message) {
        return message + "\n=== Footer ===";
    }
}

// Example implementation
class MessagePrinter implements PrivateInterfaceMethods {
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    public static void main(String[] args) {
        MessagePrinter printer = new MessagePrinter();
        printer.displayFormattedMessage("Hello from Java 9!");
    }
}
