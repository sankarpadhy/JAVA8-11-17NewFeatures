package com.java.features.java17.records;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

@DisplayName("Java 17 Records Examples")
class RecordExamplesTest {

    @Test
    @DisplayName("Point record basic operations")
    void testPointRecord() {
        Point p1 = new Point(3, 4);
        Point p2 = new Point(3, 4);
        Point origin = Point.ORIGIN;

        // Test accessors
        assertEquals(3, p1.x());
        assertEquals(4, p1.y());

        // Test equals and hashCode
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());

        // Test toString
        assertTrue(p1.toString().contains("3") && p1.toString().contains("4"));

        // Test custom methods
        assertEquals(5.0, p1.distanceFromOrigin(), 0.001);
        assertEquals(0.0, origin.distanceFromOrigin(), 0.001);

        // Test validation
        assertThrows(IllegalArgumentException.class, () -> new Point(-1, 5));
    }

    @Test
    @DisplayName("Person record with complex validation and derived attributes")
    void testPersonRecord() {
        LocalDate dob = LocalDate.of(1990, 1, 1);
        Person person = new Person("John", "Doe", dob, "john.doe@example.com");

        // Test basic accessors
        assertEquals("John", person.firstName());
        assertEquals("Doe", person.lastName());
        assertEquals(dob, person.dateOfBirth());
        assertEquals("john.doe@example.com", person.email());

        // Test derived methods
        assertTrue(person.age() > 0);
        assertEquals("John Doe", person.fullName());

        // Test validation
        assertThrows(IllegalArgumentException.class, 
            () -> new Person("", "Doe", dob, "john.doe@example.com"));
        assertThrows(IllegalArgumentException.class, 
            () -> new Person("John", "Doe", dob, "invalid-email"));
        assertThrows(IllegalArgumentException.class, 
            () -> new Person("John", "Doe", LocalDate.now().plusDays(1), "john.doe@example.com"));
    }

    @Test
    @DisplayName("Result type with pattern matching")
    void testResultType() {
        // Test success case
        Result<Integer> success = Result.success(42);
        String successResult = success.match(
            s -> "Value: " + s.value(),
            f -> "Error: " + f.message()
        );
        assertEquals("Value: 42", successResult);

        // Test failure case
        Result<Integer> failure = Result.failure("Something went wrong");
        String failureResult = failure.match(
            s -> "Value: " + s.value(),
            f -> "Error: " + f.message()
        );
        assertEquals("Error: Something went wrong", failureResult);

        // Test failure with cause
        Exception cause = new RuntimeException("Original error");
        Result<Integer> failureWithCause = Result.failure("Wrapped error", cause);
        assertTrue(failureWithCause instanceof Result.Failure);
        Result.Failure<Integer> f = (Result.Failure<Integer>) failureWithCause;
        assertEquals(cause, f.cause());
    }
}
