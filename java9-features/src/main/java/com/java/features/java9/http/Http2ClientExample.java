package com.java.features.java9.http;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;
import java.net.URI;
import java.util.concurrent.CompletableFuture;

/**
 * Demonstrates the new HTTP/2 Client API introduced in Java 9
 * Note: This API was in incubator module in Java 9
 */
public class Http2ClientExample {

    public static void main(String[] args) throws Exception {
        // Create HTTP Client
        HttpClient client = HttpClient.newHttpClient();

        // Create HTTP Request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.github.com/users/github"))
                .GET()
                .build();

        // Synchronous send
        System.out.println("Synchronous request:");
        HttpResponse<String> response = client.send(request, 
            HttpResponse.BodyHandler.asString());
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());

        // Asynchronous send
        System.out.println("\nAsynchronous request:");
        CompletableFuture<HttpResponse<String>> futureResponse = 
            client.sendAsync(request, HttpResponse.BodyHandler.asString());
        
        futureResponse.thenAccept(resp -> {
            System.out.println("Status code: " + resp.statusCode());
            System.out.println("Response body: " + resp.body());
        });

        // Wait for async request to complete
        Thread.sleep(2000);

        // HTTP/2 specific features
        HttpClient http2Client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        System.out.println("\nHTTP/2 specific request:");
        HttpResponse<String> http2Response = http2Client.send(request, 
            HttpResponse.BodyHandler.asString());
        System.out.println("Protocol version: " + http2Response.version());
        System.out.println("Status code: " + http2Response.statusCode());
    }
}
