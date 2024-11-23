package com.java.features.java17.sealed;

/**
 * Demonstrates Sealed Classes feature introduced in Java 17
 */
public sealed class Shape 
    permits Circle, Rectangle, Triangle {
    
    private final String name;

    protected Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getArea();
}
