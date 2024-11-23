package com.java.features.java11.http;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@DisplayName("HTTP Client Real-World Examples")
class HttpClientExamplesTest {

    // Real-world use case: REST API Client
    @Test
    @DisplayName("REST API Client Implementation")
    void testRestApiClient() {
        RestApiClient client = new RestApiClient("https://api.example.com");
        
        // GET request
        User user = client.getUser(123);
        assertNotNull(user);
        assertEquals("john.doe@example.com", user.email());
        
        // POST request
        Order order = new Order(null, "Product A", 2, 99.99);
        Order createdOrder = client.createOrder(order);
        assertNotNull(createdOrder.id());
        
        // PUT request
        order = new Order(createdOrder.id(), "Product A", 3, 99.99);
        Order updatedOrder = client.updateOrder(order);
        assertEquals(3, updatedOrder.quantity());
    }

    // Real-world use case: Parallel API Requests
    @Test
    @DisplayName("Parallel API Requests")
    void testParallelApiRequests() {
        WeatherService weatherService = new WeatherService();
        List<String> cities = Arrays.asList("London", "New York", "Tokyo", "Paris");
        
        // Fetch weather for multiple cities in parallel
        List<CompletableFuture<WeatherData>> futures = cities.stream()
            .map(weatherService::getWeatherAsync)
            .collect(Collectors.toList());
        
        List<WeatherData> weatherData = CompletableFuture.allOf(
            futures.toArray(new CompletableFuture[0]))
            .thenApply(v -> futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList()))
            .join();
        
        assertEquals(cities.size(), weatherData.size());
        assertTrue(weatherData.stream().allMatch(wd -> wd.temperature() > -50));
    }

    // Real-world use case: File Download with Progress
    @Test
    @DisplayName("File Download with Progress Tracking")
    void testFileDownload() {
        FileDownloader downloader = new FileDownloader();
        ProgressListener listener = new ProgressListener() {
            @Override
            public void onProgress(long bytesRead, long totalBytes) {
                assertTrue(bytesRead <= totalBytes);
                assertTrue(bytesRead >= 0);
            }
        };
        
        byte[] fileContent = downloader.downloadFile(
            "https://example.com/large-file.zip",
            listener
        );
        
        assertNotNull(fileContent);
        assertTrue(fileContent.length > 0);
    }

    // Real-world use case: WebSocket Chat Client
    @Test
    @DisplayName("WebSocket Chat Client")
    void testWebSocketChat() {
        ChatClient chatClient = new ChatClient("wss://chat.example.com");
        
        // Connect and send message
        chatClient.connect();
        assertTrue(chatClient.isConnected());
        
        chatClient.sendMessage("Hello, World!");
        
        // Receive messages
        Message message = chatClient.receiveMessage();
        assertNotNull(message);
        assertEquals("Hello, World!", message.content());
        
        chatClient.disconnect();
        assertFalse(chatClient.isConnected());
    }

    // Supporting classes
    record User(Long id, String email, String name) {}
    record Order(Long id, String product, int quantity, double price) {}
    record WeatherData(String city, double temperature, String condition) {}
    record Message(String sender, String content, long timestamp) {}
    
    // Example implementation of RestApiClient
    static class RestApiClient {
        private final HttpClient client;
        private final String baseUrl;
        
        RestApiClient(String baseUrl) {
            this.baseUrl = baseUrl;
            this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        }
        
        User getUser(long id) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/users/" + id))
                .header("Accept", "application/json")
                .GET()
                .build();
                
            // Implementation details omitted for brevity
            return new User(id, "john.doe@example.com", "John Doe");
        }
        
        Order createOrder(Order order) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/orders"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                    // Convert order to JSON
                    "{\"product\":\"" + order.product() + "\"}"
                ))
                .build();
                
            // Implementation details omitted for brevity
            return new Order(1L, order.product(), order.quantity(), order.price());
        }
        
        Order updateOrder(Order order) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/orders/" + order.id()))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(
                    // Convert order to JSON
                    "{\"quantity\":" + order.quantity() + "}"
                ))
                .build();
                
            // Implementation details omitted for brevity
            return order;
        }
    }
}
