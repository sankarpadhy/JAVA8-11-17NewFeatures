package com.java.features.java17.sealed;

/**
 * Demonstrates Sealed Classes feature introduced in Java 17.
 * A sealed class restricts which other classes may extend it.
 * This creates a closed hierarchy of types, improving type safety and pattern matching.
 *
 * Key features demonstrated:
 * - Sealed class declaration with 'permits' clause
 * - Restricted inheritance hierarchy
 * - Integration with pattern matching
 * - Compile-time verification of permitted subtypes
 * - Enhanced type safety and maintainability
 *
 * Example usage with pattern matching:
 * ```java
 * Shape shape = new Circle("circle", 5.0);
 * String description = switch (shape) {
 *     case Circle c -> "Circle with radius " + c.getRadius();
 *     case Rectangle r -> "Rectangle " + r.getWidth() + "x" + r.getHeight();
 *     case Triangle t -> "Triangle with base " + t.getBase();
 * }; // No default needed - compiler knows all possible subtypes
 * ```
 */
public sealed abstract class Shape 
    permits Circle, Rectangle, Triangle {
    
    private final String name;

    protected Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Calculates the area of the shape.
     * Each permitted subclass must implement this method.
     * 
     * Example:
     * ```java
     * Shape circle = new Circle(5.0);
     * double area = circle.getArea(); // Returns: 78.54
     * 
     * Shape rectangle = new Rectangle(4.0, 6.0);
     * double area = rectangle.getArea(); // Returns: 24.0
     * ```
     */
    public abstract double getArea();

    /**
     * Calculates the perimeter of the shape.
     * Each permitted subclass must implement this method.
     * 
     * Example:
     * ```java
     * Shape circle = new Circle(5.0);
     * double perimeter = circle.getPerimeter(); // Returns: 31.42
     * 
     * Shape rectangle = new Rectangle(4.0, 6.0);
     * double perimeter = rectangle.getPerimeter(); // Returns: 20.0
     * ```
     */
    public abstract double getPerimeter();

    /**
     * Returns a string representation of the shape.
     * Each permitted subclass should override this method.
     * 
     * Example:
     * ```java
     * Shape circle = new Circle(5.0);
     * System.out.println(circle); // Output: Circle[radius=5.0]
     * 
     * Shape rectangle = new Rectangle(4.0, 6.0);
     * System.out.println(rectangle); // Output: Rectangle[width=4.0, height=6.0]
     * ```
     */
    @Override
    public abstract String toString();
}
