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
