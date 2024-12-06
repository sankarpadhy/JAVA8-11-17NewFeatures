package com.java.features.java8.concurrent;

import java.util.concurrent.locks.StampedLock;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Demonstrates the new concurrent utilities introduced in Java 8, specifically StampedLock
 * and LongAdder/DoubleAdder classes for high-performance concurrent operations.
 * 
 * Key features covered:
 * 1. StampedLock - A capability-based lock with three modes
 *    - Write Lock (Exclusive)
 *    - Read Lock (Shared)
 *    - Optimistic Read (No locking)
 * 
 * 2. LongAdder/DoubleAdder - High-performance counters
 *    - Better than AtomicLong/AtomicDouble for high contention
 *    - Internal striping to reduce contention
 *    - Optimized for increment/add operations
 * 
 * Example usage pattern:
 * ```java
 * // StampedLock example
 * StampedLock lock = new StampedLock();
 * 
 * // Write lock
 * long stamp = lock.writeLock();
 * try {
 *     // make changes
 * } finally {
 *     lock.unlockWrite(stamp);
 * }
 * 
 * // Optimistic read
 * long stamp = lock.tryOptimisticRead();
 * // read values
 * if (!lock.validate(stamp)) {
 *     // fallback to regular read lock
 * }
 * ```
 */
public class ConcurrentUtilsExample {

    /**
     * Example class demonstrating StampedLock usage in a thread-safe point implementation.
     * Shows various locking strategies including write locks, optimistic reads, and lock conversion.
     * 
     * Key features demonstrated:
     * - Write locking for exclusive access
     * - Optimistic reading for better performance
     * - Lock conversion from read to write
     * - Proper lock cleanup with try-finally blocks
     */
    public static class Point {
        private double x, y;
        private final StampedLock lock = new StampedLock();

        /**
         * Demonstrates exclusive write lock usage for updating coordinates.
         * Write locks prevent any concurrent reads or writes.
         * 
         * Example usage:
         * ```java
         * Point p = new Point();
         * p.move(10.0, 20.0);  // Atomic movement
         * ```
         * 
         * @param deltaX Change in x coordinate
         * @param deltaY Change in y coordinate
         */
        public void move(double deltaX, double deltaY) {
            long stamp = lock.writeLock(); // Exclusive lock
            try {
                x += deltaX;
                y += deltaY;
            } finally {
                lock.unlockWrite(stamp);
            }
        }

        /**
         * Demonstrates optimistic read lock usage for calculating distance.
         * Optimistic reads don't block writers and provide better performance
         * when write contention is low.
         * 
         * Example usage:
         * ```java
         * Point p = new Point();
         * double distance = p.distanceFromOrigin();  // Non-blocking read
         * ```
         * 
         * Algorithm:
         * 1. Try optimistic read
         * 2. Read values
         * 3. Validate read
         * 4. If validation fails, use regular read lock
         * 
         * @return Distance from origin (0,0)
         */
        public double distanceFromOrigin() {
            long stamp = lock.tryOptimisticRead(); // Optimistic read
            double currentX = x, currentY = y;
            if (!lock.validate(stamp)) { // Check if a write occurred
                stamp = lock.readLock(); // Get a read lock
                try {
                    currentX = x;
                    currentY = y;
                } finally {
                    lock.unlockRead(stamp);
                }
            }
            return Math.sqrt(currentX * currentX + currentY * currentY);
        }

        /**
         * Demonstrates lock conversion from read to write lock.
         * Shows how to atomically upgrade from a read lock to a write lock
         * when an update is needed based on the read values.
         * 
         * Example usage:
         * ```java
         * Point p = new Point();
         * double newDistance = p.calculateAndUpdateDistance(100.0);
         * ```
         * 
         * Algorithm:
         * 1. Try optimistic read
         * 2. If optimistic read fails, get read lock
         * 3. Calculate new values
         * 4. If update needed, try to convert to write lock
         * 5. If conversion successful, update values
         * 
         * @param distance Target distance from origin
         * @return Actual distance after calculation/update
         */
        public double calculateAndUpdateDistance(double distance) {
            long stamp = lock.tryOptimisticRead();
            double currentX = x, currentY = y;
            if (!lock.validate(stamp)) {
                stamp = lock.readLock();
                try {
                    currentX = x;
                    currentY = y;
                } finally {
                    lock.unlockRead(stamp);
                }
            }

            double newDistance = Math.sqrt(currentX * currentX + currentY * currentY);
            if (newDistance != distance) {
                long ws = lock.tryConvertToWriteLock(stamp);
                if (ws != 0L) { // Upgrade successful
                    try {
                        x = distance * currentX / newDistance;
                        y = distance * currentY / newDistance;
                        return distance;
                    } finally {
                        lock.unlockWrite(ws);
                    }
                }
            }
            return newDistance;
        }
    }

