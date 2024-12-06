package com.java.features.java9.jshell;

/**
 * Examples of features available in JShell (Java REPL)
 * Note: These examples are meant to be run in JShell directly
 * This class serves as documentation of JShell features
 */
public class JShellExamples {

    /**
     * To use JShell:
     * 1. Open terminal
     * 2. Type 'jshell'
     * 3. Try the following examples
     */
    public static void main(String[] args) {
        System.out.println("JShell Examples - Run these in JShell:");
        System.out.println("\n1. Simple Expressions:");
        System.out.println("2 + 2");
        System.out.println("System.out.println(\"Hello, JShell!\")");

        System.out.println("\n2. Variable Declaration:");
        System.out.println("int x = 10");
        System.out.println("String name = \"Java 9\"");

        System.out.println("\n3. Method Definition:");
        System.out.println("int square(int n) { return n * n; }");
        System.out.println("square(5)");

        System.out.println("\n4. Class Definition:");
        System.out.println("class Person {");
        System.out.println("    private String name;");
        System.out.println("    public Person(String name) { this.name = name; }");
        System.out.println("    public String getName() { return name; }");
        System.out.println("}");

        System.out.println("\n5. Import Statements:");
        System.out.println("/import java.util.List");
        System.out.println("/imports");

        System.out.println("\n6. JShell Commands:");
        System.out.println("/help - Show help");
        System.out.println("/list - List source code");
        System.out.println("/vars - List variables");
        System.out.println("/methods - List methods");
        System.out.println("/types - List types");
        System.out.println("/exit - Exit JShell");

        System.out.println("\n7. Tab Completion:");
        System.out.println("System.out. [Press Tab]");

        System.out.println("\n8. Method Information:");
        System.out.println("String str = \"Hello\"");
        System.out.println("str. [Press Tab]");
    }

    /**
     * JShell features:
     * - Immediate feedback
     * - No need for main method
     * - No need for semicolons
     * - Auto-complete
     * - Command history
     * - Snippet editing
     * - Variable and method inspection
     */
}
