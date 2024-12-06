package com.java.features.java8.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * Demonstrates the key features and improvements of Java 8's DateTime API.
 * 
 * Key advantages over old java.util.Date and Calendar:
 * 1. Immutable: All classes in the API are immutable, making them thread-safe
 * 2. Separation of Concerns: Clear separation between human-readable dates and machine time
 * 3. Clarity: Methods are clearly named and do exactly what you'd expect
 * 4. Utility Operations: Common operations are provided as built-in methods
 * 5. Extensible: The API is extensible for different calendaring systems
 */
public class DateTimeExamples {

    /**
     * Demonstrates working with dates without time components.
     * LocalDate is used for dates without times (e.g., birthdays, holidays).
     * 
     * @return A formatted string containing date information
     */
    public static String demonstrateLocalDate() {
        // Get current date
        LocalDate today = LocalDate.now();
        
        // Create specific date
        LocalDate independenceDay = LocalDate.of(2024, Month.JULY, 4);
        
        // Date arithmetic
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate threeDaysAgo = today.minusDays(3);
        
        // Date comparison
        boolean isBefore = today.isBefore(independenceDay);
        
        // Get date components
        int year = today.getYear();
        Month month = today.getMonth();
        int day = today.getDayOfMonth();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        
        return String.format("Today: %s, Next Week: %s, Is before July 4th? %s", 
                today, nextWeek, isBefore);
    }

    /**
     * Demonstrates working with times without date components.
     * LocalTime is used for times without dates (e.g., business hours, schedules).
     * 
     * @return A formatted string containing time information
     */
    public static String demonstrateLocalTime() {
        // Current time
        LocalTime now = LocalTime.now();
        
        // Specific time
        LocalTime noon = LocalTime.of(12, 0);
        
        // Time arithmetic
        LocalTime twoHoursLater = now.plusHours(2);
        LocalTime tenMinutesEarlier = now.minusMinutes(10);
        
        // Time comparison
        boolean isAfterNoon = now.isAfter(noon);
        
        // Get time components
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        
        return String.format("Current time: %s, Two hours later: %s, Is after noon? %s",
                now, twoHoursLater, isAfterNoon);
    }

    /**
     * Demonstrates working with date and time together.
     * LocalDateTime combines date and time, but without timezone information.
     * 
     * @return A formatted string containing date-time information
     */
    public static String demonstrateLocalDateTime() {
        // Current date and time
        LocalDateTime now = LocalDateTime.now();
        
        // Specific date and time
        LocalDateTime meeting = LocalDateTime.of(2024, Month.JANUARY, 15, 10, 30);
        
        // Date-time arithmetic
        LocalDateTime nextMeeting = meeting.plusWeeks(1);
        LocalDateTime rescheduled = meeting.withHour(14);
        
        // Extract date or time
        LocalDate meetingDate = meeting.toLocalDate();
        LocalTime meetingTime = meeting.toLocalTime();
        
        return String.format("Meeting scheduled for: %s, Rescheduled to: %s",
                meeting, rescheduled);
    }

    /**
     * Demonstrates working with timezones.
     * ZonedDateTime adds timezone awareness to date-time operations.
     * 
     * @return A formatted string containing timezone information
     */
    public static String demonstrateZonedDateTime() {
        // Get available timezone IDs
        Set<String> zones = ZoneId.getAvailableZoneIds();
        
        // Create timezone objects
        ZoneId newYork = ZoneId.of("America/New_York");
        ZoneId tokyo = ZoneId.of("Asia/Tokyo");
        
        // Current time in different zones
        ZonedDateTime nyTime = ZonedDateTime.now(newYork);
        ZonedDateTime tokyoTime = nyTime.withZoneSameInstant(tokyo);
        
        // Time differences
        Duration timeDiff = Duration.between(nyTime.toLocalTime(), tokyoTime.toLocalTime());
        
        return String.format("NY: %s%nTokyo: %s%nTime difference: %d hours",
                nyTime, tokyoTime, timeDiff.toHours());
    }

    /**
     * Demonstrates working with time periods and durations.
     * Period: Date-based amount of time
     * Duration: Time-based amount of time
     * 
     * @return A formatted string containing period and duration calculations
     */
    public static String demonstratePeriodAndDuration() {
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2025, 6, 15);
        
        // Calculate period between dates
        Period period = Period.between(start, end);
        
        LocalTime timeStart = LocalTime.of(9, 0);
        LocalTime timeEnd = LocalTime.of(17, 30);
        
        // Calculate duration between times
        Duration duration = Duration.between(timeStart, timeEnd);
        
        return String.format("Period: %d years, %d months, %d days%nDuration: %d hours, %d minutes",
                period.getYears(), period.getMonths(), period.getDays(),
                duration.toHours(), duration.toMinutes() % 60);
    }

    /**
     * Demonstrates date and time formatting.
     * DateTimeFormatter provides thread-safe formatting and parsing.
     * 
     * @param dateTime The date-time to format
     * @return A formatted string representation
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        // Predefined formatters
        String iso = dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        
        // Custom formatters
        DateTimeFormatter custom = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy 'at' HH:mm");
        String formatted = dateTime.format(custom);
        
        // Localized formatters
        DateTimeFormatter localized = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String localDate = dateTime.format(localized);
        
        return String.format("ISO: %s%nCustom: %s%nLocalized: %s",
                iso, formatted, localDate);
    }

    /**
     * Demonstrates temporal adjusters for common date manipulations.
     * TemporalAdjusters provide common calendar operations.
     * 
     * @param date The date to adjust
     * @return A formatted string containing adjusted dates
     */
    public static String demonstrateAdjusters(LocalDate date) {
        // First day of month
        LocalDate firstDay = date.with(TemporalAdjusters.firstDayOfMonth());
        
        // Last day of month
        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        
        // Next Monday
        LocalDate nextMonday = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        
        // First day of next year
        LocalDate firstDayNextYear = date.with(TemporalAdjusters.firstDayOfNextYear());
        
        return String.format("First day of month: %s%nLast day of month: %s%nNext Monday: %s%nFirst day of next year: %s",
                firstDay, lastDay, nextMonday, firstDayNextYear);
    }

    /**
     * Demonstrates instant operations for machine-based time.
     * Instant represents a point in time on the timeline (epoch-based).
     * 
     * @return A formatted string containing instant operations
     */
    public static String demonstrateInstant() {
        // Current instant
        Instant now = Instant.now();
        
        // Instant arithmetic
        Instant later = now.plus(1, ChronoUnit.HOURS);
        Instant earlier = now.minus(30, ChronoUnit.MINUTES);
        
        // Conversion to/from epoch
        long epochSecond = now.getEpochSecond();
        Instant fromEpoch = Instant.ofEpochSecond(epochSecond);
        
        return String.format("Now: %s%nOne hour later: %s%nEpoch seconds: %d",
                now, later, epochSecond);
    }
}
