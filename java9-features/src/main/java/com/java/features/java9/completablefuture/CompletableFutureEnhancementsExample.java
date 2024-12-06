package com.java.features.java9.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Demonstrates the CompletableFuture enhancements introduced in Java 9.
 * This class showcases new methods and capabilities added to improve
 * asynchronous programming and error handling.
 *
 * Key enhancements demonstrated:
 * 1. New Factory Methods:
 *    - completedFuture(): Pre-completed with value
 *    - failedFuture(): Pre-completed exceptionally
 *    - delayedExecutor(): Delayed execution
 *
 * 2. Timeout Support:
 *    - orTimeout(): Complete exceptionally after timeout
 *    - completeOnTimeout(): Complete with value after timeout
 *
 * 3. Copy Methods:
 *    - copy(): Create independent copy
 *    - newIncompleteFuture(): Create empty copy
 *
 * 4. Improved Exception Handling:
 *    - exceptionallyAsync(): Handle exceptions asynchronously
 *    - exceptionallyCompose(): Chain exception handlers
 *
 * Example usage:
 * ```java
 * // Timeout handling
 * CompletableFuture<String> future = CompletableFuture
 *     .supplyAsync(() -> slowOperation())
 *     .orTimeout(1, TimeUnit.SECONDS)
 *     .exceptionally(ex -> "Timeout occurred");
 *
 * // Delayed execution
 * Executor delayed = CompletableFuture
 *     .delayedExecutor(1, TimeUnit.SECONDS);
 * CompletableFuture<String> delayed = CompletableFuture
 *     .supplyAsync(() -> "Delayed", delayed);
 *
 * // Default value on timeout
 * CompletableFuture<String> withDefault = CompletableFuture
 *     .supplyAsync(() -> slowOperation())
 *     .completeOnTimeout("Default", 1, TimeUnit.SECONDS);
 * ```
 *
 * Benefits:
 * - Better timeout handling
 * - Improved error recovery
 * - More flexible execution control
 * - Enhanced composability
 *
 * @see java.util.concurrent.CompletableFuture
 * @see java.util.concurrent.TimeoutException
 * @since Java 9
 */
public class CompletableFutureEnhancementsExample {

    private final Executor executor = Executors.newFixedThreadPool(3);

    /**
     * Demonstrates new factory methods in Java 9.
     * 
     * Example:
     * ```java
     * demonstrateFactoryMethods();
     * // Output:
     * // Completed future value: Done
     * // Failed future exception: Failed intentionally
     * // Delayed execution result: Delayed
     * ```
     */
    public void demonstrateFactoryMethods() {
        System.out.println("=== Factory Methods Demo ===");

        // completedFuture with value
        CompletableFuture<String> completed = CompletableFuture.completedFuture("Done");
        System.out.println("Completed future: " + completed.join());

        // failedFuture with exception
        CompletableFuture<String> failed = CompletableFuture.failedFuture(
            new RuntimeException("Failed intentionally"));
        try {
            failed.join();
        } catch (Exception e) {
            System.out.println("Failed future exception: " + e.getCause().getMessage());
        }

        // delayedExecutor
        CompletableFuture.supplyAsync(() -> "Delayed", 
            CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS))
            .thenAccept(result -> System.out.println("Delayed result: " + result));

