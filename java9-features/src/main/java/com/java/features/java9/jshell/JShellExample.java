package com.java.features.java9.jshell;

/**
 * Documentation and examples for using JShell - The Java REPL Tool introduced in Java 9
 * 
 * What is JShell?
 * -------------
 * JShell is Java's first official REPL (Read-Eval-Print Loop) tool.
 * It allows developers to:
 * 1. Quickly test Java code snippets
 * 2. Explore APIs interactively
 * 3. Prototype new code
 * 4. Learn Java in an interactive way
 * 
 * Key Features:
 * -----------
 * 1. Immediate expression evaluation
 * 2. Automatic variable declaration
 * 3. Automatic imports of common packages
 * 4. Command history and editing
 * 5. Tab completion
 * 6. Snippet management
 * 
 * Common JShell Commands:
 * --------------------
 * /help           - List all commands
 * /list           - List entered snippets
 * /vars           - List declared variables
 * /methods        - List declared methods
 * /imports        - List imported packages
 * /exit           - Exit JShell
 * 
 * Example Usage:
 * ------------
 * To start JShell:
 * {@code
 * > jshell
 * 
 * // Direct expression evaluation
 * jshell> 2 + 2
 * $1 ==> 4
 * 
 * // Variable declaration (type inference)
 * jshell> var message = "Hello, JShell!"
 * message ==> "Hello, JShell!"
 * 
 * // Method declaration
 * jshell> void greet(String name) {
 *    ...> System.out.println("Hello, " + name);
 *    ...> }
 * 
 * // Method invocation
 * jshell> greet("Java 9")
 * Hello, Java 9
 * }
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
