# Java Feature Evolution (Java 8 to 17) Demo Project

A comprehensive demonstration project showcasing the evolution of Java features from version 8 to 17. This project is structured as a multi-module Maven project with detailed examples, documentation, and tests.

## Project Structure

```
Java8-11-17/
├── java8-features/
│   └── src/
│       ├── main/java/com/java/features/java8/
│       │   ├── lambda/          # Lambda expressions and functional interfaces
│       │   ├── stream/          # Stream API examples
│       │   ├── datetime/        # Date/Time API
│       │   ├── optional/        # Optional API
│       │   ├── nashorn/         # JavaScript engine
│       │   ├── concurrent/      # StampedLock and LongAdder
│       │   ├── base64/          # Base64 encoding/decoding
│       │   ├── annotations/     # Repeating and Type annotations
│       │   ├── map/            # Enhanced Map API
│       │   └── generics/       # Improved type inference
│       └── test/
├── java11-features/
│   └── src/
│       ├── main/java/com/java/features/java11/
│       │   ├── string/         # String API enhancements
│       │   └── http/           # HTTP Client API
│       └── test/
├── java17-features/
│   └── src/
│       ├── main/java/com/java/features/java17/
│       │   ├── records/        # Records
│       │   ├── sealed/         # Sealed classes
│       │   └── pattern/        # Pattern matching
│       └── test/
├── docker/
│   ├── java8/
│   ├── java11/
│   └── java17/
├── pom.xml
└── docker-compose.yml
```

## Features Demonstrated

### Java 8 Features

1. **Lambda Expressions and Functional Interfaces**
   - Custom functional interfaces with default methods
   - Method references
   - Type inference
   - Exception handling in lambdas
   - Currying and composition

2. **Stream API**
   - Filtering and mapping
   - Reduction operations
   - Parallel streams
   - Custom collectors
   - Infinite streams

3. **Optional API**
   - Creation and manipulation
   - Chaining operations
   - Integration with Stream API

4. **Date/Time API**
   - LocalDate, LocalTime, LocalDateTime
   - ZonedDateTime
   - Period and Duration
   - Temporal adjusters

5. **JavaScript Engine (Nashorn)**
   - JavaScript evaluation
   - Java-JavaScript interop
   - Script file loading

6. **Concurrent Utilities**
   - StampedLock with optimistic reading
   - LongAdder for high-concurrency counting
   - DoubleAdder for concurrent double operations

7. **Base64 Encoding/Decoding**
   - Basic encoding/decoding
   - URL-safe encoding
   - MIME encoding
   - Streaming support

8. **Enhanced Annotations**
   - Repeating annotations
   - Type annotations
   - Annotation composition

9. **Map API Enhancements**
   - putIfAbsent
   - computeIfAbsent/Present
   - merge operations
   - forEach iteration

10. **Improved Type Inference**
    - Diamond operator enhancements
    - Generic method type inference
    - Lambda type inference

### Java 11 Features

1. **String API Enhancements**
   - isBlank(), lines(), strip()
   - repeat() method
   - String processing improvements

2. **HTTP Client API**
   - Synchronous and asynchronous requests
   - WebSocket support
   - HTTP/2 support

### Java 17 Features

1. **Records**
   - Compact constructors
   - Custom accessors
   - Pattern matching

2. **Sealed Classes**
   - Inheritance control
   - Pattern matching integration
   - Exhaustive switch statements

3. **Pattern Matching**
   - instanceof patterns
   - Switch expressions
   - Guard patterns

## Getting Started

### Prerequisites
- JDK 8, 11, and 17 installed
- Maven 3.6+
- Docker (optional, for containerized execution)

### Building the Project
```bash
mvn clean install
```

### Running Examples
Each module can be run independently:
```bash
# Java 8 features
mvn exec:java -pl java8-features -Dexec.mainClass="com.java.features.java8.lambda.LambdaExamples"

# Java 11 features
mvn exec:java -pl java11-features -Dexec.mainClass="com.java.features.java11.string.StringAPIEnhancements"

# Java 17 features
mvn exec:java -pl java17-features -Dexec.mainClass="com.java.features.java17.records.RecordExamples"
```

### Docker Support
Run examples in containers:
```bash
docker-compose up java8
docker-compose up java11
docker-compose up java17
```

## Running with Docker

If you don't have Java installed locally, you can use Docker to run and test the application. This project includes Docker configurations for all three Java versions (8, 11, and 17).

### Prerequisites
- Docker installed on your system
- Docker Compose (usually included with Docker Desktop)

### Quick Start with Docker

1. Clone the repository:
```bash
git clone https://github.com/sankarpadhy/JAVA8-11-17NewFeatures.git
cd JAVA8-11-17NewFeatures
```

2. Build and run all modules using Docker Compose:
```bash
docker-compose up --build
```

This will:
- Create containers for Java 8, 11, and 17
- Build the project using Maven
- Run all tests
- Display the test results

### Running Specific Java Versions

To run a specific Java version:

#### Java 8 Features
```bash
docker-compose run java8
```

#### Java 11 Features
```bash
docker-compose run java11
```

#### Java 17 Features
```bash
docker-compose run java17
```

### Running Tests in Docker

To run tests for a specific module:

```bash
# Java 8 Tests
docker-compose run java8 mvn test -pl java8-features

# Java 11 Tests
docker-compose run java11 mvn test -pl java11-features

# Java 17 Tests
docker-compose run java17 mvn test -pl java17-features
```

### Interactive Development

For interactive development using Docker:

1. Start a container with mounted source code:
```bash
docker-compose run --service-ports java17 /bin/bash
```

2. Inside the container, you can:
```bash
# Build the project
mvn clean install

# Run specific tests
mvn test -pl java8-features -Dtest=LambdaExamplesTest

# Run specific classes
mvn exec:java -pl java8-features -Dexec.mainClass="com.java.features.java8.lambda.LambdaExamples"
```

### Docker Container Features

Each Docker container includes:
- OpenJDK (version 8, 11, or 17)
- Maven 3.8+
- Git
- Basic development tools

### Troubleshooting Docker Setup

1. **Container Build Issues**
   ```bash
   # Remove all containers and images
   docker-compose down --rmi all
   # Rebuild from scratch
   docker-compose up --build
   ```

2. **Permission Issues**
   ```bash
   # If you encounter permission issues on Linux/Mac
   sudo chown -R $USER:$USER .
   ```

3. **Memory Issues**
   - Increase Docker memory limit in Docker Desktop settings
   - Default memory allocation might not be sufficient for running all tests

4. **Port Conflicts**
   - The containers use ports 8080-8082
   - Ensure these ports are available or modify docker-compose.yml

### Best Practices

1. **Resource Management**
   - Stop containers when not in use: `docker-compose down`
   - Remove unused images: `docker system prune`

2. **Development Workflow**
   - Use volume mounts for real-time code changes
   - Run tests in containers for consistent environments
   - Use Docker logs for debugging

3. **Performance Tips**
   - Build images in advance for faster startup
   - Use Docker layer caching effectively
   - Consider using multi-stage builds for production

## Testing
Run all tests:
```bash
mvn test
```

Run specific module tests:
```bash
mvn test -pl java8-features
mvn test -pl java11-features
mvn test -pl java17-features
```

## Contributing
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License
This project is licensed under the MIT License - see the LICENSE file for details.
