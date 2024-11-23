package com.java.features.java17.pattern;

import com.java.features.java17.sealed.Shape;
import com.java.features.java17.sealed.Circle;
import com.java.features.java17.sealed.Rectangle;
import com.java.features.java17.sealed.Triangle;

/**
 * Demonstrates Pattern Matching and Switch Expressions in Java 17
 */
public class PatternMatchingExample {

    /**
     * Demonstrates pattern matching with instanceof
     */
    public static String getShapeDescription(Shape shape) {
        if (shape instanceof Circle c) {
            return "Circle with radius " + c.getRadius();
        } else if (shape instanceof Rectangle r) {
            return "Rectangle " + r.getWidth() + " x " + r.getHeight();
        } else if (shape instanceof Triangle t) {
            return "Triangle with base " + t.getBase() + " and height " + t.getHeight();
        }
        return "Unknown shape";
    }

    /**
     * Demonstrates switch expression with pattern matching
     */
    public static String getShapeType(Shape shape) {
        return switch (shape) {
            case Circle c -> "This is a circle";
            case Rectangle r -> "This is a rectangle";
            case Triangle t -> "This is a triangle";
            // No default needed as Shape is sealed
        };
    }

    /**
     * Demonstrates switch expression with multiple cases and guards
     */
    public static String classifyShape(Shape shape) {
        return switch (shape) {
            case Circle c && c.getArea() > 100 -> "Large circle";
            case Circle c -> "Small circle";
            case Rectangle r && r.getWidth() == r.getHeight() -> "Square";
            case Rectangle r -> "Rectangle";
            case Triangle t && t.getBase() == t.getHeight() -> "Equilateral triangle";
            case Triangle t -> "Triangle";
        };
    }

    /**
     * Demonstrates pattern matching with null check
     */
    public static String describeShape(Shape shape) {
        return switch (shape) {
            case null -> "No shape provided";
            case Circle c -> String.format("Circle with area %.2f", c.getArea());
            case Rectangle r -> String.format("Rectangle with area %.2f", r.getArea());
            case Triangle t -> String.format("Triangle with area %.2f", t.getArea());
        };
    }

    /**
     * Demonstrates yield in switch expression
     */
    public static double getShapeMetric(Shape shape) {
        return switch (shape) {
            case Circle c -> {
                double area = c.getArea();
                double circumference = 2 * Math.PI * c.getRadius();
                yield area / circumference;
            }
            case Rectangle r -> {
                double area = r.getArea();
                double perimeter = 2 * (r.getWidth() + r.getHeight());
                yield area / perimeter;
            }
            case Triangle t -> {
                double area = t.getArea();
                double perimeter = t.getBase() * 3; // Assuming equilateral
                yield area / perimeter;
            }
        };
    }
}
