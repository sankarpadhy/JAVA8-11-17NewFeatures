package com.java.features.java8.optional;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Demonstrates Optional API features introduced in Java 8
 */
public class OptionalExamples {
    
    /**
     * Simulates a database lookup and returns an Optional containing a user if found.
     * 
     * Sample usage:
     * ```java
     * Optional<String> user1 = findUserById(42);
     * // Returns: Optional[User42]
     * 
     * Optional<String> user2 = findUserById(101);
     * // Returns: Optional.empty
     * ```
     * 
     * @param id User ID (valid range: 1-99)
     * @return Optional containing "User[id]" if id is valid, empty Optional otherwise
     */
    public static Optional<String> findUserById(int id) {
        // Simulating database lookup
        if (id > 0 && id < 100) {
            return Optional.of("User" + id);
        }
        return Optional.empty();
    }

    /**
     * Returns a username for the given ID, or "Unknown User" if not found.
     * 
     * Sample usage:
     * ```java
     * String user1 = getUserNameById(42);
     * // Returns: "User42"
     * 
     * String user2 = getUserNameById(101);
     * // Returns: "Unknown User"
     * ```
     * 
     * @param id User ID
     * @return Username if found, "Unknown User" otherwise
     */
    public static String getUserNameById(int id) {
        return findUserById(id).orElse("Unknown User");
    }

    /**
     * Returns a username for the given ID, or generates a new username if not found.
     * 
     * Sample usage:
     * ```java
     * String user1 = getUserNameWithSupplier(42);
     * // Returns: "User42"
     * 
     * String user2 = getUserNameWithSupplier(101);
     * // Returns: "Generated User1640995200000" (timestamp will vary)
     * ```
     * 
     * @param id User ID
     * @return Username if found, generated name with timestamp otherwise
     */
    public static String getUserNameWithSupplier(int id) {
        return findUserById(id)
                .orElseGet(() -> "Generated User" + System.currentTimeMillis());
    }

    /**
     * Returns the length of the username for the given ID.
     * 
     * Sample usage:
     * ```java
     * Optional<Integer> len1 = getUserNameLength(42);
     * // Returns: Optional[6] (length of "User42")
     * 
     * Optional<Integer> len2 = getUserNameLength(101);
     * // Returns: Optional.empty
     * ```
     * 
     * @param id User ID
     * @return Optional containing username length if user exists, empty Optional otherwise
     */
    public static Optional<Integer> getUserNameLength(int id) {
        return findUserById(id).map(String::length);
    }

    /**
     * Returns username only if it's longer than 5 characters.
     * 
     * Sample usage:
     * ```java
     * Optional<String> user1 = findUserWithLongName(42);
     * // Returns: Optional[User42] (length > 5)
     * 
     * Optional<String> user2 = findUserWithLongName(1);
     * // Returns: Optional.empty (User1 has length <= 5)
     * ```
     * 
     * @param id User ID
     * @return Optional containing username if it exists and length > 5, empty Optional otherwise
     */
    public static Optional<String> findUserWithLongName(int id) {
        return findUserById(id)
                .filter(name -> name.length() > 5);
    }

    /**
     * Prints the username if it exists.
     * 
     * Sample usage:
     * ```java
     * printUserIfExists(42);
     * // Prints: User42
     * 
     * printUserIfExists(101);
     * // Prints nothing
     * ```
     * 
     * @param id User ID
     */
    public static void printUserIfExists(int id) {
        findUserById(id).ifPresent(System.out::println);
    }

    /**
     * Returns username or throws exception if not found.
     * 
     * Sample usage:
     * ```java
     * String user1 = getUserOrThrow(42);
     * // Returns: "User42"
     * 
     * getUserOrThrow(101);
     * // Throws: IllegalArgumentException("User not found: 101")
     * ```
     * 
     * @param id User ID
     * @return Username if found
     * @throws IllegalArgumentException if user not found
     */
    public static String getUserOrThrow(int id) {
        return findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
    }

    /**
     * Returns email address for the given user ID.
     * 
     * Sample usage:
     * ```java
     * Optional<String> email1 = getUserEmail(42);
     * // Returns: Optional[user42@example.com]
     * 
     * Optional<String> email2 = getUserEmail(1);
     * // Returns: Optional.empty (username too short)
     * ```
     * 
     * @param id User ID
     * @return Optional containing email if user exists and username > 5 chars, empty Optional otherwise
     */
    public static Optional<String> getUserEmail(int id) {
        return findUserById(id)
                .flatMap(OptionalExamples::findEmailByUsername);
    }

    /**
     * Helper method to find email by username.
     * 
     * Sample usage:
     * ```java
     * Optional<String> email1 = findEmailByUsername("User123");
     * // Returns: Optional[user123@example.com]
     * 
     * Optional<String> email2 = findEmailByUsername("User1");
     * // Returns: Optional.empty (username too short)
     * ```
     */
    private static Optional<String> findEmailByUsername(String username) {
        // Simulating email lookup
        if (username.length() > 5) {
            return Optional.of(username.toLowerCase() + "@example.com");
        }
        return Optional.empty();
    }

    /**
     * Filters a list of IDs and returns only valid usernames.
     * 
     * Sample usage:
     * ```java
     * List<Integer> ids = Arrays.asList(42, 101, 7, 200);
     * List<String> users = filterValidUsers(ids);
     * // Returns: ["User42", "User7"]
     * ```
     * 
     * @param ids List of user IDs
     * @return List of valid usernames
     */
    public static List<String> filterValidUsers(List<Integer> ids) {
        return ids.stream()
                .map(OptionalExamples::findUserById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
