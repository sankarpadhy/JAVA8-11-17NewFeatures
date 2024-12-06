package com.java.features.java8.concurrent;

import java.util.concurrent.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Demonstrates the concurrent programming features introduced in Java 8.
 * This class showcases CompletableFuture, parallel array operations,
 * and enhanced concurrent collections.
 * 
 * Sample usage:
 * ```java
 * ConcurrentExamples examples = new ConcurrentExamples();
 * 
 * // Async computation
 * CompletableFuture<String> future = examples.asyncComputation("input");
 * future.thenAccept(System.out::println);
 * 
 * // Parallel array sorting
 * int[] array = {5, 3, 1, 4, 2};
 * examples.parallelSort(array);
 * ```
 */
public class ConcurrentExamples {

    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    /**
     * Demonstrates basic CompletableFuture usage for async computation.
     * 
     * Sample usage:
     * ```java
     * CompletableFuture<String> future = asyncComputation("Hello");
     * future.thenAccept(result -> System.out.println(result));
     * // Eventually prints: "Processed: Hello"
     * ```
     * 
     * @param input The input string to process
     * @return CompletableFuture containing the processed result
     */
    public CompletableFuture<String> asyncComputation(String input) {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate some processing
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Processed: " + input;
        }, executor);
    }

    /**
     * Demonstrates chaining multiple async operations using thenCompose.
     * 
     * Sample usage:
     * ```java
     * CompletableFuture<Integer> future = chainedAsyncComputation("123");
     * future.thenAccept(result -> System.out.println(result));
     * // Eventually prints the length of the processed string
     * ```
     * 
     * @param input The input string to process
     * @return CompletableFuture containing the final result
     */
    public CompletableFuture<Integer> chainedAsyncComputation(String input) {
        return asyncComputation(input)
                .thenCompose(processed -> CompletableFuture.supplyAsync(
                    () -> processed.length(),
                    executor
                ));
    }

    /**
     * Demonstrates combining results from multiple CompletableFutures.
     * 
     * Sample usage:
     * ```java
     * CompletableFuture<String> future = combineResults("Hello", "World");
     * future.thenAccept(System.out::println);
     * // Eventually prints: "Hello - World"
     * ```
     * 
     * @param input1 First input string
     * @param input2 Second input string
     * @return CompletableFuture containing the combined result
     */
    public CompletableFuture<String> combineResults(String input1, String input2) {
        CompletableFuture<String> future1 = asyncComputation(input1);
        CompletableFuture<String> future2 = asyncComputation(input2);
        
        return future1.thenCombine(future2, (result1, result2) -> 
            result1 + " - " + result2
        );
    }

    /**
     * Demonstrates parallel array sorting using Arrays.parallelSort.
     * 
     * Sample usage:
     * ```java
     * int[] array = {5, 3, 1, 4, 2};
     * parallelSort(array);
     * System.out.println(Arrays.toString(array));
     * // Prints: [1, 2, 3, 4, 5]
     * ```
     * 
     * @param array The array to sort
     */
    public void parallelSort(int[] array) {
        Arrays.parallelSort(array);
    }

    /**
     * Demonstrates parallel array prefix operations.
     * 
     * Sample usage:
     * ```java
     * double[] array = {1.0, 2.0, 3.0, 4.0};
     * parallelPrefix(array);
     * // Array becomes: [1.0, 3.0, 6.0, 10.0]
     * ```
     * 
     * @param array The array to process
     */
    public void parallelPrefix(double[] array) {
        Arrays.parallelPrefix(array, Double::sum);
    }

    /**
     * Demonstrates exception handling in CompletableFuture.
     * 
     * Sample usage:
     * ```java
     * CompletableFuture<String> future = handleErrors("input");
     * future.thenAccept(System.out::println);
     * // Prints either the result or "Error occurred"
     * ```
     * 
     * @param input The input string
     * @return CompletableFuture with error handling
     */
    public CompletableFuture<String> handleErrors(String input) {
        return CompletableFuture.supplyAsync(() -> {
            if (input == null) {
                throw new IllegalArgumentException("Input cannot be null");
            }
            return "Processed: " + input;
        }).exceptionally(throwable -> 
            "Error occurred: " + throwable.getMessage()
        );
    }

    /**
     * Demonstrates timeout handling with CompletableFuture.
     * 
     * Sample usage:
     * ```java
     * CompletableFuture<String> future = withTimeout("input", 2);
     * future.thenAccept(System.out::println);
     * // Prints result or times out after specified seconds
     * ```
     * 
     * @param input The input string
     * @param timeoutSeconds Timeout duration in seconds
     * @return CompletableFuture with timeout handling
     */
    public CompletableFuture<String> withTimeout(String input, long timeoutSeconds) {
        CompletableFuture<String> future = asyncComputation(input);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        
        CompletableFuture<String> timeout = new CompletableFuture<>();
        scheduler.schedule(() -> timeout.complete("Timeout occurred"), timeoutSeconds, TimeUnit.SECONDS);
        
        return CompletableFuture.anyOf(future, timeout)
                .thenApply(result -> (String) result)
                .whenComplete((r, t) -> scheduler.shutdown());
    }

    /**
     * Demonstrates parallel collection processing with CompletableFuture.
     * 
     * Sample usage:
     * ```java
     * List<String> inputs = Arrays.asList("A", "B", "C");
     * CompletableFuture<List<String>> future = processInParallel(inputs);
     * future.thenAccept(System.out::println);
     * // Prints processed results
     * ```
     * 
     * @param inputs List of input strings
     * @return CompletableFuture containing list of processed results
     */
    public CompletableFuture<List<String>> processInParallel(List<String> inputs) {
        List<CompletableFuture<String>> futures = inputs.stream()
            .map(this::asyncComputation)
            .collect(java.util.stream.Collectors.toList());
        
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
            futures.toArray(new CompletableFuture[0])
        );
        
        return allFutures.thenApply(v -> 
            futures.stream()
                   .map(CompletableFuture::join)
                   .collect(java.util.stream.Collectors.toList())
        );
    }

    /**
     * Demonstrates async task scheduling with CompletableFuture.
     * 
     * Sample usage:
     * ```java
     * CompletableFuture<String> future = scheduleTask("task", 2);
     * future.thenAccept(System.out::println);
     * // Prints result after specified delay
     * ```
     * 
     * @param input The input string
     * @param delaySeconds Delay in seconds
     * @return CompletableFuture that completes after the delay
     */
    public CompletableFuture<String> scheduleTask(String input, long delaySeconds) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        
        CompletableFuture<String> future = new CompletableFuture<>();
        
        scheduler.schedule(() -> {
            future.complete("Delayed task completed: " + input);
            scheduler.shutdown();
        }, delaySeconds, TimeUnit.SECONDS);
        
        return future;
    }

    /**
     * Cleanup method to shut down the executor service.
     * Should be called when the instance is no longer needed.
     */
    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
