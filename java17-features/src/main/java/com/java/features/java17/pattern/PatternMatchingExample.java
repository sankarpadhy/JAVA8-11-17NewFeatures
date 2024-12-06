/**
 * Demonstrates Pattern Matching features introduced in Java 17.
 * Includes examples of type patterns, guarded patterns, and pattern matching in switch expressions.
 */
public class PatternMatchingExample {

    /**
     * Demonstrates instanceof pattern matching.
     * 
     * Example:
     * ```java
     * Object str = "Hello";
     * Object num = 42;
     * 
     * demonstrateInstanceOf(str); // Output: String length: 5
     * demonstrateInstanceOf(num); // Output: Integer value: 42
     * demonstrateInstanceOf(null); // Output: Object is null
     * ```
     */
    public void demonstrateInstanceOf(Object obj) {
        if (obj instanceof String s) {
            System.out.println("String length: " + s.length());
        } else if (obj instanceof Integer i) {
            System.out.println("Integer value: " + i);
        } else {
            System.out.println("Object is null");
        }
    }

    /**
     * Demonstrates pattern matching in switch expressions.
     * 
     * Example:
     * ```java
     * Object[] inputs = {"Hello", 42, 3.14, null};
     * for (Object input : inputs) {
     *     String result = demonstratePatternSwitch(input);
     *     System.out.println(result);
     * }
     * // Output:
     * // String value: Hello (length: 5)
     * // Integer value: 42
     * // Double value: 3.14
     * // Input is null
     * ```
     */
    public String demonstratePatternSwitch(Object obj) {
        return switch (obj) {
            case String s -> "String value: " + s + " (length: " + s.length() + ")";
            case Integer i -> "Integer value: " + i;
            case Double d -> "Double value: " + d;
            case null -> "Input is null";
            default -> "Unknown input type";
        };
    }

    /**
     * Demonstrates guarded patterns with type checking.
     * 
     * Example:
     * ```java
     * demonstrateGuardedPatterns("Hello"); // Output: Valid string: Hello
     * demonstrateGuardedPatterns("");      // Output: Empty string
     * demonstrateGuardedPatterns(null);    // Output: Null input
     * ```
     */
    public void demonstrateGuardedPatterns(String input) {
        if (input instanceof String s && !s.isEmpty()) {
            System.out.println("Valid string: " + s);
        } else if (input instanceof String s && s.isEmpty()) {
            System.out.println("Empty string");
        } else {
            System.out.println("Null input");
        }
    }

    /**
     * Demonstrates sealed type pattern matching.
     * 
     * Example:
     * ```java
     * Shape circle = new Circle(5.0);
     * Shape rectangle = new Rectangle(4.0, 6.0);
     * 
     * demonstrateSealedPatterns(circle);     // Output: Circle area: 78.54
     * demonstrateSealedPatterns(rectangle);  // Output: Rectangle area: 24.0
     * ```
     */
    public void demonstrateSealedPatterns(Shape shape) {
        if (shape instanceof Circle c) {
            System.out.println("Circle area: " + c.getArea());
        } else if (shape instanceof Rectangle r) {
            System.out.println("Rectangle area: " + r.getArea());
        }
    }

    /**
     * Demonstrates record pattern matching.
     * 
     * Example:
     * ```java
     * Point p1 = new Point(0, 0);
     * Point p2 = new Point(3, 4);
     * 
     * demonstrateRecordPatterns(p1);  // Output: Point at origin
     * demonstrateRecordPatterns(p2);  // Output: Point at (3, 4), distance from origin: 5.0
     * ```
     */
    public void demonstrateRecordPatterns(Point point) {
        if (point instanceof Point p && p.getX() == 0 && p.getY() == 0) {
            System.out.println("Point at origin");
        } else if (point instanceof Point p) {
            double distance = Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());
            System.out.println("Point at (" + p.getX() + ", " + p.getY() + "), distance from origin: " + distance);
        }
    }
}
