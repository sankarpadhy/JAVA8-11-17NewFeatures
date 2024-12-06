package com.java.features.java11.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Demonstrates File API enhancements introduced in Java 11.
 * Includes new methods for reading and writing files.
 */
public class FileAPIEnhancements {

    /**
     * Demonstrates reading a file as a String.
     * New method Files.readString() simplifies reading text files.
     * 
     * Example:
     * ```java
     * Path file = Path.of("example.txt");
     * Files.writeString(file, "Hello, World!");
     * String content = demonstrateReadString(file);
     * System.out.println(content); // Output: Hello, World!
     * ```
     * 
     * @param path the path to the file
     * @return the content of the file as a string
     * @throws IOException if an I/O error occurs
     */
    public String demonstrateReadString(Path path) throws IOException {
        return Files.readString(path);
    }

    /**
     * Demonstrates writing a String to a file.
     * New method Files.writeString() simplifies writing text to files.
     * 
     * Example:
     * ```java
     * Path file = Path.of("example.txt");
     * demonstrateWriteString(file, "Hello, World!");
     * // Creates file with content: Hello, World!
     * ```
     * 
     * @param path the path to the file
     * @param content the string to write
     * @throws IOException if an I/O error occurs
     */
    public void demonstrateWriteString(Path path, String content) throws IOException {
        Files.writeString(path, content, StandardOpenOption.CREATE);
    }

    /**
     * Demonstrates checking if a file is readable/writable/executable.
     * 
     * Example:
     * ```java
     * Path file = Path.of("example.txt");
     * demonstrateFilePermissions(file);
     * // Output:
     * // File permissions for example.txt:
     * // Readable: true
     * // Writable: true
     * // Executable: false
     * ```
     * 
     * @param path the path to check
     */
    public void demonstrateFilePermissions(Path path) {
        System.out.println("File permissions for " + path.getFileName() + ":");
        System.out.println("Readable: " + Files.isReadable(path));
        System.out.println("Writable: " + Files.isWritable(path));
        System.out.println("Executable: " + Files.isExecutable(path));
    }

    /**
     * Demonstrates creating a temporary file with custom prefix and suffix.
     * 
     * Example:
     * ```java
     * Path temp = demonstrateCreateTempFile("prefix-", ".txt");
     * System.out.println("Created temp file: " + temp);
     * // Output: Created temp file: /tmp/prefix-randomchars.txt
     * ```
     * 
     * @param prefix the prefix for the temp file name
     * @param suffix the suffix for the temp file name
     * @return the path to the created temp file
     * @throws IOException if an I/O error occurs
     */
    public Path demonstrateCreateTempFile(String prefix, String suffix) throws IOException {
        return Files.createTempFile(prefix, suffix);
    }

    public static void main(String[] args) {
        FileAPIEnhancements demo = new FileAPIEnhancements();
        
        try {
            // Create and write to a file
            Path file = Path.of("example.txt");
            demo.demonstrateWriteString(file, "Hello, Java 11!");
            
            // Read from the file
            String content = demo.demonstrateReadString(file);
            System.out.println("File content: " + content);
            
            // Check file permissions
            demo.demonstrateFilePermissions(file);
            
            // Create a temp file
            Path tempFile = demo.demonstrateCreateTempFile("java11-", ".tmp");
            System.out.println("Created temp file: " + tempFile);
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
