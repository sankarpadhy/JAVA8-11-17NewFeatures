package com.java.features.java9.http;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

/**
 * Demonstrates the new HTTP Client API introduced in Java 9 (incubator module).
 */
public class HttpClientExample {

    private static final String USER_AGENT = "Java9-HTTP-Client-Demo";

    /**
     * Demonstrates synchronous (blocking) HTTP request.
     */
    public void demonstrateSynchronousRequest() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://httpbin.org/get"))
                .header("User-Agent", USER_AGENT)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString(StandardCharsets.UTF_8));

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }

    /**
     * Demonstrates asynchronous (non-blocking) HTTP request.
     */
    public void demonstrateAsynchronousRequest() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://httpbin.org/get"))
                .header("User-Agent", USER_AGENT)
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> futureResponse =
                client.sendAsync(request, HttpResponse.BodyHandler.asString(StandardCharsets.UTF_8));

        // Process the response asynchronously
        futureResponse.thenAccept(response -> {
            System.out.println("Async Status Code: " + response.statusCode());
            System.out.println("Async Response Body: " + response.body());
        }).exceptionally(e -> {
            System.err.println("Error during async request: " + e.getMessage());
            return null;
        });

        // Wait for the async operation to complete
        futureResponse.join();
    }

    /**
     * Demonstrates how to send a POST request with a body.
     */
    public void demonstratePostRequest() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        String jsonBody = "{\"name\": \"test\", \"description\": \"test data\"}";
        byte[] bodyBytes = jsonBody.getBytes(StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://httpbin.org/post"))
                .header("Content-Type", "application/json")
                .header("User-Agent", USER_AGENT)
                .POST(HttpRequest.BodyProcessor.fromByteArray(bodyBytes))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString(StandardCharsets.UTF_8));

        System.out.println("POST Status: " + response.statusCode());
        System.out.println("POST Response: " + response.body());
    }

    public static void main(String[] args) throws Exception {
        HttpClientExample example = new HttpClientExample();

        System.out.println("=== Synchronous Request Demo ===");
        example.demonstrateSynchronousRequest();

        System.out.println("\n=== Asynchronous Request Demo ===");
        example.demonstrateAsynchronousRequest();

        System.out.println("\n=== POST Request Demo ===");
        example.demonstratePostRequest();
    }
}
