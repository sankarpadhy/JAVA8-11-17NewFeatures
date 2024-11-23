package com.java.features.java8.datetime;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeExamplesTest {

    @Test
    void testCreateLocalDate() {
        String date = DateTimeExamples.createLocalDate(2023, 12, 31);
        assertEquals("2023-12-31", date);
    }

    @Test
    void testCreateLocalTime() {
        String time = DateTimeExamples.createLocalTime(13, 45);
        assertEquals("13:45:00", time);
    }

    @Test
    void testGetCurrentDateTime() {
        String dateTime = DateTimeExamples.getCurrentDateTime();
        assertNotNull(dateTime);
        assertTrue(dateTime.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*"));
    }

    @Test
    void testCalculateDaysBetween() {
        LocalDate start = LocalDate.of(2023, 1, 1);
        LocalDate end = LocalDate.of(2023, 12, 31);
        assertEquals(364, DateTimeExamples.calculateDaysBetween(start, end));
    }

    @Test
    void testGetTimeInZone() {
        String zonedTime = DateTimeExamples.getTimeInZone("UTC");
        assertNotNull(zonedTime);
        assertTrue(zonedTime.endsWith("Z"));
    }

    @Test
    void testCalculateMinutesBetween() {
        LocalTime time1 = LocalTime.of(10, 0);
        LocalTime time2 = LocalTime.of(11, 30);
        assertEquals(90, DateTimeExamples.calculateMinutesBetween(time1, time2));
    }

    @Test
    void testGetLastDayOfMonth() {
        LocalDate date = LocalDate.of(2023, 2, 1);
        LocalDate lastDay = DateTimeExamples.getLastDayOfMonth(date);
        assertEquals(28, lastDay.getDayOfMonth());
    }

    @Test
    void testGetCurrentTimestamp() {
        long timestamp = DateTimeExamples.getCurrentTimestamp();
        assertTrue(timestamp > 0);
        assertTrue(String.valueOf(timestamp).length() >= 13); // Milliseconds timestamp length
    }

    @Test
    void testFormatCustomDate() {
        LocalDate date = LocalDate.of(2023, 12, 31);
        String formatted = DateTimeExamples.formatCustomDate(date);
        assertEquals("31-Dec-2023", formatted);
    }
}
