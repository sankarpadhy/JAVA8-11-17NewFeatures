package com.java.features.java8.datetime;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * Test class for Java 8's DateTime API features.
 * Verifies the functionality of various date-time operations including:
 * - Date operations (LocalDate)
 * - Time operations (LocalTime)
 * - Combined date-time operations (LocalDateTime)
 * - Timezone handling (ZonedDateTime)
 * - Period and Duration calculations
 * - Formatting and parsing
 * - Temporal adjusters
 */
public class DateTimeExamplesTest {

    /**
     * Tests LocalDate operations and formatting.
     * Verifies:
     * - Current date retrieval
     * - Date arithmetic
     * - Date comparisons
     * - Component extraction
     */
    @Test
    public void testDemonstrateLocalDate() {
        String result = DateTimeExamples.demonstrateLocalDate();
        assertNotNull("Result should not be null", result);
        assertTrue("Result should contain today's date", result.contains("Today:"));
        assertTrue("Result should contain next week's date", result.contains("Next Week:"));
        assertTrue("Result should contain comparison result", result.contains("Is before July 4th?"));
    }

    /**
     * Tests LocalTime operations and formatting.
     * Verifies:
     * - Current time retrieval
     * - Time arithmetic
     * - Time comparisons
     * - Component extraction
     */
    @Test
    public void testDemonstrateLocalTime() {
        String result = DateTimeExamples.demonstrateLocalTime();
        assertNotNull("Result should not be null", result);
        assertTrue("Result should contain current time", result.contains("Current time:"));
        assertTrue("Result should contain future time", result.contains("Two hours later:"));
        assertTrue("Result should contain noon comparison", result.contains("Is after noon?"));
    }

    /**
     * Tests LocalDateTime operations.
     * Verifies:
     * - DateTime creation
     * - DateTime arithmetic
     * - Component extraction
     * - Formatting
     */
    @Test
    public void testDemonstrateLocalDateTime() {
        String result = DateTimeExamples.demonstrateLocalDateTime();
        assertNotNull("Result should not be null", result);
        assertTrue("Result should contain meeting time", result.contains("Meeting scheduled for:"));
        assertTrue("Result should contain rescheduled time", result.contains("Rescheduled to:"));
    }

    /**
     * Tests timezone operations with ZonedDateTime.
     * Verifies:
     * - Timezone conversion
     * - Time differences
     * - Zone ID handling
     */
    @Test
    public void testDemonstrateZonedDateTime() {
        String result = DateTimeExamples.demonstrateZonedDateTime();
        assertNotNull("Result should not be null", result);
        assertTrue("Result should contain NY time", result.contains("NY:"));
        assertTrue("Result should contain Tokyo time", result.contains("Tokyo:"));
        assertTrue("Result should show time difference", result.contains("Time difference:"));
    }

    /**
     * Tests Period and Duration calculations.
     * Verifies:
     * - Period between dates
     * - Duration between times
     * - Formatting of results
     */
    @Test
    public void testDemonstratePeriodAndDuration() {
        String result = DateTimeExamples.demonstratePeriodAndDuration();
        assertNotNull("Result should not be null", result);
        assertTrue("Result should contain period info", result.contains("Period:"));
        assertTrue("Result should contain duration info", result.contains("Duration:"));
        assertTrue("Result should show years", result.contains("years"));
        assertTrue("Result should show months", result.contains("months"));
    }

    /**
     * Tests date-time formatting operations.
     * Verifies:
     * - ISO format
     * - Custom format
     * - Localized format
     */
    @Test
    public void testFormatDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 1, 15, 14, 30);
        String result = DateTimeExamples.formatDateTime(dateTime);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should contain ISO format", result.contains("ISO:"));
        assertTrue("Result should contain custom format", result.contains("Custom:"));
        assertTrue("Result should contain localized format", result.contains("Localized:"));
    }

    /**
     * Tests temporal adjusters.
     * Verifies:
     * - First/last day of month
     * - Next Monday
     * - First day of next year
     */
    @Test
    public void testDemonstrateAdjusters() {
        LocalDate date = LocalDate.of(2024, 1, 15);
        String result = DateTimeExamples.demonstrateAdjusters(date);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should show first day of month", result.contains("First day of month:"));
        assertTrue("Result should show last day of month", result.contains("Last day of month:"));
        assertTrue("Result should show next Monday", result.contains("Next Monday:"));
        assertTrue("Result should show first day of next year", result.contains("First day of next year:"));
    }

    /**
     * Tests Instant operations.
     * Verifies:
     * - Current instant
     * - Instant arithmetic
     * - Epoch conversion
     */
    @Test
    public void testDemonstrateInstant() {
        String result = DateTimeExamples.demonstrateInstant();
        assertNotNull("Result should not be null", result);
        assertTrue("Result should contain current instant", result.contains("Now:"));
        assertTrue("Result should contain future instant", result.contains("One hour later:"));
        assertTrue("Result should contain epoch seconds", result.contains("Epoch seconds:"));
    }
}
