package com.java.features.java11.string;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Demonstrates String API enhancements in Java 11
 */
public class StringAPIEnhancements {

    /**
     * Demonstrates String.isBlank()
     */
    public static boolean isBlankString(String str) {
        return str.isBlank();
    }

    /**
     * Demonstrates String.lines()
     */
    public static List<String> getLines(String multiLineString) {
        return multiLineString.lines()
                .collect(Collectors.toList());
    }

    /**
     * Demonstrates String.strip()
     */
    public static String stripWhitespace(String str) {
        return str.strip();
    }

    /**
     * Demonstrates String.stripLeading()
     */
    public static String stripLeadingWhitespace(String str) {
        return str.stripLeading();
    }

    /**
     * Demonstrates String.stripTrailing()
     */
    public static String stripTrailingWhitespace(String str) {
        return str.stripTrailing();
    }

    /**
     * Demonstrates String.repeat()
     */
    public static String repeatString(String str, int times) {
        return str.repeat(times);
    }

    /**
     * Complex example combining multiple String operations
     */
    public static String processMultiLineText(String text) {
        return text.lines()
                .filter(line -> !line.isBlank())
                .map(String::strip)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Demonstrates the difference between strip() and trim()
     */
    public static boolean compareStripAndTrim(String str) {
        String stripped = str.strip();
        String trimmed = str.trim();
        return !stripped.equals(trimmed);
    }
}
