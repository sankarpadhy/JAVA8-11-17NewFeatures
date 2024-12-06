package com.java.features.java9.jshell;

/**
 * Demonstrates the JShell REPL (Read-Eval-Print Loop) tool introduced in Java 9.
 * This class provides documentation and examples of using JShell for interactive
 * Java code evaluation and rapid prototyping.
 *
 * Key features demonstrated:
 * - Basic expression evaluation
 * - Variable declarations and assignments
 * - Method and class definitions
 * - Import statements management
 * - Tab completion functionality
 * - Command history and navigation
 * - Built-in JShell commands
 *
 * To use JShell:
 * 1. Open terminal/command prompt
 * 2. Type 'jshell'
 * 3. Try these examples:
 * ```java
 * // Basic expressions
 * 2 + 2
 * "Hello, " + "JShell!"
 *
 * // Variable declaration
 * var message = "Hello, World!"
 * System.out.println(message)
 *
 * // Method definition
 * void greet(String name) {
 *     System.out.println("Hello, " + name + "!");
 * }
 * greet("Java 9")
 * ```
 *
 * Useful JShell commands:
 * - /help - Show help
 * - /list - List source code
 * - /vars - List variables
 * - /methods - List methods
 * - /exit - Exit JShell
 */
import java.util.Collections;

public class JShellExample {
    
    /**
     * This class serves as documentation for JShell usage.
     * JShell is a command-line tool, so actual examples should be run in the JShell environment.
     * 
     * To try these examples:
     * 1. Open terminal/command prompt
     * 2. Type 'jshell'
     * 3. Copy and paste the examples below
     */
    public static void main(String[] args) {
        System.out.println("JShell Example Snippets to Try:");
        System.out.println("==============================");
        
        printExample("Basic Expression", 
            "2 + 2\n" +
            "// Output: $1 ==> 4");
            
        printExample("Variable Declaration", 
            "var message = \"Hello, JShell!\"\n" +
            "System.out.println(message)");
            
        printExample("Method Declaration", 
            "void greet(String name) {\n" +
            "    System.out.println(\"Hello, \" + name);\n" +
            "}\n" +
            "greet(\"Java 9\")");
            
        printExample("List Manipulation", 
            "import java.util.List;\n" +
            "var numbers = List.of(1, 2, 3, 4, 5)\n" +
            "numbers.stream()\n" +
            "       .filter(n -> n % 2 == 0)\n" +
            "       .forEach(System.out::println)");
            
        printExample("Exception Handling", 
            "try {\n" +
            "    throw new RuntimeException(\"Test\");\n" +
            "} catch (Exception e) {\n" +
            "    System.out.println(e.getMessage());\n" +
            "}");
    }
    
    private static void printExample(String title, String code) {
        System.out.println("\n" + title + ":");
        String separator = String.join("", Collections.nCopies(title.length() + 1, "-"));
        System.out.println(separator);
        System.out.println(code);
        System.out.println();
    }
}
