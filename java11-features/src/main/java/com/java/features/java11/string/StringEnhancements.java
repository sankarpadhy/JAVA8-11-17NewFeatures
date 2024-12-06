package com.java.features.java11.string;

/**
 * Demonstrates String API enhancements introduced in Java 11.
 * Includes new methods for string manipulation and validation.
 */
public class StringEnhancements {

    /**
     * Demonstrates the isBlank() method.
     * Returns true if string is empty or contains only whitespace.
     * 
     * Example:
     * ```java
     * demonstrateIsBlank();
     * // Output:
     * // "  " is blank: true
     * // "" is blank: true
     * // "Hello" is blank: false
     * ```
     */
    public void demonstrateIsBlank() {
        String whitespace = "   ";
        System.out.println("\"  \" is blank: " + whitespace.isBlank());

        String empty = "";
        System.out.println("\"\" is blank: " + empty.isBlank());

        String text = "Hello";
        System.out.println("\"Hello\" is blank: " + text.isBlank());
    }

    /**
     * Demonstrates the lines() method.
     * Returns a Stream of lines from the string.
     * 
     * Example:
     * ```java
     * demonstrateLines();
     * // Output:
     * // Line 1: First line
     * // Line 2: Second line
     * // Line 3: Third line
     * ```
     */
    public void demonstrateLines() {
        String multiline = "First line\nSecond line\nThird line";
        multiline.lines()
                .map(line -> "Line: " + line)
                .forEach(System.out::println);
    }

    /**
     * Demonstrates the strip(), stripLeading(), and stripTrailing() methods.
     * These methods handle Unicode whitespace characters better than trim().
     * 
     * Example:
     * ```java
     * demonstrateStrip();
     * // Output:
     * // Original: "  Hello World  "
     * // Stripped: "Hello World"
     * // Leading stripped: "Hello World  "
     * // Trailing stripped: "  Hello World"
     * ```
     */
    public void demonstrateStrip() {
        String text = "  Hello World  ";
        System.out.println("Original: \"" + text + "\"");
        System.out.println("Stripped: \"" + text.strip() + "\"");
        System.out.println("Leading stripped: \"" + text.stripLeading() + "\"");
        System.out.println("Trailing stripped: \"" + text.stripTrailing() + "\"");
    }

    /**
     * Demonstrates the repeat() method.
     * Returns a string whose value is the concatenation of the string repeated count times.
     * 
     * Example:
     * ```java
     * demonstrateRepeat();
     * // Output:
     * // "Ha" repeated 3 times: HaHaHa
     * // "-" repeated 5 times: -----
     * // Empty string repeated: ""
     * ```
     */
    public void demonstrateRepeat() {
        String laugh = "Ha";
        System.out.println("\"Ha\" repeated 3 times: " + laugh.repeat(3));

        String dash = "-";
        System.out.println("\"-\" repeated 5 times: " + dash.repeat(5));

        String empty = "";
        System.out.println("Empty string repeated: \"" + empty.repeat(3) + "\"");
    }

    public static void main(String[] args) {
        StringEnhancements demo = new StringEnhancements();
        
        System.out.println("=== isBlank() Demo ===");
        demo.demonstrateIsBlank();
        
        System.out.println("\n=== lines() Demo ===");
        demo.demonstrateLines();
        
        System.out.println("\n=== strip() Demo ===");
        demo.demonstrateStrip();
        
        System.out.println("\n=== repeat() Demo ===");
        demo.demonstrateRepeat();
    }
}
