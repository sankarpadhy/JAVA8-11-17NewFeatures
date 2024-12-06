package com.java.features.java17.records;

/**
 * Demonstrates a simple record in Java 17.
 * Records provide a compact way to declare classes that are transparent holders for shallowly immutable data.
 */
public record Point(int x, int y) {
    // Compact constructor
    public Point {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates cannot be negative");
        }
    }

    // Static field (allowed in records)
    public static final Point ORIGIN = new Point(0, 0);

    // Instance method
    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }

    // Static method
    public static Point of(int x, int y) {
        return new Point(x, y);
    }
}
