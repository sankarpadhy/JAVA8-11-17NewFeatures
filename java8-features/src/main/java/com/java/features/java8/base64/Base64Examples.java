package com.java.features.java8.base64;

import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.io.*;

/**
 * Demonstrates Base64 encoding and decoding features in Java 8
 */
public class Base64Examples {

    /**
     * Demonstrates basic Base64 encoding
     */
    public static String encodeString(String input) {
        return Base64.getEncoder()
                .encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Demonstrates basic Base64 decoding
     */
    public static String decodeString(String encoded) {
        byte[] decoded = Base64.getDecoder().decode(encoded);
        return new String(decoded, StandardCharsets.UTF_8);
    }

    /**
     * Demonstrates URL-safe Base64 encoding
     */
    public static String encodeUrlSafe(String input) {
        return Base64.getUrlEncoder()
                .encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Demonstrates MIME Base64 encoding for longer content
     */
    public static String encodeMime(String input) {
        return Base64.getMimeEncoder()
                .encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Demonstrates Base64 encoding with no padding
     */
    public static String encodeWithoutPadding(String input) {
        return Base64.getEncoder().withoutPadding()
                .encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Demonstrates Base64 encoding of binary data (file)
     */
    public static String encodeFile(String filePath) throws IOException {
        try (InputStream is = new FileInputStream(filePath)) {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            return Base64.getEncoder().encodeToString(buffer.toByteArray());
        }
    }

    /**
     * Demonstrates Base64 decoding to file
     */
    public static void decodeToFile(String encoded, String filePath) throws IOException {
        byte[] decoded = Base64.getDecoder().decode(encoded);
        try (OutputStream os = new FileOutputStream(filePath)) {
            os.write(decoded);
        }
    }

    /**
     * Demonstrates streaming Base64 encoding
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
     * Demonstrates custom line length for MIME encoding
     */
    public static String encodeMimeWithLineLength(String input, int lineLength) {
        return Base64.getMimeEncoder(lineLength, "\r\n".getBytes(StandardCharsets.UTF_8))
                .encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }
}
