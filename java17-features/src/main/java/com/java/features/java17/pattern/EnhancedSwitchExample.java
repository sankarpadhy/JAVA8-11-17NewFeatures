package com.java.features.java17.pattern;

import com.java.features.java17.sealed.Circle;
import com.java.features.java17.sealed.Rectangle;
import com.java.features.java17.sealed.Shape;
import com.java.features.java17.sealed.Triangle;

/**
 * Demonstrates the enhanced switch expressions and pattern matching introduced in Java 17.
 */
public class EnhancedSwitchExample {

    /**
     * Demonstrates type pattern matching in switch with null handling.
     * 
     * Example:
     * ```java
     * Object obj1 = "Hello";
     * getTypeDescription(obj1)  // Returns: "String: hello"
     * 
     * Object obj2 = 42;
     * getTypeDescription(obj2)  // Returns: "Integer: 84"
     * 
     * Object obj3 = null;
     * getTypeDescription(obj3)  // Returns: "null"
     * ```
     */
    public static String getTypeDescription(Object obj) {
        return switch (obj) {
            case null -> "null";
            case String s -> "String: " + s.toLowerCase();
            case Integer i -> "Integer: " + (i * 2);
            case Long l -> "Long: " + (l + 1);
            case Double d -> "Double: " + String.format("%.2f", d);
            case Number n -> "Number: " + n.toString();
            case int[] arr -> "Int Array of length: " + arr.length;
            default -> "Unknown type: " + obj.getClass().getSimpleName();
        };
    }

    /**
     * Demonstrates guarded patterns in switch expressions.
     * 
     * Example:
     * ```java
     * String str1 = "Hello";
     * categorizeString(str1)  // Returns: "Medium string"
     * 
     * String str2 = "Hi";
     * categorizeString(str2)  // Returns: "Short string"
     * 
     * String str3 = "Hello, World!";
     * categorizeString(str3)  // Returns: "Long string"
     * 
     * String str4 = null;
     * categorizeString(str4)  // Returns: "Empty or null"
     * ```
     */
    public static String categorizeString(String str) {
        return switch (str == null ? "" : str.strip().toLowerCase()) {
            case "" -> "Empty or null";
            case String s && s.length() < 5 -> "Short string";
            case String s && s.length() < 10 -> "Medium string";
            case String s -> "Long string";
        };
    }

    /**
     * Demonstrates sealed type pattern matching in switch.
     * 
     * Example:
     * ```java
     * Shape circle = new Circle(5.0);
     * getShapeDescription(circle)  // Returns: "Large circle with radius 5.0"
     * 
     * Shape rectangle = new Rectangle(4.0, 6.0);
     * getShapeDescription(rectangle)  // Returns: "Rectangle 4.0 x 6.0"
     * 
     * Shape triangle = new Triangle(3.0, 4.0, 5.0);
     * getShapeDescription(triangle)  // Returns: "Triangle with base 3.0 and height 4.0"
     * ```
     */
    public static String getShapeDescription(Shape shape) {
        return switch (shape) {
            case Circle c -> {
                if (c.getArea() > 100) {
                    yield "Large circle with radius " + c.getRadius();
                } else {
                    yield "Small circle with radius " + c.getRadius();
                }
            }
            case Rectangle r && r.getWidth() == r.getHeight() -> 
                "Square with side " + r.getWidth();
            case Rectangle r -> 
                "Rectangle " + r.getWidth() + "x" + r.getHeight();
            case Triangle t && t.isEquilateral() -> 
                "Equilateral triangle with base " + t.getBase();
            case Triangle t -> 
                "Triangle with base " + t.getBase() + " and height " + t.getHeight();
        };
    }

    /**
     * Demonstrates pattern matching with strings in switch.
     * 
     * Example:
     * ```java
     * String command1 = "help";
     * processCommand(command1)  // Returns: "Showing help menu..."
     * 
     * String command2 = "version";
     * processCommand(command2)  // Returns: "v1.0.0"
     * 
     * String command3 = "42";
     * processCommand(command3)  // Returns: "Numeric command: 42"
     * 
     * String command4 = null;
     * processCommand(command4)  // Returns: "Empty command"
     * ```
     */
    public static String processCommand(String command) {
        return switch (command == null ? "" : command.strip().toLowerCase()) {
            case "" -> "Empty command";
            case String s && s.startsWith("help") -> "Showing help menu...";
            case String s && s.startsWith("version") -> "v1.0.0";
            case String s && s.matches("\\d+") -> "Numeric command: " + s;
            case String s && s.length() < 3 -> "Command too short: " + s;
            case String s -> "Processing command: " + s;
        };
    }
}
