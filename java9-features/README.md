# Java 9 Features Module

## Overview
This module demonstrates the key features and improvements introduced in Java 9, with a particular focus on the new HTTP Client API (incubator module). The examples showcase practical implementations of Java 9's enhancements.

## Key Features Demonstrated

### 1. HTTP Client API (Incubator Module)
Located in `src/main/java/com/java/features/java9/http/HttpClientExample.java`

#### Features:
- Synchronous and asynchronous HTTP requests
- POST requests with JSON payloads
- Custom header management
- Error handling and response processing
- HTTP/2 support (configurable)

#### Example Usage:
```java
// Synchronous GET request
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("http://httpbin.org/get"))
    .header("User-Agent", "Java9-HTTP-Client-Demo")
    .GET()
    .build();
HttpResponse<String> response = client.send(request, 
    HttpResponse.BodyHandler.asString(StandardCharsets.UTF_8));
```

### 2. Module System (Project Jigsaw)
- Module declarations
- Encapsulation and visibility control
- Service provider interface

### 3. Collection Factory Methods
- Immutable List: `List.of()`
- Immutable Set: `Set.of()`
- Immutable Map: `Map.of()`

### 4. Stream API Improvements
- `takeWhile()` and `dropWhile()`
- Stream iteration improvements
- Optional stream conversion

## Project Structure
```
java9-features/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── java/
│   │               └── features/
│   │                   └── java9/
│   │                       ├── http/           # HTTP Client examples
│   │                       ├── collections/    # Collection factory methods
│   │                       ├── stream/         # Stream API improvements
│   │                       └── module/         # Module system examples
│   └── test/
│       └── java/
│           └── com/
│               └── java/
│                   └── features/
│                       └── java9/
│                           └── http/           # HTTP Client tests
├── pom.xml                 # Module-specific Maven configuration
└── README.md              # This documentation
```

## Building and Running

### Prerequisites
- JDK 9
- Maven 3.6+
- Docker (optional)

### Maven Configuration
The module's `pom.xml` includes necessary configurations for Java 9 features:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <source>9</source>
                <target>9</target>
                <compilerArgs>
                    <arg>--add-modules</arg>
                    <arg>jdk.incubator.httpclient</arg>
                </compilerArgs>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### Building the Module
```bash
# Using Maven directly
mvn clean install -P java9

# Using Docker
docker-compose --profile dev up java9-dev
```

### Running Examples

#### 1. HTTP Client Example
```bash
# Using Docker
docker exec java8-11-17-java9-dev-1 java \
    --add-modules jdk.incubator.httpclient \
    -cp /app/java9-features/target/classes \
    com.java.features.java9.http.HttpClientExample

# Using Java directly
java --add-modules jdk.incubator.httpclient \
    -cp target/classes \
    com.java.features.java9.http.HttpClientExample
```

#### 2. Collection Factory Methods Example
```bash
java -cp target/classes \
    com.java.features.java9.collections.CollectionFactoryExample
```

## Docker Development Environment

#### Docker Compose Commands Explained

1. Building and Starting the Container
```bash
docker compose --profile dev up --build java9-dev
```
This command:
- `--profile dev`: Activates the development profile defined in docker-compose.yml
- `up`: Creates and starts the containers
- `--build`: Forces rebuilding of the Docker image, ensuring all changes are included
- `java9-dev`: Specifies the service name for Java 9 development environment

The command will:
- Build a fresh Docker image using the Java 9 Dockerfile
- Mount your source code directory as a volume
- Set up remote debugging on port 5009
- Configure the Java 9 environment with required modules
- Start the container in development mode

2. Additional Useful Commands
```bash
# Start without rebuilding
docker compose --profile dev up java9-dev

# Stop the container
docker compose --profile dev down

# View container logs
docker compose --profile dev logs java9-dev

# Execute commands inside the container
docker compose --profile dev exec java9-dev bash
```

#### Container Features
- Hot reloading of code changes
- Remote debugging enabled
- Maven repository cached
- Source code mounted from host
- JDK 9 with incubator modules pre-configured

#### Environment Variables
The container is configured with:
```
JAVA_TOOL_OPTIONS=--add-modules=jdk.incubator.httpclient
MAVEN_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5009
```

## Debugging

### Remote Debugging
1. The Docker container exposes port 5009 for debugging
2. Configure your IDE with these settings:
   - Host: localhost
   - Port: 5009
   - Command line arguments: --add-modules jdk.incubator.httpclient

### Common Issues and Solutions

#### 1. Module Not Found
Error: `module not found: jdk.incubator.httpclient`
Solution: Add the module to Java command:
```bash
java --add-modules jdk.incubator.httpclient ...
```

#### 2. Compilation Errors
Error: `package jdk.incubator.http does not exist`
Solution: Add compiler argument in pom.xml:
```xml
<compilerArgs>
    <arg>--add-modules</arg>
    <arg>jdk.incubator.httpclient</arg>
</compilerArgs>
```

#### 3. Runtime Errors
Error: `java.lang.NoClassDefFoundError: jdk/incubator/http/HttpClient`
Solution: Add JAVA_TOOL_OPTIONS to environment:
```bash
export JAVA_TOOL_OPTIONS="--add-modules=jdk.incubator.httpclient"
```

## Testing
The module includes unit tests for the HTTP Client implementation:

```bash
# Run tests using Maven
mvn test -P java9

# Run tests in Docker
docker-compose --profile test up java9-dev
```

## API Documentation

### HTTP Client API
The HTTP Client API provides a modern way to make HTTP requests:

#### 1. Synchronous Requests
```java
HttpResponse<String> response = client.send(request, 
    HttpResponse.BodyHandler.asString(StandardCharsets.UTF_8));
```

#### 2. Asynchronous Requests
```java
CompletableFuture<HttpResponse<String>> futureResponse =
    client.sendAsync(request, 
        HttpResponse.BodyHandler.asString(StandardCharsets.UTF_8));
```

#### 3. POST Requests
```java
HttpRequest request = HttpRequest.newBuilder()
    .POST(HttpRequest.BodyProcessor.fromString(jsonBody))
    .build();
```

## Contributing
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## References
- [JEP 110: HTTP/2 Client](https://openjdk.java.net/jeps/110)
- [Java 9 Documentation](https://docs.oracle.com/javase/9/)
