# Java 11 Features Module

## Overview
This module demonstrates the key features and improvements introduced in Java 11, with a particular focus on the standardized HTTP Client API. The examples showcase practical implementations of Java 11's enhancements and improvements over Java 9's incubator HTTP client.

## Key Features Demonstrated

### 1. HTTP Client API (Standardized)
Located in `src/main/java/com/java/features/java11/http/HttpClientExample.java`

#### Features:
- Standardized HTTP Client (no longer incubator)
- Synchronous and asynchronous HTTP requests
- WebSocket support
- HTTP/2 support by default
- Improved response handling
- Better security with TLS 1.3

#### Example Usage:
```java
// Synchronous GET request with the standardized API
HttpClient client = HttpClient.newBuilder()
    .version(Version.HTTP_2)
    .connectTimeout(Duration.ofSeconds(10))
    .build();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("http://httpbin.org/get"))
    .header("User-Agent", "Java11-HTTP-Client")
    .GET()
    .build();
HttpResponse<String> response = client.send(request, 
    HttpResponse.BodyHandlers.ofString());
```

### 2. String API Enhancements
- `String.strip()`
- `String.isBlank()`
- `String.lines()`
- `String.repeat()`

### 3. File API Improvements
- `Path.of()`
- `Files.writeString()`
- `Files.readString()`

### 4. Collection to Array
- `List.toArray(String[]::new)`
- Type inference improvements

## Project Structure
```
java11-features/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── java/
│   │               └── features/
│   │                   └── java11/
│   │                       ├── http/           # HTTP Client examples
│   │                       ├── string/         # String API improvements
│   │                       └── file/           # File API enhancements
│   └── test/
│       └── java/
│           └── com/
│               └── java/
│                   └── features/
│                       └── java11/
│                           └── http/           # HTTP Client tests
├── pom.xml                 # Module-specific Maven configuration
└── README.md              # This documentation
```

## Building and Running

### Prerequisites
- JDK 11
- Maven 3.6+
- Docker (optional)

### Maven Configuration
The module's `pom.xml` includes necessary configurations for Java 11 features:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <source>11</source>
                <target>11</target>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### Building the Module
```bash
# Using Maven directly
mvn clean install -P java11

# Using Docker
docker compose --profile dev up java11-dev
```

## Docker Development Environment

#### Docker Compose Commands Explained

1. Building and Starting the Container
```bash
docker compose --profile dev up --build java11-dev
```
This command:
- `--profile dev`: Activates the development profile defined in docker-compose.yml
- `up`: Creates and starts the containers
- `--build`: Forces rebuilding of the Docker image, ensuring all changes are included
- `java11-dev`: Specifies the service name for Java 11 development environment

The command will:
- Build a fresh Docker image using the Java 11 Dockerfile
- Mount your source code directory as a volume
- Set up remote debugging on port 5011
- Configure the Java 11 environment
- Start the container in development mode

2. Additional Useful Commands
```bash
# Start without rebuilding
docker compose --profile dev up java11-dev

# Stop the container
docker compose --profile dev down

# View container logs
docker compose --profile dev logs java11-dev

# Execute commands inside the container
docker compose --profile dev exec java11-dev bash
```

#### Container Features
- Hot reloading of code changes
- Remote debugging enabled
- Maven repository cached
- Source code mounted from host
- JDK 11 with full HTTP Client support

#### Environment Variables
The container is configured with:
```
MAVEN_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5011
```

## Running Examples

### 1. HTTP Client Example
```bash
# Using Docker
docker exec java8-11-17-java11-dev-1 java \
    -cp /app/java11-features/target/classes \
    com.java.features.java11.http.HttpClientExample

# Using Java directly
java -cp target/classes \
    com.java.features.java11.http.HttpClientExample
```

### 2. String API Examples
```bash
java -cp target/classes \
    com.java.features.java11.string.StringExample
```

## Debugging

### Remote Debugging
1. The Docker container exposes port 5011 for debugging
2. Configure your IDE with these settings:
   - Host: localhost
   - Port: 5011

### Common Issues and Solutions

#### 1. Connection Issues
Error: `Connection refused to localhost:5011`
Solution: Ensure the container is running and debug port is mapped:
```bash
docker compose --profile dev ps
```

#### 2. Class Not Found
Error: `ClassNotFoundException`
Solution: Rebuild the project and restart the container:
```bash
mvn clean install -P java11
docker compose --profile dev restart java11-dev
```

## Testing
The module includes unit tests for all features:

```bash
# Run tests using Maven
mvn test -P java11

# Run tests in Docker
docker compose --profile test up java11-dev
```

## API Documentation

### HTTP Client API Improvements over Java 9
The Java 11 HTTP Client offers several improvements:

#### 1. Simplified Builder Pattern
```java
HttpClient client = HttpClient.newBuilder()
    .version(Version.HTTP_2)
    .connectTimeout(Duration.ofSeconds(10))
    .build();
```

#### 2. Better Response Handling
```java
HttpResponse<String> response = client.send(request, 
    HttpResponse.BodyHandlers.ofString());
```

#### 3. WebSocket Support
```java
WebSocket webSocket = HttpClient.newHttpClient()
    .newWebSocketBuilder()
    .buildAsync(URI.create("ws://echo.websocket.org"), listener)
    .join();
```

## Contributing
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## References
- [JEP 321: HTTP Client](https://openjdk.java.net/jeps/321)
- [Java 11 Documentation](https://docs.oracle.com/en/java/javase/11/)
