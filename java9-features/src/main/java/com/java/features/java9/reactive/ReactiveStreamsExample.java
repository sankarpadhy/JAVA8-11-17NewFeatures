package com.java.features.java9.reactive;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the Reactive Streams API introduced in Java 9.
 * This class showcases reactive programming concepts and patterns
 * using the Flow API for handling asynchronous stream processing
 * with non-blocking back pressure.
 *
 * Key concepts demonstrated:
 * - Publisher-Subscriber pattern
 * - Back pressure handling
 * - Flow control
 * - Error handling
 * - Resource management
 *
 * Components of Reactive Streams:
 * 1. Publisher: Source of data items
 *    - Controls data flow
 *    - Respects subscriber's request
 *
 * 2. Subscriber: Consumer of data items
 *    - Requests data (back pressure)
 *    - Processes items
 *    - Handles completion/errors
 *
 * 3. Subscription: Controls the flow
 *    - Links Publisher and Subscriber
 *    - Manages request amounts
 *    - Allows cancellation
 *
 * 4. Processor: Both Publisher and Subscriber
 *    - Transforms data items
 *    - Acts as intermediate step
 *
 * Example usage:
 * ```java
 * // Create publisher
 * SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
 * 
 * // Create subscriber
 * Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {
 *     public void onSubscribe(Flow.Subscription s) {
 *         s.request(Long.MAX_VALUE);
 *     }
 *     public void onNext(String item) {
 *         System.out.println("Received: " + item);
 *     }
 *     public void onError(Throwable t) { }
 *     public void onComplete() { }
 * };
 * 
 * // Connect them
 * publisher.subscribe(subscriber);
 * ```
 *
 * @see java.util.concurrent.Flow
 * @see java.util.concurrent.SubmissionPublisher
 * @since Java 9
 */
public class ReactiveStreamsExample {

    /**
     * Demonstrates basic Publisher-Subscriber interaction
     */
    public void demonstrateBasicPubSub() {
        System.out.println("=== Basic Publisher-Subscriber Demo ===");
        
        // Create publisher
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        
        // Create subscriber
        SimpleSubscriber<String> subscriber = new SimpleSubscriber<>("Simple");
        
        // Subscribe
        publisher.subscribe(subscriber);
        
        // Publish items
        System.out.println("Publishing items...");
        List<String> items = List.of("Item 1", "Item 2", "Item 3");
        items.forEach(publisher::submit);
        
        // Close publisher
        publisher.close();
        
        // Wait a bit for processing to complete
        sleep(1000);
    }

    /**
     * Demonstrates a Processor that transforms data
     */
    public void demonstrateProcessor() {
        System.out.println("\n=== Processor Demo ===");
        
        // Create publisher
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        
        // Create processor
        TransformProcessor processor = new TransformProcessor();
        
        // Create subscriber
        SimpleSubscriber<String> subscriber = new SimpleSubscriber<>("Processor");
        
        // Connect the flow
        publisher.subscribe(processor);
        processor.subscribe(subscriber);
        
        // Publish items
        System.out.println("Publishing items through processor...");
        List<String> items = List.of("hello", "reactive", "streams");
        items.forEach(publisher::submit);
        
        // Close publisher
        publisher.close();
        
        // Wait a bit for processing to complete
        sleep(1000);
    }

    /**
     * Demonstrates back pressure handling
     */
    public void demonstrateBackPressure() {
        System.out.println("\n=== Back Pressure Demo ===");
        
        // Create publisher
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        
        // Create slow subscriber
        SlowSubscriber subscriber = new SlowSubscriber();
        
        // Subscribe
        publisher.subscribe(subscriber);
        
        // Publish many items quickly
        System.out.println("Publishing items rapidly...");
        for (int i = 1; i <= 10; i++) {
            System.out.println("Publishing: " + i);
            publisher.submit(i);
        }
        
        // Close publisher
        publisher.close();
        
        // Wait for processing to complete
        sleep(3000);
    }

    /**
     * Simple subscriber implementation
     */
    private static class SimpleSubscriber<T> implements Flow.Subscriber<T> {
        private Flow.Subscription subscription;
        private final String name;
        
        public SimpleSubscriber(String name) {
            this.name = name;
        }
        
        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            System.out.println(name + " Subscriber: Subscribed");
            subscription.request(1); // Request one item
        }
        
        @Override
        public void onNext(T item) {
            System.out.println(name + " Subscriber: Received - " + item);
            subscription.request(1); // Request next item
        }
        
        @Override
        public void onError(Throwable error) {
            System.out.println(name + " Subscriber: Error - " + error.getMessage());
        }
        
        @Override
        public void onComplete() {
            System.out.println(name + " Subscriber: Completed");
        }
    }

    /**
     * Processor that transforms strings to uppercase
     */
    private static class TransformProcessor 
            extends SubmissionPublisher<String>
            implements Flow.Processor<String, String> {
        
        private Flow.Subscription subscription;
        
        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }
        
        @Override
        public void onNext(String item) {
            // Transform and submit
            submit(item.toUpperCase());
            subscription.request(1);
        }
        
        @Override
        public void onError(Throwable error) {
            error.printStackTrace();
        }
        
        @Override
        public void onComplete() {
            close();
        }
    }

    /**
     * Slow subscriber to demonstrate back pressure
     */
    private static class SlowSubscriber implements Flow.Subscriber<Integer> {
        private Flow.Subscription subscription;
        
        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            System.out.println("Slow Subscriber: Subscribed");
            subscription.request(1);
        }
        
        @Override
        public void onNext(Integer item) {
            System.out.println("Slow Subscriber: Processing - " + item);
            
            // Simulate slow processing
            sleep(500);
            
            subscription.request(1);
        }
        
        @Override
        public void onError(Throwable error) {
            System.out.println("Slow Subscriber: Error - " + error.getMessage());
        }
        
        @Override
        public void onComplete() {
            System.out.println("Slow Subscriber: Completed");
        }
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
        ReactiveStreamsExample example = new ReactiveStreamsExample();
        
        example.demonstrateBasicPubSub();
        example.demonstrateProcessor();
        example.demonstrateBackPressure();
    }
}
