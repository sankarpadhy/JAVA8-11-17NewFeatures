package com.java.features.java9.modules;

import com.java.features.java9.modules.export.regular.PublicAPI;
import com.java.features.java9.modules.export.qualified.InternalAPI;

/**
 * This class demonstrates the practical usage of Java Module System's export features.
 * It shows how regular and qualified exports affect code accessibility.
 * 
 * Module Export Types in Java 9+:
 * ------------------------------
 * 1. Regular Export (exports package.name)
 *    - Makes package accessible to ALL modules
 *    - Used for public APIs and utilities
 *    
 * 2. Qualified Export (exports package.name to module.name)
 *    - Makes package accessible only to SPECIFIED modules
 *    - Used for internal APIs and sensitive operations
 * 
 * Key Differences:
 * --------------
 * Regular Export:
 * - Like a public library - open to everyone
 * - Cannot be restricted later without breaking compatibility
 * - Good for stable, public APIs
 * 
 * Qualified Export:
 * - Like a private API - only trusted partners get access
 * - Can control exactly which modules have access
 * - Good for internal implementations and sensitive features
 * 
 * Example Usage in module-info.java:
 * --------------------------------
 * {@code
 * module my.module {
 *     // Regular Export - Everyone can access
 *     exports com.example.public.api;
 * 
 *     // Qualified Export - Only specified modules can access
 *     exports com.example.internal.api to trusted.module1, trusted.module2;
 * }
 * }
 */
public class ModuleExportExample {
    
    /**
     * Demonstrates usage of regularly exported API.
     * This will work in any module that requires our module.
     */
    public void demonstratePublicAPI() {
        // This works because PublicAPI is in a regularly exported package
        PublicAPI publicAPI = new PublicAPI();
        System.out.println("Public API access: " + publicAPI.getPublicData());
        publicAPI.demonstratePublicOperation();
    }

    /**
     * Demonstrates usage of qualified exported API.
     * This will only work if the current module is in the qualified exports list.
     */
    public void demonstrateInternalAPI() {
        // This only works if our module is in the qualified exports list
        InternalAPI internalAPI = new InternalAPI();
        System.out.println("Internal API access: " + internalAPI.getSensitiveData());
        internalAPI.performInternalOperation();
    }

    /**
     * Main method to demonstrate the export features.
     */
    public static void main(String[] args) {
        ModuleExportExample example = new ModuleExportExample();
        
        System.out.println("=== Regular Export Demo ===");
        example.demonstratePublicAPI();
        
        System.out.println("\n=== Qualified Export Demo ===");
        example.demonstrateInternalAPI();
        
        System.out.println("\nNote: If you see compilation errors in demonstrateInternalAPI(),");
        System.out.println("it means your module doesn't have access to the qualified export.");
    }
}
