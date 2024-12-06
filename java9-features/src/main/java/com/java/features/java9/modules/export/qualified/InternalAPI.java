package com.java.features.java9.modules.export.qualified;

/**
 * This class demonstrates the concept of Qualified Exports in the Java Module System.
 * 
 * Qualified Export Syntax in module-info.java:
 * {@code exports com.java.features.java9.modules.export.qualified to com.trusted.module;}
 * 
 * What is a Qualified Export?
 * --------------------------
 * A qualified export makes public types in a package accessible ONLY to specifically
 * named modules. It's like having an API that's only available to trusted partners.
 * 
 * When to use Qualified Exports:
 * -----------------------------
 * 1. Internal APIs: Functionality that should only be used by specific modules
 * 2. Security-sensitive code: When you need to restrict access for security
 * 3. Implementation details: When you want to share internal workings with specific modules
 * 4. Partner-specific APIs: Features designed for specific client modules
 * 
 * Real-world Examples:
 * -------------------
 * 1. sun.security package: Only exported to specific JDK modules
 * 2. jdk.internal packages: Restricted to specific JDK modules
 * 3. Framework internal APIs: Limited to specific framework modules
 * 
 * Benefits:
 * ---------
 * 1. Enhanced Security: Strict control over who can access your code
 * 2. Better Encapsulation: Internal APIs stay truly internal
 * 3. Controlled Evolution: Changes only affect known consumers
 * 
 * Usage Example:
 * -------------
 * Only in trusted module:
 * {@code 
 *     // In com.trusted.module's module-info.java
 *     requires com.java.features.java9;
 *     
 *     // Can use InternalAPI
 *     InternalAPI api = new InternalAPI();
 * }
 * 
 * In other modules:
 * {@code 
 *     // Will NOT compile - no access to InternalAPI
 *     InternalAPI api = new InternalAPI(); // Compilation Error!
 * }
 */
public class InternalAPI {
    /**
     * Example method demonstrating restricted access.
     * Only modules specified in the qualified export can call this method.
     * 
     * @return A message indicating the restricted nature of this API
     */
    public String getSensitiveData() {
        return "This API is only accessible to trusted modules! Use this pattern for internal or sensitive operations.";
    }
    
    /**
     * Example of an internal operation that should only be available to trusted modules.
     * This demonstrates how qualified exports can protect sensitive operations.
     */
    public void performInternalOperation() {
        String sensitiveData = processSensitiveData();
        System.out.println("Performing sensitive internal operation with: " + sensitiveData);
    }

    // Private methods are still completely hidden
    private String processSensitiveData() {
        return "sensitive data";
    }
}
