package com.java.features.java9.varhandle;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demonstrates the Variable Handles API introduced in Java 9.
 * 
 * What's New in Java 9:
 * -------------------
 * 1. VarHandle class for accessing object fields and array elements
 * 2. Fine-grained memory access control
 * 3. Memory fence operations
 * 4. Compare-and-set operations
 * 
 * Key Benefits:
 * -----------
 * 1. Better performance than Reflection
 * 2. More flexible than AtomicInteger/AtomicReference
 * 3. Direct access to memory ordering operations
 * 4. Support for atomic operations
 */
public class VarHandleExample {

    private int plainInt;
    private volatile int volatileInt;
    private final AtomicInteger atomicInt = new AtomicInteger(0);
    private static final VarHandle PLAIN_HANDLE;
    private static final VarHandle VOLATILE_HANDLE;
    private int[] arrayInt = new int[10];
    private static final VarHandle ARRAY_HANDLE;

    // Initialize VarHandles
    static {
        try {
            PLAIN_HANDLE = MethodHandles.lookup()
                .findVarHandle(VarHandleExample.class, "plainInt", int.class);
            
            VOLATILE_HANDLE = MethodHandles.lookup()
                .findVarHandle(VarHandleExample.class, "volatileInt", int.class);
            
            ARRAY_HANDLE = MethodHandles.arrayElementVarHandle(int[].class);
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Demonstrates basic get and set operations
     */
    public void demonstrateBasicAccess() {
        System.out.println("=== Basic Access Demo ===");

        // Plain field access
        PLAIN_HANDLE.set(this, 42);
        int plainValue = (int) PLAIN_HANDLE.get(this);
        System.out.println("Plain value: " + plainValue);

        // Volatile field access
        VOLATILE_HANDLE.set(this, 100);
        int volatileValue = (int) VOLATILE_HANDLE.get(this);
        System.out.println("Volatile value: " + volatileValue);

        // Array element access
        ARRAY_HANDLE.set(arrayInt, 0, 999);
        int arrayValue = (int) ARRAY_HANDLE.get(arrayInt, 0);
        System.out.println("Array[0] value: " + arrayValue);
    }

    /**
     * Demonstrates atomic operations
     */
    public void demonstrateAtomicOperations() {
        System.out.println("\n=== Atomic Operations Demo ===");

        // Compare and set
        boolean success = PLAIN_HANDLE.compareAndSet(this, 42, 43);
        System.out.println("CAS success: " + success);
        System.out.println("New value: " + PLAIN_HANDLE.get(this));

        // Get and add
        int previous = (int) PLAIN_HANDLE.getAndAdd(this, 10);
        System.out.println("Previous value: " + previous);
        System.out.println("Current value: " + PLAIN_HANDLE.get(this));

        // Get and set
        previous = (int) PLAIN_HANDLE.getAndSet(this, 100);
        System.out.println("Previous value: " + previous);
        System.out.println("Current value: " + PLAIN_HANDLE.get(this));
    }

    /**
     * Demonstrates memory ordering operations
     */
    public void demonstrateMemoryOrdering() {
        System.out.println("\n=== Memory Ordering Demo ===");

        // Volatile semantics
        VOLATILE_HANDLE.setVolatile(this, 1);
        int value = (int) VOLATILE_HANDLE.getVolatile(this);
        System.out.println("Volatile value: " + value);

        // Release/Acquire semantics
        VOLATILE_HANDLE.setRelease(this, 2);
        value = (int) VOLATILE_HANDLE.getAcquire(this);
        System.out.println("Release/Acquire value: " + value);

        // Opaque semantics
        VOLATILE_HANDLE.setOpaque(this, 3);
        value = (int) VOLATILE_HANDLE.getOpaque(this);
        System.out.println("Opaque value: " + value);
    }

    /**
     * Demonstrates array operations
     */
    public void demonstrateArrayOperations() {
        System.out.println("\n=== Array Operations Demo ===");

        // Initialize array
        Arrays.fill(arrayInt, 1);

        // Atomic array operations
        int oldValue = (int) ARRAY_HANDLE.getAndAdd(arrayInt, 5, 10);
        System.out.println("Previous array[5]: " + oldValue);
        System.out.println("Current array[5]: " + arrayInt[5]);

        // Compare and set array element
        boolean swapped = ARRAY_HANDLE.compareAndSet(arrayInt, 5, 11, 20);
        System.out.println("Array CAS success: " + swapped);
        System.out.println("Array[5] after CAS: " + arrayInt[5]);
    }

    /**
     * Demonstrates performance comparison
     */
    public void demonstratePerformance() {
        System.out.println("\n=== Performance Comparison ===");

        final int iterations = 10_000_000;

        // VarHandle increment
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            PLAIN_HANDLE.getAndAdd(this, 1);
        }
        long varHandleTime = System.nanoTime() - start;

        // AtomicInteger increment
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            atomicInt.getAndIncrement();
        }
        long atomicTime = System.nanoTime() - start;

        System.out.printf("VarHandle time: %,d ns%n", varHandleTime);
        System.out.printf("AtomicInteger time: %,d ns%n", atomicTime);
    }

    /**
     * Demonstrates a practical use case with custom synchronization
     */
    public void demonstratePracticalUseCase() {
        System.out.println("\n=== Practical Use Case Demo ===");

        // Custom counter with memory fence
        CustomCounter counter = new CustomCounter();

        // Multiple threads incrementing counter
        Thread[] threads = new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        // Wait for threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Final counter value: " + counter.getValue());
    }

    /**
     * Custom counter class using VarHandle
     */
    private static class CustomCounter {
        private int count = 0;
        private static final VarHandle COUNT_HANDLE;

        static {
            try {
                COUNT_HANDLE = MethodHandles.lookup()
                    .findVarHandle(CustomCounter.class, "count", int.class);
            } catch (ReflectiveOperationException e) {
                throw new ExceptionInInitializerError(e);
            }
        }

        public void increment() {
            COUNT_HANDLE.getAndAdd(this, 1);
        }

        public int getValue() {
            return (int) COUNT_HANDLE.getVolatile(this);
        }
    }

    public static void main(String[] args) {
        VarHandleExample example = new VarHandleExample();

        example.demonstrateBasicAccess();
        example.demonstrateAtomicOperations();
        example.demonstrateMemoryOrdering();
        example.demonstrateArrayOperations();
        example.demonstratePerformance();
        example.demonstratePracticalUseCase();
    }
}
