package com.java.features.java17.sealed;

/**
 * Rectangle implementation of the sealed Shape class.
 * Demonstrates a permitted subclass in the sealed class hierarchy.
 */
public final class Rectangle extends Shape {
    private final double width;
    private final double height;

    /**
     * Constructs a Rectangle with the specified width and height.
     * 
     * Example:
     * ```java
     * Rectangle rectangle = new Rectangle(4.0, 6.0);
     * System.out.println(rectangle.getWidth());  // Output: 4.0
     * System.out.println(rectangle.getHeight()); // Output: 6.0
     * ```
     * 
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @throws IllegalArgumentException if width or height is negative
     */
    public Rectangle(double width, double height) {
        super("Rectangle");
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Width and height must be non-negative");
        }
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the width of the rectangle.
     * 
     * Example:
     * ```java
     * Rectangle rectangle = new Rectangle(4.0, 6.0);
     * double width = rectangle.getWidth(); // Returns: 4.0
     * ```
     * 
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets the height of the rectangle.
     * 
     * Example:
     * ```java
     * Rectangle rectangle = new Rectangle(4.0, 6.0);
     * double height = rectangle.getHeight(); // Returns: 6.0
     * ```
     * 
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Determines if the rectangle is a square.
     * 
     * Example:
     * ```java
     * Rectangle square = new Rectangle(5.0, 5.0);
     * System.out.println(square.isSquare());    // Output: true
     * 
     * Rectangle rectangle = new Rectangle(4.0, 6.0);
     * System.out.println(rectangle.isSquare()); // Output: false
     * ```
     * 
     * @return true if width equals height, false otherwise
     */
    public boolean isSquare() {
        return width == height;
    }

    /**
     * Calculates the area of the rectangle using width * height.
     * 
     * Example:
     * ```java
     * Rectangle rectangle = new Rectangle(4.0, 6.0);
     * double area = rectangle.getArea(); // Returns: 24.0 (4 * 6)
     * ```
     * 
     * @return the area of the rectangle
     */
    @Override
    public double getArea() {
        return width * height;
    }

    /**
     * Calculates the perimeter of the rectangle using 2 * (width + height).
     * 
     * Example:
     * ```java
     * Rectangle rectangle = new Rectangle(4.0, 6.0);
     * double perimeter = rectangle.getPerimeter(); // Returns: 20.0 (2 * (4 + 6))
     * ```
     * 
     * @return the perimeter of the rectangle
     */
    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    /**
     * Returns a string representation of the rectangle.
     * 
     * Example:
     * ```java
     * Rectangle rectangle = new Rectangle(4.0, 6.0);
     * System.out.println(rectangle); // Output: Rectangle[width=4.0, height=6.0]
     * ```
     * 
     * @return string representation of the rectangle
     */
    @Override
    public String toString() {
        return String.format("Rectangle[width=%.1f, height=%.1f]", width, height);
    }
}
