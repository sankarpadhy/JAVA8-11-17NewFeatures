package com.java.features.java9.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Demonstrates the CompletableFuture enhancements introduced in Java 9.
 * 
 * New Features in Java 9:
 * --------------------
 * 1. New factory methods:
 *    - completedFuture()
 *    - failedFuture()
 *    - delayedExecutor()
 * 
 * 2. New instance methods:
 *    - completeAsync()
 *    - orTimeout()
 *    - completeOnTimeout()
 *    - copy()
 * 
 * Key Benefits:
 * -----------
 * 1. Better timeout handling
 * 2. Improved async completion
 * 3. Enhanced error handling
 * 4. More flexible execution control
 */
public class CompletableFutureEnhancementsExample {

    private final Executor executor = Executors.newFixedThreadPool(3);

    /**
     * Demonstrates new factory methods
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
     * Demonstrates timeout handling methods
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
     * Demonstrates async completion methods
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
     * Demonstrates copy() method and dependent futures
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
     * Demonstrates practical error handling scenarios
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
     * Demonstrates combining multiple futures with timeouts
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