    /**
     * Demonstrates high-performance concurrent counting using LongAdder and DoubleAdder.
     * These classes are optimized for high-concurrency scenarios where multiple threads
     * are frequently updating counters.
     * 
     * Key advantages over Atomic* classes:
     * - Better performance under high contention
     * - Internal striping to reduce thread contention
     * - Optimized for increment/add operations
     * 
     * Example usage:
     * ```java
     * ConcurrentCounter counter = new ConcurrentCounter();
     * 
     * // Multiple threads can safely increment
     * counter.increment();  // Long counter
     * counter.addDouble(1.5);  // Double counter
     * 
     * // Get final results
     * long count = counter.getCount();
     * double doubleCount = counter.getDoubleCount();
     * ```
     */
    public static class ConcurrentCounter {
        private final LongAdder counter = new LongAdder();
        private final DoubleAdder doubleCounter = new DoubleAdder();

        /**
         * Increments the long counter by 1
         */
        public void increment() {
            counter.increment();
        }

        /**
         * Adds a value to the long counter
         * @param value Value to add
         */
        public void add(long value) {
            counter.add(value);
        }

        /**
         * Adds a value to the double counter
         * @param value Value to add
         */
        public void addDouble(double value) {
            doubleCounter.add(value);
        }

        /**
         * Gets the current sum of the long counter
         * @return Current sum
         */
        public long getCount() {
            return counter.sum();
        }

        /**
         * Gets the current sum of the double counter
         * @return Current sum
         */
        public double getDoubleCount() {
            return doubleCounter.sum();
        }

        /**
         * Resets both counters to zero
         */
        public void reset() {
            counter.reset();
            doubleCounter.reset();
        }
    }

    /**
     * Demonstrates performance comparison between LongAdder operations in a multi-threaded environment.
     * This method creates multiple threads that concurrently increment and add to counters.
     * 
     * Example usage:
     * ```java
     * performanceComparison();  // Runs the benchmark
     * ```
     * 
     * @throws InterruptedException if thread execution is interrupted
     */
    public static void performanceComparison() throws InterruptedException {
        final int THREADS = 4;
        final int ITERATIONS = 1_000_000;

        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        ConcurrentCounter counter = new ConcurrentCounter();

        long startTime = System.nanoTime();

        for (int i = 0; i < THREADS; i++) {
            executor.submit(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    counter.increment();
                    counter.add(ThreadLocalRandom.current().nextLong(10));
                    counter.addDouble(ThreadLocalRandom.current().nextDouble());
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long endTime = System.nanoTime();
        System.out.printf("LongAdder final count: %d%n", counter.getCount());
        System.out.printf("DoubleAdder final count: %.2f%n", counter.getDoubleCount());
        System.out.printf("Time taken: %d ms%n", (endTime - startTime) / 1_000_000);
    }

    /**
     * Main method demonstrating the usage of StampedLock and LongAdder in a concurrent environment.
     * Creates separate threads for reading and writing to demonstrate thread safety and performance.
     * 
     * Example output:
     * ```
     * Distance: 1.41
     * Distance: 2.83
     * ...
     * LongAdder final count: 8000000
     * DoubleAdder final count: 2000000.50
     * Time taken: 1234 ms
     * ```
     * 
     * @param args Command line arguments (not used)
     * @throws InterruptedException if thread execution is interrupted
     */
    public static void main(String[] args) throws InterruptedException {
        // StampedLock example
        Point point = new Point();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Writer thread
        executor.submit(() -> {
            for (int i = 0; i < 100; i++) {
                point.move(ThreadLocalRandom.current().nextDouble(), 
                         ThreadLocalRandom.current().nextDouble());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Reader thread
        executor.submit(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.printf("Distance: %.2f%n", point.distanceFromOrigin());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        // LongAdder performance comparison
        performanceComparison();
    }
}
