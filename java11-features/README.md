# Java 11 Features Module

This module demonstrates the key features and enhancements introduced in Java 11.

## 1. String API Enhancements

### New String Methods
```java
// isBlank()
String str = "  ";
boolean isEmpty = str.isBlank(); // true

// lines()
String multiline = "line1\nline2\nline3";
Stream<String> lines = multiline.lines();

// strip(), stripLeading(), stripTraading()
String text = "  Hello World  ";
String stripped = text.strip(); // "Hello World"
String leadingStripped = text.stripLeading(); // "Hello World  "
String trailingStripped = text.stripTrailing(); // "  Hello World"

// repeat()
String repeated = "Java".repeat(3); // "JavaJavaJava"
```

### String Processing Examples
```java
public class StringProcessing {
    public static List<String> processMultilineString(String input) {
        return input.lines()
                   .filter(line -> !line.isBlank())
                   .map(String::strip)
                   .collect(Collectors.toList());
    }

    public static String padString(String input, int length) {
        return input.length() >= length 
            ? input 
            : input + " ".repeat(length - input.length());
    }
}
```

## 2. HTTP Client API

### Synchronous Requests
```java
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/data"))
    .header("Content-Type", "application/json")
    .GET()
    .build();

HttpResponse<String> response = 
    client.send(request, HttpResponse.BodyHandlers.ofString());
```

### Asynchronous Requests
```java
HttpClient client = HttpClient.newBuilder()
    .version(Version.HTTP_2)
    .connectTimeout(Duration.ofSeconds(10))
    .build();

CompletableFuture<HttpResponse<String>> futureResponse = 
    client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenApply(response -> {
            System.out.println("Status: " + response.statusCode());
            return response;
        });
```

### WebSocket Support
```java
WebSocket webSocket = HttpClient.newHttpClient()
    .newWebSocketBuilder()
    .buildAsync(URI.create("ws://websocket.example.com"), 
        new WebSocket.Listener() {
            @Override
            public CompletionStage<?> onText(WebSocket webSocket,
                                           CharSequence data,
                                           boolean last) {
                System.out.println("Received: " + data);
                return CompletableFuture.completedFuture(null);
            }
        })
    .join();
```

### Custom Request Bodies
```java
HttpRequest postRequest = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/post"))
    .header("Content-Type", "application/json")
    .POST(HttpRequest.BodyPublishers.ofString(
        "{\"key\":\"value\"}"
    ))
    .build();
```

## Common Issues and Solutions

### HTTP Client Issues
1. **Problem**: Connection timeouts
   **Solution**: Configure appropriate timeouts
   ```java
   HttpClient client = HttpClient.newBuilder()
       .connectTimeout(Duration.ofSeconds(10))
       .build();
   ```

2. **Problem**: SSL/TLS certificate validation
   **Solution**: Configure custom SSL context
   ```java
   SSLContext sslContext = SSLContext.getInstance("TLS");
   sslContext.init(null, trustAllCerts, new SecureRandom());
   
   HttpClient client = HttpClient.newBuilder()
       .sslContext(sslContext)
       .build();
   ```

### String Processing Issues
1. **Problem**: Memory consumption with large strings
   **Solution**: Use streaming operations
   ```java
   // Process large files line by line
   Files.lines(path)
        .filter(line -> !line.isBlank())
        .forEach(System.out::println);
   ```

## Best Practices

1. **HTTP Client**
   - Reuse HttpClient instances
   - Configure appropriate timeouts
   - Use async operations for better scalability
   - Handle errors appropriately

2. **String Processing**
   - Use appropriate string methods for the task
   - Consider memory implications for large strings
   - Use string processing methods in combination with streams

## Performance Considerations

### HTTP Client Performance
```java
// Configure for optimal performance
HttpClient client = HttpClient.newBuilder()
    .version(Version.HTTP_2)
    .executor(Executors.newFixedThreadPool(10))
    .connectTimeout(Duration.ofSeconds(5))
    .priority(1) // HTTP/2 priority
    .build();

// Parallel request processing
List<URI> urls = Arrays.asList(
    URI.create("https://api1.example.com"),
    URI.create("https://api2.example.com")
);

List<CompletableFuture<String>> futures = urls.stream()
    .map(uri -> HttpRequest.newBuilder(uri).GET().build())
    .map(request -> client.sendAsync(request, 
        HttpResponse.BodyHandlers.ofString())
        .thenApply(HttpResponse::body))
    .collect(Collectors.toList());

CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
    .join();
```

### String Processing Performance
```java
// Efficient string processing
public static String processLargeString(String input) {
    return input.lines()
               .parallel()
               .filter(line -> !line.isBlank())
               .map(String::strip)
               .collect(Collectors.joining("\n"));
}
```

## Running the Examples

```bash
# Compile and run
mvn clean install
mvn exec:java -Dexec.mainClass="com.java.features.java11.string.StringAPIEnhancements"

# Run specific tests
mvn test -Dtest=HttpClientTest
```

## Further Reading
- [Java 11 Documentation](https://docs.oracle.com/en/java/javase/11/docs/api/)
- [HTTP Client API Guide](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html)
- [String API Changes](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html)
