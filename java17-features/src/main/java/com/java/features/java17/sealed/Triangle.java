package com.java.features.java17.sealed;

public final class Triangle extends Shape {
    private final double base;
    private final double height;

    public Triangle(double base, double height) {
        super("Triangle");
        this.base = base;
        this.height = height;
    }

    @Override
    public double getArea() {
        return (base * height) / 2;
    }

    public double getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }

    public boolean isEquilateral() {
        // In an equilateral triangle, height = √3/2 * base
        double expectedHeight = (Math.sqrt(3) / 2) * base;
        return Math.abs(height - expectedHeight) < 0.0001; // Using epsilon for double comparison
    }
}
