package com.java.features.java8.base64;

import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.io.*;

/**
 * Demonstrates Base64 encoding and decoding features in Java 8
 */
public class Base64Examples {

    /**
     * Encodes a string using Base64 encoding.
     * 
     * Sample usage:
     * ```java
     * String result = encodeString("Hello World");
     * // Returns: "SGVsbG8gV29ybGQ="
     * ```
     * 
     * @param input The string to encode
     * @return The Base64 encoded string
     */
    public static String encodeString(String input) {
        return Base64.getEncoder()
                .encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Decodes a Base64 encoded string back to its original form.
     * 
     * Sample usage:
     * ```java
     * String encoded = "SGVsbG8gV29ybGQ=";
     * String decoded = decodeString(encoded);
     * // Returns: "Hello World"
     * ```
     * 
     * @param encodedString The Base64 encoded string
     * @return The decoded original string
     */
    public static String decodeString(String encodedString) {
        byte[] decoded = Base64.getDecoder().decode(encodedString);
        return new String(decoded, StandardCharsets.UTF_8);
    }

    /**
     * Encodes a string using URL-safe Base64 encoding.
     * 
     * Sample usage:
     * ```java
     * String result = encodeUrlSafe("Hello World");
     * // Returns: "SGVsbG8gV29ybGQ="
     * ```
     * 
     * @param input The string to encode
     * @return The URL-safe Base64 encoded string
     */
    public static String encodeUrlSafe(String input) {
        return Base64.getUrlEncoder()
                .encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Encodes a string using MIME Base64 encoding for longer content.
     * 
     * Sample usage:
     * ```java
     * String result = encodeMime("Hello World");
     * // Returns: "SGVsbG8gV29ybGQ=\r\n"
     * ```
     * 
     * @param input The string to encode
     * @return The MIME Base64 encoded string
     */
    public static String encodeMime(String input) {
        return Base64.getMimeEncoder()
                .encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Encodes a string using Base64 encoding without padding.
     * 
     * Sample usage:
     * ```java
     * String result = encodeWithoutPadding("Hello World");
     * // Returns: "SGVsbG8gV29ybGQ"
     * ```
     * 
     * @param input The string to encode
     * @return The Base64 encoded string without padding
     */
    public static String encodeWithoutPadding(String input) {
        return Base64.getEncoder().withoutPadding()
                .encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Encodes a file using Base64 encoding and writes the result to another file.
     * 
     * Sample usage:
     * ```java
     * encodeFile("input.txt", "encoded.txt");
     * // Creates encoded.txt containing the Base64 encoded content of input.txt
     * ```
     * 
     * @param inputPath Path to the input file
     * @param outputPath Path where the encoded content will be written
     * @throws IOException If there are issues reading or writing the files
     */
    public static void encodeFile(String inputPath, String outputPath) throws IOException {
        try (InputStream is = new FileInputStream(inputPath)) {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            byte[] encoded = Base64.getEncoder().encode(buffer.toByteArray());
            try (OutputStream os = new FileOutputStream(outputPath)) {
                os.write(encoded);
            }
        }
    }

    /**
     * Decodes a Base64 encoded file back to its original form.
     * 
     * Sample usage:
     * ```java
     * decodeFile("encoded.txt", "decoded.txt");
     * // Creates decoded.txt containing the original content decoded from encoded.txt
     * ```
     * 
     * @param encodedPath Path to the Base64 encoded file
     * @param outputPath Path where the decoded content will be written
     * @throws IOException If there are issues reading or writing the files
     */
    public static void decodeFile(String encodedPath, String outputPath) throws IOException {
        try (InputStream is = new FileInputStream(encodedPath)) {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            byte[] decoded = Base64.getDecoder().decode(buffer.toByteArray());
            try (OutputStream os = new FileOutputStream(outputPath)) {
                os.write(decoded);
            }
        }
    }

    /**
     * Demonstrates streaming Base64 encoding.
     * 
     * Sample usage:
     * ```java
     * encodeStream(new FileInputStream("input.txt"), new FileOutputStream("encoded.txt"));
     * // Creates encoded.txt containing the Base64 encoded content of input.txt
     * ```
     * 
     * @param input The input stream to encode
     * @param output The output stream to write the encoded content
     * @throws IOException If there are issues reading or writing the streams
     */
    public static void encodeStream(InputStream input, OutputStream output) throws IOException {
        try (OutputStream encodedStream = Base64.getEncoder().wrap(output)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                encodedStream.write(buffer, 0, bytesRead);
            }
        }
    }

    /**
     * Demonstrates custom line length for MIME encoding.
     * 
     * Sample usage:
     * ```java
     * String result = encodeMimeWithLineLength("Hello World", 10);
     * // Returns: "SGVsbG8gV29y\r\nbGQ=\r\n"
     * ```
     * 
     * @param input The string to encode
     * @param lineLength The custom line length for MIME encoding
     * @return The MIME encoded string with custom line length
     */
    public static String encodeMimeWithLineLength(String input, int lineLength) {
        return Base64.getMimeEncoder(lineLength, "\r\n".getBytes(StandardCharsets.UTF_8))
                .encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Demonstrates various Base64 encoding/decoding examples.
     * 
     * Sample output:
     * ```
     * Original String: Hello World
     * Encoded String: SGVsbG8gV29ybGQ=
     * Decoded String: Hello World
     * 
     * File encoding/decoding completed successfully
     * ```
     */
    public static void main(String[] args) throws IOException {
        String original = "Hello World";
        String encoded = encodeString(original);
        String decoded = decodeString(encoded);
        System.out.println("Original String: " + original);
        System.out.println("Encoded String: " + encoded);
        System.out.println("Decoded String: " + decoded);
        encodeFile("input.txt", "encoded.txt");
        decodeFile("encoded.txt", "decoded.txt");
        System.out.println("File encoding/decoding completed successfully");
    }
}
