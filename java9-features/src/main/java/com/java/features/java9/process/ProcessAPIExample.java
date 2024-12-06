package com.java.features.java9.process;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Demonstrates the Process API improvements introduced in Java 9.
 * 
 * What's New in Java 9 Process API:
 * ------------------------------
 * 1. Process Information
 *    - Process ID
 *    - Process name
 *    - Command arguments
 *    - Start time
 *    - CPU time
 *    - User
 * 
 * 2. Process Handle Interface
 *    - Manage and monitor processes
 *    - Get process information
 *    - Enumerate processes
 *    - Handle child processes
 * 
 * Key Benefits:
 * -----------
 * 1. Better process management
 * 2. Enhanced system monitoring
 * 3. Improved process control
 * 4. Cross-platform process handling
 */
public class ProcessAPIExample {

    /**
     * Demonstrates how to get information about the current process
     */
    public void demonstrateCurrentProcess() {
        ProcessHandle current = ProcessHandle.current();
        ProcessHandle.Info info = current.info();

        System.out.println("=== Current Process Information ===");
        System.out.println("Process ID: " + current.pid());
        System.out.println("Command: " + info.command().orElse("N/A"));
        System.out.println("Command Line: " + info.commandLine().orElse("N/A"));
        System.out.println("Start Time: " + info.startInstant().orElse(Instant.MIN));
        System.out.println("CPU Time: " + info.totalCpuDuration().orElse(Duration.ZERO));
        System.out.println("User: " + info.user().orElse("N/A"));
    }

    /**
     * Demonstrates how to list and filter system processes
     */
    public void demonstrateProcessListing() {
        System.out.println("\n=== System Processes ===");
        
        // Stream all processes
        Stream<ProcessHandle> processes = ProcessHandle.allProcesses();
        
        // Filter and display processes using more than 100MB of memory
        processes
            .filter(process -> process.info().totalCpuDuration()
                .orElse(Duration.ZERO)
                .getSeconds() > 1)
            .forEach(process -> {
                ProcessHandle.Info info = process.info();
                System.out.printf("PID: %d, Command: %s%n",
                    process.pid(),
                    info.command().orElse("N/A"));
            });
    }

    /**
     * Demonstrates process spawning and management
     */
    public void demonstrateProcessSpawning() {
        System.out.println("\n=== Process Spawning Demo ===");
        
        try {
            // Start a new process (example: notepad)
            ProcessBuilder builder = new ProcessBuilder("notepad.exe");
            Process process = builder.start();
            
            // Get the process handle
            ProcessHandle handle = process.toHandle();
            
            System.out.println("Started process with PID: " + handle.pid());
            
            // Add a termination callback
            handle.onExit().thenAccept(ph -> 
                System.out.println("Process " + ph.pid() + " terminated"));
            
            // Wait a bit then destroy the process
            Thread.sleep(2000);
            handle.destroy();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Demonstrates working with child processes
     */
    public void demonstrateChildProcesses() {
        System.out.println("\n=== Child Processes Demo ===");
        
        ProcessHandle current = ProcessHandle.current();
        
        System.out.println("Current process children:");
        current.children().forEach(child -> {
            System.out.printf("Child PID: %d, Command: %s%n",
                child.pid(),
                child.info().command().orElse("N/A"));
        });
        
        System.out.println("\nCurrent process descendants:");
        current.descendants().forEach(descendant -> {
            System.out.printf("Descendant PID: %d, Command: %s%n",
                descendant.pid(),
                descendant.info().command().orElse("N/A"));
        });
    }

    /**
     * Demonstrates process cleanup and management
     */
    public void demonstrateProcessManagement() {
        System.out.println("\n=== Process Management Demo ===");
        
        try {
            // Start multiple processes
            ProcessBuilder builder1 = new ProcessBuilder("notepad.exe");
            ProcessBuilder builder2 = new ProcessBuilder("calc.exe");
            
            Process p1 = builder1.start();
            Process p2 = builder2.start();
            
            // Store handles
            ProcessHandle h1 = p1.toHandle();
            ProcessHandle h2 = p2.toHandle();
            
            System.out.println("Started processes with PIDs: " + 
                h1.pid() + ", " + h2.pid());
            
            // Demonstrate process checking
            System.out.println("Process " + h1.pid() + 
                " is alive: " + h1.isAlive());
            
            // Clean up processes
            h1.destroy();
            h2.destroyForcibly();
            
            // Wait for termination
            boolean terminated1 = h1.onExit().get().isAlive();
            boolean terminated2 = h2.onExit().get().isAlive();
            
            System.out.println("Processes terminated successfully");
            
        } catch (Exception e) {
            System.out.println("Error in process management: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ProcessAPIExample example = new ProcessAPIExample();
        
        example.demonstrateCurrentProcess();
        example.demonstrateProcessListing();
        example.demonstrateProcessSpawning();
        example.demonstrateChildProcesses();
        example.demonstrateProcessManagement();
    }
}
