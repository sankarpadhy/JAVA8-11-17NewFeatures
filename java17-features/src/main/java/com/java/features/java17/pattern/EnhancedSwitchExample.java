package com.java.features.java17.pattern;

import com.java.features.java17.sealed.Circle;
import com.java.features.java17.sealed.Rectangle;
import com.java.features.java17.sealed.Shape;
import com.java.features.java17.sealed.Triangle;

/**
 * Demonstrates enhanced switch expressions with pattern matching in Java 17.
 * Shows how switch expressions can now handle multiple patterns, null values,
 * and guard patterns.
 */
public class EnhancedSwitchExample {

    /**
     * Demonstrates type pattern matching in switch
     */
    public static String formatValue(Object obj) {
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
     * Demonstrates pattern matching with guards in switch
     */
    public static String classifyNumber(Number number) {
        return switch (number) {
            case null -> "Invalid number: null";
            case Integer i && i < 0 -> "Negative integer: " + i;
            case Integer i && i == 0 -> "Zero";
            case Integer i && i > 0 -> "Positive integer: " + i;
            case Double d && d.isNaN() -> "Not a number";
            case Double d && d.isInfinite() -> "Infinite";
            case Double d && d < 0 -> "Negative double: " + String.format("%.2f", d);
            case Double d -> "Positive double: " + String.format("%.2f", d);
            default -> "Other number type: " + number.getClass().getSimpleName();
        };
    }

    /**
     * Demonstrates combining sealed types with pattern matching in switch
     */
    public static String processShape(Shape shape) {
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
     * Demonstrates pattern matching with strings in switch
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
