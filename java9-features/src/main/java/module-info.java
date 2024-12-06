/**
 * This module demonstrates the Java Platform Module System (JPMS) features introduced in Java 9.
 * 
 * Key Module Concepts:
 * 1. exports     - Makes packages accessible to other modules
 * 2. requires    - Declares dependencies on other modules
 * 3. opens      - Enables runtime-only access via reflection
 * 4. uses       - Indicates that this module consumes a service
 * 5. provides   - Specifies an implementation of a service
 * 6. with       - Defines the implementing class for a service
 * 
 * Module Types:
 * - Named Modules: Have module-info.java (like this one)
 * - Automatic Modules: JARs without module-info.java placed on module path
 * - Unnamed Module: Code on classpath (no module-info.java)
 */
module com.java.features.java9 {
    /**
     * REGULAR EXPORT EXAMPLE
     * ----------------------
     * exports com.java.features.java9.modules.export.regular;
     * 
     * This means:
     * - ANY module can access this package
     * - ALL public types in this package are accessible
     * - Commonly used for public APIs, utilities, and interfaces
     * 
     * Real-world example: java.util package is exported to everyone
     */

    /**
     * QUALIFIED EXPORT EXAMPLE
     * -----------------------
     * exports com.java.features.java9.modules.export.qualified to com.trusted.module;
     * 
     * This means:
     * - ONLY com.trusted.module can access this package
     * - Other modules CANNOT access this package
     * - Used for internal APIs that should only be used by specific trusted modules
     * 
     * Real-world example: sun.security package is only exported to specific JDK modules
     */

    /**
     * REQUIRES - Declaring module dependencies
     * 
     * 1. requires           - Standard dependency
     * 2. requires static    - Optional dependency (compile-time only)
     * 3. requires transitive - Re-export dependency to dependent modules
     */
    requires java.base;              // Standard module dependency
    requires jdk.incubator.httpclient; // Required for HTTP Client example
    requires java.logging;           // For logging functionality
    requires java.sql;              // For JDBC functionality

    /**
     * EXPORTS - Making packages accessible to other modules
     * 
     * 1. Regular export: All modules can access this package
     * 2. Qualified export: Only specified modules can access
     */
    exports com.java.features.java9.modules.export.regular;
    exports com.java.features.java9.modules.export.qualified to com.specific.module;

    /**
     * OPENS - Enabling reflection access
     * 
     * 1. opens              - Opens package to all modules
     * 2. opens ... to       - Opens package to specific modules
     * 
     * Note: Reflection access is runtime-only
     */
    opens com.java.features.java9.modules.export.regular;

    /**
     * SERVICE CONFIGURATION
     * 
     * uses    - Declares that this module consumes a service
     * provides ... with ... - Declares service implementation
     * 
     * This enables the ServiceLoader pattern for loose coupling
     */
    uses java.sql.Driver;
    uses com.java.features.java9.service.MessageService;     // Service consumer
    provides com.java.features.java9.service.MessageService  // Service provider
        with com.java.features.java9.service.provider.CustomMessageService;
}
