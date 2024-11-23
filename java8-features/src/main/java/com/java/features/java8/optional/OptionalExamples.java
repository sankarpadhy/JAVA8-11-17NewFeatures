package com.java.features.java8.optional;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Demonstrates Optional API features introduced in Java 8
 */
public class OptionalExamples {
    
    /**
     * Demonstrates Optional creation and basic operations
     */
    public static Optional<String> findUserById(int id) {
        // Simulating database lookup
        if (id > 0 && id < 100) {
            return Optional.of("User" + id);
        }
        return Optional.empty();
    }

    /**
     * Demonstrates orElse operation
     */
    public static String getUserNameById(int id) {
        return findUserById(id).orElse("Unknown User");
    }

    /**
     * Demonstrates orElseGet with supplier
     */
    public static String getUserNameWithSupplier(int id) {
        return findUserById(id)
                .orElseGet(() -> "Generated User" + System.currentTimeMillis());
    }

    /**
     * Demonstrates map operation
     */
    public static Optional<Integer> getUserNameLength(int id) {
        return findUserById(id).map(String::length);
    }

    /**
     * Demonstrates filter operation
     */
    public static Optional<String> findUserWithLongName(int id) {
        return findUserById(id)
                .filter(name -> name.length() > 5);
    }

    /**
     * Demonstrates ifPresent consumer
     */
    public static void printUserIfExists(int id) {
        findUserById(id).ifPresent(System.out::println);
    }

    /**
     * Demonstrates orElseThrow operation
     */
    public static String getUserOrThrow(int id) {
        return findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
    }

    /**
     * Demonstrates flatMap operation
     */
    public static Optional<String> getUserEmail(int id) {
        return findUserById(id)
                .flatMap(OptionalExamples::findEmailByUsername);
    }

    private static Optional<String> findEmailByUsername(String username) {
        // Simulating email lookup
        if (username.length() > 5) {
            return Optional.of(username.toLowerCase() + "@example.com");
        }
        return Optional.empty();
    }

    /**
     * Demonstrates Optional with streams
     */
    public static List<String> filterValidUsers(List<Integer> ids) {
        return ids.stream()
                .map(OptionalExamples::findUserById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
