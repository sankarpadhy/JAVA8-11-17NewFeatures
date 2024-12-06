package com.java.features.java9.modules.export.regular;

/**
 * This class demonstrates the concept of Regular Exports in the Java Module System.
 * 
 * Regular Export Syntax in module-info.java:
 * {@code exports com.java.features.java9.modules.export.regular;}
 * 
 * What is a Regular Export?
 * ------------------------
 * A regular export makes all public types in a package accessible to ALL other modules.
 * It's like making your API public for everyone to use.
 * 
 * When to use Regular Exports:
 * ---------------------------
 * 1. Public APIs: When you want to provide functionality for general use
 * 2. Utility Classes: Common helper functions that any module might need
 * 3. Shared Interfaces: Common contracts that multiple modules implement
 * 4. Standard Libraries: Reusable components for wide usage
 * 
 * Real-world Examples:
 * -------------------
 * 1. java.util package: Contains Collections API used by everyone
 * 2. java.io package: File operations available to all modules
 * 3. java.lang package: Core language features accessible everywhere
 * 
 * Benefits:
 * ---------
 * 1. Wide Accessibility: Any module can use your code
 * 2. API Stability: Encourages maintaining backward compatibility
 * 3. Code Reuse: Promotes sharing common functionality
 * 
 * Usage Example:
 * -------------
 * In another module:
 * {@code 
 *     requires com.java.features.java9;  // Add this in module-info.java
 *     PublicAPI api = new PublicAPI();   // Can be used directly
 * }
 */
public class PublicAPI {
    /**
     * Example method demonstrating public API access.
     * This method is accessible to any module that requires our module.
     * 
     * @return A message indicating the public nature of this API
     */
    public String getPublicData() {
        return "This API is accessible to all modules! Use this pattern for public utilities and shared interfaces.";
    }

    /**
     * Another example showing typical public API usage.
     * Even though this is public, internal implementation details remain hidden.
     */
    public void demonstratePublicOperation() {
        // Internal implementation details are still encapsulated
        String internalData = processInternalData();
        System.out.println("Public operation using " + internalData);
    }

    // Private methods are not affected by exports
    private String processInternalData() {
        return "processed data";
    }
}
