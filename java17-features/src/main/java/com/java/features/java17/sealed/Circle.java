/**
 * Circle implementation of the sealed Shape class.
 * Demonstrates a permitted subclass in the sealed class hierarchy.
 */
public final class Circle extends Shape {
    private final double radius;

    /**
     * Constructs a Circle with the specified radius.
     * 
     * Example:
     * ```java
     * Circle circle = new Circle(5.0);
     * System.out.println(circle.getRadius()); // Output: 5.0
     * ```
     * 
     * @param radius the radius of the circle
     * @throws IllegalArgumentException if radius is negative
     */
    public Circle(double radius) {
        super("Circle");
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative");
        }
        this.radius = radius;
    }

    /**
     * Gets the radius of the circle.
     * 
     * Example:
     * ```java
     * Circle circle = new Circle(5.0);
     * double radius = circle.getRadius(); // Returns: 5.0
     * ```
     * 
     * @return the radius of the circle
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Calculates the area of the circle using π * r².
     * 
     * Example:
     * ```java
     * Circle circle = new Circle(5.0);
     * double area = circle.getArea(); // Returns: 78.54 (π * 5²)
     * ```
     * 
     * @return the area of the circle
     */
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    /**
     * Calculates the perimeter (circumference) of the circle using 2 * π * r.
     * 
     * Example:
     * ```java
     * Circle circle = new Circle(5.0);
     * double perimeter = circle.getPerimeter(); // Returns: 31.42 (2 * π * 5)
     * ```
     * 
     * @return the perimeter of the circle
     */
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    /**
     * Returns a string representation of the circle.
     * 
     * Example:
     * ```java
     * Circle circle = new Circle(5.0);
     * System.out.println(circle); // Output: Circle[radius=5.0]
     * ```
     * 
     * @return string representation of the circle
     */
    @Override
    public String toString() {
        return String.format("Circle[radius=%.1f]", radius);
    }
}
