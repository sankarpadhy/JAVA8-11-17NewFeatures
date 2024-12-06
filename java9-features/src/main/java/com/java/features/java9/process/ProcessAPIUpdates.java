package com.java.features.java9.process;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

/**
 * Demonstrates the new Process API features in Java 9
 */
public class ProcessAPIUpdates {

    public static void main(String[] args) {
        // Get the current process
        ProcessHandle currentProcess = ProcessHandle.current();
        
        // Process information
        ProcessHandle.Info processInfo = currentProcess.info();
        
        // Print process details
        System.out.println("Current Process Details:");
        System.out.println("PID: " + currentProcess.pid());
        
        // Process start time
        Optional<Instant> startTime = processInfo.startInstant();
        startTime.ifPresent(time -> System.out.println("Start Time: " + time));
        
        // Total CPU duration
        Optional<Duration> cpuDuration = processInfo.totalCpuDuration();
        cpuDuration.ifPresent(duration -> System.out.println("CPU Duration: " + duration));
        
        // Command and arguments
        Optional<String[]> arguments = processInfo.arguments();
        arguments.ifPresent(args1 -> System.out.println("Arguments: " + String.join(" ", args1)));
        
        // User
        Optional<String> user = processInfo.user();
        user.ifPresent(u -> System.out.println("User: " + u));
        
        // List all processes
        System.out.println("\nAll Running Java Processes:");
        ProcessHandle.allProcesses()
                    .filter(process -> process.info().command().orElse("").contains("java"))
                    .forEach(process -> {
                        System.out.println("PID: " + process.pid());
                        process.info().command().ifPresent(cmd -> System.out.println("Command: " + cmd));
                        System.out.println("------------------------");
                    });
    }
}