        sleep(2000); // Wait for delayed execution
    }

    /**
     * Demonstrates new timeout methods.
     * 
     * Example:
     * ```java
     * demonstrateTimeoutMethods();
     * // Output:
     * // orTimeout result: Timeout occurred as expected
     * // completeOnTimeout result: Fallback Result
     * // Completed within timeout: Primary Result
     * ```
     */
    public void demonstrateTimeoutMethods() {
        System.out.println("\n=== Timeout Methods Demo ===");

        // orTimeout example
        CompletableFuture<String> withTimeout = new CompletableFuture<String>()
            .orTimeout(1, TimeUnit.SECONDS);
        
        try {
            withTimeout.join();
        } catch (Exception e) {
            System.out.println("Timeout occurred as expected");
        }

        // completeOnTimeout example
        String result = CompletableFuture.supplyAsync(() -> {
            sleep(2000); // Simulate long operation
            return "Primary Result";
        })
        .completeOnTimeout("Fallback Result", 1, TimeUnit.SECONDS)
        .join();

        System.out.println("Result with timeout fallback: " + result);
    }

    /**
     * Demonstrates async completion methods.
     * 
     * Example:
     * ```java
     * demonstrateAsyncCompletion();
     * // Output:
     * // Waiting for async completion...
     * // Result: Completed Asynchronously
     * ```
     */
    public void demonstrateAsyncCompletion() {
        System.out.println("\n=== Async Completion Demo ===");

        CompletableFuture<String> future = new CompletableFuture<>();

        // Complete async with supplier
        future.completeAsync(() -> {
            sleep(1000);
            return "Completed Asynchronously";
        }, executor);

        System.out.println("Waiting for async completion...");
        System.out.println("Result: " + future.join());
    }

    /**
     * Demonstrates copy and dependent futures.
     * 
     * Example:
     * ```java
     * demonstrateCopyAndDependentFutures();
     * // Output:
     * // Original future value: Random Number
     * // Copied future value: Random Number
     * ```
     */
    public void demonstrateCopyAndDependentFutures() {
        System.out.println("\n=== Copy and Dependent Futures Demo ===");

        // Create original future
        CompletableFuture<Integer> original = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return ThreadLocalRandom.current().nextInt(100);
        });

        // Create a copy
        CompletableFuture<Integer> copy = original.copy();

        // Use both futures
        original.thenAccept(value -> 
            System.out.println("Original future value: " + value));
        copy.thenAccept(value -> 
            System.out.println("Copied future value: " + value));

        // Wait for both to complete
        CompletableFuture.allOf(original, copy).join();
    }

    /**
     * Demonstrates enhanced error handling.
     * 
     * Example:
     * ```java
     * demonstrateErrorHandling();
     * // Output:
     * // Result: Fallback Service Response
     * // Chained result: Chain failed: java.util.concurrent.TimeoutException
     * ```
     */
    public void demonstrateErrorHandling() {
        System.out.println("\n=== Error Handling Demo ===");

        // Handle timeout with fallback
        CompletableFuture<String> futureWithFallback = CompletableFuture
            .supplyAsync(() -> {
                sleep(2000); // Simulate long operation
                return "Primary Service Response";
            })
            .completeOnTimeout("Fallback Service Response", 1, TimeUnit.SECONDS)
            .exceptionally(ex -> "Error occurred: " + ex.getMessage());

        System.out.println("Result: " + futureWithFallback.join());

        // Chain of operations with timeout
        CompletableFuture<String> chainedOperation = CompletableFuture
            .supplyAsync(() -> "Step 1")
            .thenApplyAsync(s -> s + " -> Step 2")
            .orTimeout(1, TimeUnit.SECONDS)
            .exceptionally(ex -> "Chain failed: " + ex.getMessage());

        System.out.println("Chained result: " + chainedOperation.join());
    }

    /**
     * Demonstrates combining futures with timeouts.
     * 
     * Example:
     * ```java
     * demonstrateCombiningFutures();
     * // Output:
     * // All services completed successfully
     * // Completed: Service 1 (delayed 1000ms)
     * // Completed: Service 2 (delayed 2000ms)
     * // Completed: Service 3 (delayed 3000ms)
     * ```
     */
    public void demonstrateCombiningFutures() {
        System.out.println("\n=== Combining Futures Demo ===");

        // Create multiple futures
        List<CompletableFuture<String>> futures = List.of(
            createFutureWithRandomDelay("Service 1"),
            createFutureWithRandomDelay("Service 2"),
            createFutureWithRandomDelay("Service 3")
        );

        // Combine all futures with timeout
        CompletableFuture<Void> combined = CompletableFuture.allOf(
            futures.toArray(new CompletableFuture[0]))
            .orTimeout(2, TimeUnit.SECONDS);

        try {
            combined.join();
            System.out.println("All services completed successfully");
        } catch (Exception e) {
            System.out.println("Some services timed out");
        }

        // Print completed results
        futures.forEach(f -> {
            if (f.isDone() && !f.isCompletedExceptionally()) {
                System.out.println("Completed: " + f.join());
            }
        });
    }

    /**
     * Helper method to create a future with random delay
     */
    private CompletableFuture<String> createFutureWithRandomDelay(String name) {
        return CompletableFuture.supplyAsync(() -> {
            int delay = ThreadLocalRandom.current().nextInt(1000, 3000);
            sleep(delay);
            return name + " (delayed " + delay + "ms)";
        }, executor);
    }

    /**
     * Helper method to sleep
     */
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        CompletableFutureEnhancementsExample example = 
            new CompletableFutureEnhancementsExample();

        example.demonstrateFactoryMethods();
        example.demonstrateTimeoutMethods();
        example.demonstrateAsyncCompletion();
        example.demonstrateCopyAndDependentFutures();
        example.demonstrateErrorHandling();
        example.demonstrateCombiningFutures();
    }
}
