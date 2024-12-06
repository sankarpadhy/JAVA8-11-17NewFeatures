package com.java.features.java11.http;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

@DisplayName("HTTP Client Examples")
class HttpClientExamplesTest {

    @Test
    @DisplayName("HTTP Client Configuration")
    void testHttpClientConfiguration() {
        HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
            
        assertNotNull(client);
    }

    @Test
    @DisplayName("HTTP Request Building")
    void testHttpRequestBuilding() {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://example.com/test"))
            .timeout(Duration.ofMinutes(1))
            .header("Content-Type", "application/json")
            .GET()
            .build();
            
        assertNotNull(request);
        assertEquals("https://example.com/test", request.uri().toString());
        assertTrue(request.headers().firstValue("Content-Type").isPresent());
        assertEquals("application/json", request.headers().firstValue("Content-Type").get());
    }
}
