package com.java.features.java17.records;

/**
 * Demonstrates how records can be used with sealed interfaces to create
 * a type-safe Result type that can either be a Success or Failure.
 * This is a common pattern in functional programming.
 */
public sealed interface Result<T> permits Result.Success, Result.Failure {
    
    /**
     * Record representing a successful result containing a value
     */
    public record Success<T>(T value) implements Result<T> {
        // Compact constructor for validation
        public Success {
            if (value == null) {
                throw new IllegalArgumentException("Value cannot be null");
            }
        }
    }

    /**
     * Record representing a failure result containing an error message and optional cause
     */
    public record Failure<T>(String message, Throwable cause) implements Result<T> {
        // Compact constructor for validation
        public Failure {
            if (message == null || message.isBlank()) {
                throw new IllegalArgumentException("Error message cannot be null or empty");
            }
        }

        // Constructor overload for cases without a cause
        public Failure(String message) {
            this(message, null);
        }
    }

    /**
     * Pattern matching method to handle both success and failure cases
     */
    default <R> R match(
            java.util.function.Function<Success<T>, R> onSuccess,
            java.util.function.Function<Failure<T>, R> onFailure) {
        return switch (this) {
            case Success<T> s -> onSuccess.apply(s);
            case Failure<T> f -> onFailure.apply(f);
        };
    }

    /**
     * Factory method for creating a success result
     */
    static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    /**
     * Factory method for creating a failure result
     */
    static <T> Result<T> failure(String message) {
        return new Failure<>(message);
    }

    /**
     * Factory method for creating a failure result with a cause
     */
    static <T> Result<T> failure(String message, Throwable cause) {
        return new Failure<>(message, cause);
    }
}
