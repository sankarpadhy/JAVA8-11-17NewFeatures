package com.java.features.java11.nest;

/**
 * Demonstrates Nest-Based Access Control introduced in Java 11.
 * Nests allow classes that are logically part of the same code entity 
 * to access each other's private members without requiring compilers to insert bridge methods.
 */
public class NestBasedAccessExample {

    private String secret = "top secret";

    /**
     * Nested class demonstrating nest-based access.
     * Can access private members of the enclosing class directly.
     */
    public class NestedClass {
        /**
         * Accesses private field of enclosing class.
         * 
         * Example:
         * ```java
         * NestBasedAccessExample example = new NestBasedAccessExample();
         * NestedClass nested = example.new NestedClass();
         * nested.accessEnclosingPrivate();
         * // Output: Accessed enclosing class's secret: top secret
         * ```
         */
        public void accessEnclosingPrivate() {
            System.out.println("Accessed enclosing class's secret: " + secret);
        }

        private String nestedSecret = "nested secret";
    }

    /**
     * Another nested class demonstrating nest-based access between siblings.
     */
    public class AnotherNestedClass {
        /**
         * Accesses private members of sibling nested class.
         * 
         * Example:
         * ```java
         * NestBasedAccessExample example = new NestBasedAccessExample();
         * NestedClass nested = example.new NestedClass();
         * AnotherNestedClass another = example.new AnotherNestedClass();
         * another.accessSiblingPrivate(nested);
         * // Output: Accessed sibling's secret: nested secret
         * ```
         */
        public void accessSiblingPrivate(NestedClass sibling) {
            System.out.println("Accessed sibling's secret: " + sibling.nestedSecret);
        }
    }

    /**
     * Demonstrates nest membership test using reflection.
     * 
     * Example:
     * ```java
     * demonstrateNestMembers();
     * // Output:
     * // NestBasedAccessExample is nest host: true
     * // NestedClass is nest member: true
     * // AnotherNestedClass is nest member: true
     * ```
     */
    public void demonstrateNestMembers() {
        Class<?> hostClass = NestBasedAccessExample.class;
        Class<?> nestedClass = NestedClass.class;
        Class<?> anotherNestedClass = AnotherNestedClass.class;

        System.out.println(hostClass.getSimpleName() + " is nest host: " 
                + hostClass.isNestHost());
        System.out.println(nestedClass.getSimpleName() + " is nest member: " 
                + nestedClass.isNestMember());
        System.out.println(anotherNestedClass.getSimpleName() + " is nest member: " 
                + anotherNestedClass.isNestMember());
    }

    public static void main(String[] args) {
        NestBasedAccessExample example = new NestBasedAccessExample();
        
        System.out.println("=== Nest-Based Access Demo ===");
        NestedClass nested = example.new NestedClass();
        nested.accessEnclosingPrivate();

        System.out.println("\n=== Sibling Access Demo ===");
        AnotherNestedClass another = example.new AnotherNestedClass();
        another.accessSiblingPrivate(nested);

        System.out.println("\n=== Nest Membership Demo ===");
        example.demonstrateNestMembers();
    }
}
