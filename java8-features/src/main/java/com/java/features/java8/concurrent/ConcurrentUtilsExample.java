package com.java.features.java8.concurrent;

import java.util.concurrent.locks.StampedLock;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Demonstrates StampedLock and LongAdder features introduced in Java 8
 */
public class ConcurrentUtilsExample {

    /**
     * Example of StampedLock usage in a point class
     */
    public static class Point {
        private double x, y;
        private final StampedLock lock = new StampedLock();

        // Write lock example
        public void move(double deltaX, double deltaY) {
            long stamp = lock.writeLock(); // Exclusive lock
            try {
                x += deltaX;
                y += deltaY;
            } finally {
                lock.unlockWrite(stamp);
            }
        }

        // Optimistic read example
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

        // Convert to read lock example
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
                long ws = lock.tryConvertToWriteLock(stamp); // Try to upgrade to write lock
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
     * Example of LongAdder usage for high-concurrency counting
     */
    public static class ConcurrentCounter {
        private final LongAdder counter = new LongAdder();
        private final DoubleAdder doubleCounter = new DoubleAdder();

        public void increment() {
            counter.increment();
        }

        public void add(long value) {
            counter.add(value);
        }

        public void addDouble(double value) {
            doubleCounter.add(value);
        }

        public long getCount() {
            return counter.sum();
        }

        public double getDoubleCount() {
            return doubleCounter.sum();
        }

        public void reset() {
            counter.reset();
            doubleCounter.reset();
        }
    }

    /**
     * Demonstrates performance comparison between LongAdder and AtomicLong
     */
    public static void performanceComparison() throws InterruptedException {
        final int THREADS = 4;
        final int ITERATIONS = 1_000_000;

        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        ConcurrentCounter counter = new ConcurrentCounter();

        // Measure LongAdder performance
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
     * Example usage of StampedLock and LongAdder
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
