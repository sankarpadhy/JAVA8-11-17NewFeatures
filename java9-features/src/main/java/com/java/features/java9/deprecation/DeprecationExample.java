package com.java.features.java9.deprecation;

/**
 * Demonstrates the Enhanced Deprecation feature in Java 9
 */
public class DeprecationExample {

    /**
     * Old method marked for removal
     * @deprecated This method is scheduled for removal in a future version.
     *             Use {@link #newMethod()} instead.
     */
    @Deprecated(since = "9", forRemoval = true)
    public void oldMethod() {
        System.out.println("This is the old implementation");
    }

    /**
     * New replacement method
     */
    public void newMethod() {
        System.out.println("This is the new implementation");
    }

    /**
     * Shows runtime version information using new Java 9 version system
     */
    public void showRuntimeVersion() {
        Runtime.Version version = Runtime.version();
        System.out.println("Current Java Runtime Version:");
        System.out.println("Major version: " + version.major());
        System.out.println("Minor version: " + version.minor());
        System.out.println("Security version: " + version.security());
        
        // Build version information
        System.out.println("\nBuild Information:");
        System.out.println("Build number: " + version.build().orElse(-1));
        
        // Pre-release and additional version information
        version.pre().ifPresent(pre -> System.out.println("Pre-release: " + pre));
        version.optional().ifPresent(opt -> System.out.println("Optional: " + opt));
    }

    /**
     * Demonstrates version comparison
     */
    public void compareVersions() {
        Runtime.Version currentVersion = Runtime.version();
        Runtime.Version minVersion = Runtime.Version.parse("9.0.0");
        
        if (currentVersion.compareTo(minVersion) >= 0) {
            System.out.println("Running on Java 9 or higher");
        } else {
            System.out.println("Running on Java version lower than 9");
        }
    }

    public static void main(String[] args) {
        DeprecationExample example = new DeprecationExample();
        
        System.out.println("=== Enhanced Deprecation Demo ===");
        try {
            example.oldMethod(); // Will show compiler warning
        } catch (Exception e) {
            System.out.println("Method called: " + e.getMessage());
        }
        example.newMethod();
        
        System.out.println("\n=== Runtime Version Info ===");
        example.showRuntimeVersion();
        
        System.out.println("\n=== Version Comparison ===");
        example.compareVersions();
    }
}
