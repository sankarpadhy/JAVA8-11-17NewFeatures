package com.java.features.java17.pattern;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Enhanced Switch Examples")
class EnhancedSwitchExampleTest {

    @Test
    @DisplayName("Format different types of values")
    void testFormatValue() {
        // Test various types
        assertEquals("null", EnhancedSwitchExample.formatValue(null));
        assertEquals("String: hello", EnhancedSwitchExample.formatValue("HELLO"));
        assertEquals("Integer: 84", EnhancedSwitchExample.formatValue(42));
        assertEquals("Long: 43", EnhancedSwitchExample.formatValue(42L));
        assertEquals("Double: 3.14", EnhancedSwitchExample.formatValue(3.14159));
        assertEquals("Int Array of length: 3", EnhancedSwitchExample.formatValue(new int[]{1, 2, 3}));
        
        // Test unknown type
        assertEquals("Unknown type: StringBuilder", 
            EnhancedSwitchExample.formatValue(new StringBuilder()));
    }

    @Test
    @DisplayName("Classify different numbers")
    void testClassifyNumber() {
        // Test null
        assertEquals("Invalid number: null", EnhancedSwitchExample.classifyNumber(null));
        
        // Test integers
        assertEquals("Negative integer: -42", EnhancedSwitchExample.classifyNumber(-42));
        assertEquals("Zero", EnhancedSwitchExample.classifyNumber(0));
        assertEquals("Positive integer: 42", EnhancedSwitchExample.classifyNumber(42));
        
        // Test doubles
        assertEquals("Not a number", EnhancedSwitchExample.classifyNumber(Double.NaN));
        assertEquals("Infinite", EnhancedSwitchExample.classifyNumber(Double.POSITIVE_INFINITY));
        assertEquals("Negative double: -3.14", EnhancedSwitchExample.classifyNumber(-3.14));
        assertEquals("Positive double: 3.14", EnhancedSwitchExample.classifyNumber(3.14));
    }

    @Test
    @DisplayName("Process different commands")
    void testProcessCommand() {
        // Test null and empty
        assertEquals("Empty command", EnhancedSwitchExample.processCommand(null));
        assertEquals("Empty command", EnhancedSwitchExample.processCommand(""));
        assertEquals("Empty command", EnhancedSwitchExample.processCommand("   "));
        
        // Test special commands
        assertEquals("Showing help menu...", EnhancedSwitchExample.processCommand("help"));
        assertEquals("Showing help menu...", EnhancedSwitchExample.processCommand("help me"));
        assertEquals("v1.0.0", EnhancedSwitchExample.processCommand("version"));
        
        // Test numeric commands
        assertEquals("Numeric command: 123", EnhancedSwitchExample.processCommand("123"));
        
        // Test short commands
        assertEquals("Command too short: hi", EnhancedSwitchExample.processCommand("hi"));
        
        // Test regular commands
        assertEquals("Processing command: start", EnhancedSwitchExample.processCommand("start"));
    }
}
