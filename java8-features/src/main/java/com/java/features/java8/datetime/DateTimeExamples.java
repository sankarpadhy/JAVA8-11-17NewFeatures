package com.java.features.java8.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * Demonstrates the new Date and Time API features introduced in Java 8
 */
public class DateTimeExamples {

    /**
     * Demonstrates LocalDate usage
     * @param year Year
     * @param month Month
     * @param day Day
     * @return Formatted date string
     */
    public static String createLocalDate(int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Demonstrates LocalTime usage
     * @param hour Hour
     * @param minute Minute
     * @return Formatted time string
     */
    public static String createLocalTime(int hour, int minute) {
        LocalTime time = LocalTime.of(hour, minute);
        return time.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    /**
     * Demonstrates LocalDateTime usage
     * @return Current date and time formatted string
     */
    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Demonstrates Period calculations
     * @param startDate Start date
     * @param endDate End date
     * @return Period between dates in days
     */
    public static long calculateDaysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * Demonstrates ZonedDateTime usage
     * @param zoneName Zone name (e.g., "America/New_York")
     * @return Current time in specified zone
     */
    public static String getTimeInZone(String zoneName) {
        ZonedDateTime zonedTime = ZonedDateTime.now(ZoneId.of(zoneName));
        if ("UTC".equals(zoneName)) {
            return zonedTime.toInstant().toString(); // This will use ISO_INSTANT format which ends with Z for UTC
        }
        return zonedTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    /**
     * Demonstrates Duration calculations
     * @param time1 First time
     * @param time2 Second time
     * @return Duration between times in minutes
     */
    public static long calculateMinutesBetween(LocalTime time1, LocalTime time2) {
        return ChronoUnit.MINUTES.between(time1, time2);
    }

    /**
     * Demonstrates temporal adjusters
     * @param date Input date
     * @return Last day of the month for input date
     */
    public static LocalDate getLastDayOfMonth(LocalDate date) {
        return date.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * Demonstrates instant and conversion
     * @return Current timestamp in milliseconds
     */
    public static long getCurrentTimestamp() {
        return Instant.now().toEpochMilli();
    }

    /**
     * Demonstrates custom date formatting
     * @param date Date to format
     * @return Formatted date string (e.g., "31-Dec-2023")
     */
    public static String formatCustomDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return date.format(formatter);
    }
}
