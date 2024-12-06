package com.java.features.java11.time;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the Time Unit Conversion methods introduced in Java 11.
 * Shows how to convert between different time units using new convenience methods.
 */
public class TimeUnitConversionExample {

    /**
     * Demonstrates converting Duration to different time units.
     * 
     * Example:
     * ```java
     * Duration duration = Duration.ofHours(2).plusMinutes(30);
     * demonstrateDurationConversion(duration);
     * // Output:
     * // Duration: PT2H30M
     * // Days: 0
     * // Hours: 2
     * // Minutes: 150
     * // Seconds: 9000
     * // Milliseconds: 9000000
     * ```
     * 
     * @param duration the duration to convert
     */
    public void demonstrateDurationConversion(Duration duration) {
        System.out.println("Duration: " + duration);
        System.out.println("Days: " + duration.toDays());
        System.out.println("Hours: " + duration.toHours());
        System.out.println("Minutes: " + duration.toMinutes());
        System.out.println("Seconds: " + duration.toSeconds());
        System.out.println("Milliseconds: " + duration.toMillis());
    }

    /**
     * Demonstrates converting between different time units using TimeUnit.
     * 
     * Example:
     * ```java
     * demonstrateTimeUnitConversion(3, TimeUnit.HOURS);
     * // Output:
     * // 3 HOURS equals:
     * // Days: 0
     * // Hours: 3
     * // Minutes: 180
     * // Seconds: 10800
     * // Milliseconds: 10800000
     * ```
     * 
     * @param value the time value to convert
     * @param unit the source time unit
     */
    public void demonstrateTimeUnitConversion(long value, TimeUnit unit) {
        System.out.println(value + " " + unit + " equals:");
        System.out.println("Days: " + unit.toDays(value));
        System.out.println("Hours: " + unit.toHours(value));
        System.out.println("Minutes: " + unit.toMinutes(value));
        System.out.println("Seconds: " + unit.toSeconds(value));
        System.out.println("Milliseconds: " + unit.toMillis(value));
    }

    /**
     * Demonstrates converting between time units with precision loss.
     * 
     * Example:
     * ```java
     * demonstratePrecisionConversion(90, TimeUnit.MINUTES);
     * // Output:
     * // 90 MINUTES converts to:
     * // Hours (floor): 1
     * // Hours (ceiling): 2
     * // Hours (exact): false
     * ```
     * 
     * @param value the time value to convert
     * @param unit the source time unit
     */
    public void demonstratePrecisionConversion(long value, TimeUnit unit) {
        System.out.println(value + " " + unit + " converts to:");
        
        TimeUnit targetUnit = TimeUnit.HOURS;
        System.out.println(targetUnit + " (floor): " + unit.convert(value, targetUnit));
        System.out.println(targetUnit + " (ceiling): " + 
            (unit.convert(value, targetUnit) + (unit.toNanos(value) % targetUnit.toNanos(1) > 0 ? 1 : 0)));
        System.out.println(targetUnit + " (exact): " + 
            (unit.toNanos(value) % targetUnit.toNanos(1) == 0));
    }

    /**
     * Demonstrates working with negative durations.
     * 
     * Example:
     * ```java
     * Duration negativeDuration = Duration.ofHours(-5);
     * demonstrateNegativeDuration(negativeDuration);
     * // Output:
     * // Negative duration: PT-5H
     * // Absolute hours: 5
     * // Is negative: true
     * // Negated: PT5H
     * ```
     * 
     * @param duration the negative duration to demonstrate
     */
    public void demonstrateNegativeDuration(Duration duration) {
        System.out.println("Negative duration: " + duration);
        System.out.println("Absolute hours: " + Math.abs(duration.toHours()));
        System.out.println("Is negative: " + duration.isNegative());
        System.out.println("Negated: " + duration.negated());
    }

    public static void main(String[] args) {
        TimeUnitConversionExample demo = new TimeUnitConversionExample();

        System.out.println("=== Duration Conversion Demo ===");
        Duration duration = Duration.ofHours(2).plusMinutes(30);
        demo.demonstrateDurationConversion(duration);

        System.out.println("\n=== TimeUnit Conversion Demo ===");
        demo.demonstrateTimeUnitConversion(3, TimeUnit.HOURS);

        System.out.println("\n=== Precision Conversion Demo ===");
        demo.demonstratePrecisionConversion(90, TimeUnit.MINUTES);

        System.out.println("\n=== Negative Duration Demo ===");
        Duration negativeDuration = Duration.ofHours(-5);
        demo.demonstrateNegativeDuration(negativeDuration);
    }
}
