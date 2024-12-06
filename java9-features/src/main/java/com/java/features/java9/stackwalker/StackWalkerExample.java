package com.java.features.java9.stackwalker;

import java.lang.StackWalker.Option;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Demonstrates the Stack-Walking API introduced in Java 9.
 * 
 * What's New in Java 9:
 * -------------------
 * 1. StackWalker class
 * 2. Lazy stack walking
 * 3. Filtered stack traces
 * 4. Better performance than getStackTrace()
 * 
 * Key Benefits:
 * -----------
 * 1. More efficient stack trace processing
 * 2. Better memory usage
 * 3. Flexible filtering options
 * 4. Stream-based API
 */
public class StackWalkerExample {

    /**
     * Demonstrates basic stack walking
     */
    public void demonstrateBasicStackWalk() {
        System.out.println("=== Basic Stack Walk ===");
        
        // Get current stack frame
        StackWalker.getInstance().forEach(frame -> 
            System.out.println("Method: " + frame.getMethodName()));
            
        // Call through multiple methods to show stack
        methodA();
    }

    /**
     * Demonstrates stack walking with specific options
     */
    public void demonstrateStackWalkOptions() {
        System.out.println("\n=== Stack Walk with Options ===");
        
        // Create StackWalker with multiple options
        StackWalker walker = StackWalker.getInstance(
            EnumSet.of(
                Option.SHOW_REFLECT_FRAMES,  // Show reflection frames
                Option.SHOW_HIDDEN_FRAMES    // Show hidden frames
            )
        );
        
        // Walk and print stack
        walker.forEach(frame -> {
            System.out.printf("Class: %s, Method: %s, Line: %d%n",
                frame.getClassName(),
                frame.getMethodName(),
                frame.getLineNumber());
        });
    }

    /**
     * Demonstrates collecting stack frames into a list
     */
    public void demonstrateCollectingFrames() {
        System.out.println("\n=== Collecting Stack Frames ===");
        
        // Collect frames into list
        List<String> stack = StackWalker.getInstance().walk(frames ->
            frames.map(StackWalker.StackFrame::getMethodName)
                  .collect(Collectors.toList())
        );
        
        System.out.println("Method call stack:");
        stack.forEach(method -> System.out.println("- " + method));
    }

    /**
     * Demonstrates filtering stack frames
     */
    public void demonstrateFilteringFrames() {
        System.out.println("\n=== Filtering Stack Frames ===");
        
        // Filter frames from specific package
        StackWalker.getInstance().forEach(frame -> {
            String className = frame.getClassName();
            if (className.startsWith("com.java.features")) {
                System.out.printf("Project frame - Class: %s, Method: %s%n",
                    className,
                    frame.getMethodName());
            }
        });
    }

    /**
     * Demonstrates finding caller class and method
     */
    public void demonstrateFindingCaller() {
        System.out.println("\n=== Finding Caller ===");
        
        // Get immediate caller
        String caller = StackWalker.getInstance().walk(frames ->
            frames.skip(1)  // Skip current method
                  .findFirst()
                  .map(frame -> frame.getClassName() + "#" + frame.getMethodName())
                  .orElse("Unknown caller")
        );
        
        System.out.println("Called by: " + caller);
    }

    /**
     * Demonstrates performance comparison with Thread.getStackTrace()
     */
    public void demonstratePerformanceComparison() {
        System.out.println("\n=== Performance Comparison ===");
        
        // Using Thread.getStackTrace()
        long startTime = System.nanoTime();
        Thread.currentThread().getStackTrace();
        long traditionalTime = System.nanoTime() - startTime;
        
        // Using StackWalker
        startTime = System.nanoTime();
        StackWalker.getInstance().walk(frames -> frames.count());
        long stackWalkerTime = System.nanoTime() - startTime;
        
        System.out.printf("Traditional approach time: %d ns%n", traditionalTime);
        System.out.printf("StackWalker time: %d ns%n", stackWalkerTime);
    }

    /**
     * Demonstrates basic stack walking functionality.
     */
    public void demonstrateBasicWalk() {
        StackWalker walker = StackWalker.getInstance();
        walker.forEach(frame -> 
            System.out.println("Method: " + frame.getMethodName()));
    }

    /**
     * Demonstrates advanced stack walking with options.
     */
    public void demonstrateAdvancedWalk() {
        StackWalker walker = StackWalker.getInstance(
            EnumSet.of(Option.SHOW_REFLECT_FRAMES, Option.SHOW_HIDDEN_FRAMES)
        );
        
        List<String> stack = walker.walk(frames -> 
            frames.map(frame -> String.format("%s(%s:%d)",
                frame.getMethodName(),
                frame.getFileName(),
                frame.getLineNumber()))
            .collect(Collectors.toList())
        );
        
        System.out.println("Full stack trace:");
        stack.forEach(System.out::println);
    }

    /**
     * Demonstrates getting caller class.
     */
    public Class<?> demonstrateCallerClass() {
        return StackWalker.getInstance(Option.RETAIN_CLASS_REFERENCE)
            .getCallerClass();
    }

    /**
     * Helper method to create deeper stack
     */
    private void methodA() {
        methodB();
    }

    private void methodB() {
        methodC();
    }

    private void methodC() {
        System.out.println("\nStack trace through multiple methods:");
        StackWalker.getInstance().forEach(frame ->
            System.out.printf("Method: %s, Line: %d%n",
                frame.getMethodName(),
                frame.getLineNumber())
        );
        
        System.out.println("Called from: " + 
            StackWalker.getInstance().walk(frames ->
                frames.skip(1)
                     .findFirst()
                     .map(StackWalker.StackFrame::getMethodName)
                     .orElse("unknown")));
    }

    /**
     * Custom exception to demonstrate stack walking with exceptions
     */
    private static class CustomException extends Exception {
        public void printStackFrames() {
            System.out.println("\nException stack frames:");
            StackWalker.getInstance(Option.RETAIN_CLASS_REFERENCE)
                .walk(frames -> frames
                    .limit(5)  // Only show top 5 frames
                    .collect(Collectors.toList())
                )
                .forEach(frame -> System.out.printf("at %s.%s(%s:%d)%n",
                    frame.getClassName(),
                    frame.getMethodName(),
                    frame.getFileName(),
                    frame.getLineNumber()));
        }
    }

    /**
     * Demonstrates stack walking with exceptions
     */
    public void demonstrateExceptionStackWalk() {
        System.out.println("\n=== Exception Stack Walk ===");
        
        try {
            throw new CustomException();
        } catch (CustomException e) {
            e.printStackFrames();
        }
    }

    public static void main(String[] args) {
        StackWalkerExample example = new StackWalkerExample();
        
        example.demonstrateBasicStackWalk();
        example.demonstrateStackWalkOptions();
        example.demonstrateCollectingFrames();
        example.demonstrateFilteringFrames();
        example.demonstrateFindingCaller();
        example.demonstratePerformanceComparison();
        example.demonstrateExceptionStackWalk();
        
        System.out.println("=== Basic Stack Walk ===");
        example.demonstrateBasicWalk();
        
        System.out.println("\n=== Advanced Stack Walk ===");
        example.demonstrateAdvancedWalk();
        
        System.out.println("\n=== Caller Class Demo ===");
        System.out.println("Caller class: " + example.demonstrateCallerClass());
        
        System.out.println("\n=== Stack Navigation Demo ===");
        example.methodA();
    }
}
