# Java 8 Features Module

This module demonstrates the key features introduced in Java 8, providing practical examples and test cases for each feature.

## Table of Contents
1. [Lambda Expressions](#lambda-expressions)
2. [Stream API](#stream-api)
3. [Optional Class](#optional-class)
4. [Default Methods](#default-methods)
5. [Method References](#method-references)
6. [Date/Time API](#datetime-api)
7. [CompletableFuture](#completablefuture)
8. [Nashorn JavaScript Engine](#nashorn-javascript-engine)
9. [Memory Management and Performance](#memory-management-and-performance)

## Lambda Expressions

### Overview
Lambda expressions enable functional programming in Java by providing a concise way to represent anonymous functions.

### Examples
```java
// Basic lambda
Runnable runnable = () -> System.out.println("Hello Lambda!");

// Lambda with parameters
Comparator<String> comparator = (s1, s2) -> s1.compareTo(s2);

// Lambda with multiple statements
Consumer<String> consumer = message -> {
    String upperCase = message.toUpperCase();
    System.out.println(upperCase);
};
```

### Key Classes
- `EventSystem`: Demonstrates event handling using lambdas
- `LambdaExamples`: Collection of lambda usage patterns
- `FunctionalInterfaceExamples`: Custom functional interfaces

## Stream API

### Overview
Stream API provides a functional approach to process collections of objects.

### Examples
```java
// Filtering and mapping
List<String> filtered = list.stream()
    .filter(s -> s.startsWith("A"))
    .map(String::toUpperCase)
    .collect(Collectors.toList());

// Parallel processing
long count = list.parallelStream()
    .filter(s -> s.length() > 5)
    .count();
```

### Key Operations
- Filter: `filter()`
- Map: `map()`, `flatMap()`
- Reduce: `reduce()`, `collect()`
- Find: `findFirst()`, `findAny()`
- Match: `anyMatch()`, `allMatch()`, `noneMatch()`

## Optional Class

### Overview
Optional helps handle null values more gracefully and prevents NullPointerException.

### Examples
```java
// Creating Optional
Optional<String> optional = Optional.of("value");
Optional<String> empty = Optional.empty();

// Using Optional
String result = optional
    .filter(s -> s.length() > 5)
    .map(String::toUpperCase)
    .orElse("default");
```

### Best Practices
- Never use `Optional.get()` without checking `isPresent()`
- Prefer `orElse()` or `orElseGet()` over `get()`
- Don't use Optional as method parameter
- Use Optional as return type when null is possible

## Default Methods

### Overview
Default methods allow adding new methods to interfaces without breaking existing implementations.

### Examples
```java
public interface MyInterface {
    default void newMethod() {
        System.out.println("Default implementation");
    }
}
```

### Use Cases
- API evolution
- Multiple inheritance behavior
- Optional functionality in interfaces

## Method References

### Overview
Method references provide a shorthand notation for lambda expressions.

### Types
1. Static Methods: `ClassName::staticMethod`
2. Instance Methods: `instance::method`
3. Constructor References: `ClassName::new`

### Examples
```java
// Static method reference
Function<String, Integer> parser = Integer::parseInt;

// Instance method reference
String str = "Hello";
Supplier<Integer> lengthSupplier = str::length;

// Constructor reference
Supplier<List<String>> listSupplier = ArrayList::new;
```

## DateTime API

### Overview
The new Date/Time API provides immutable and thread-safe date/time classes.

### Key Classes
- `LocalDate`: Date without time
- `LocalTime`: Time without date
- `LocalDateTime`: Date and time
- `ZonedDateTime`: Date and time with time zone
- `Duration`: Time-based amount of time
- `Period`: Date-based amount of time

### Examples
```java
// Current date/time
LocalDate today = LocalDate.now();
LocalTime now = LocalTime.now();
LocalDateTime dateTime = LocalDateTime.now();

// Time zones
ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"));

// Parsing and formatting
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
LocalDate parsed = LocalDate.parse("2023-01-01", formatter);
```

## CompletableFuture

### Overview
CompletableFuture provides a way to write asynchronous, non-blocking code.

### Features
- Chaining operations
- Exception handling
- Combining multiple futures
- Timeout handling

### Examples
```java
CompletableFuture<String> future = CompletableFuture
    .supplyAsync(() -> "Hello")
    .thenApply(String::toUpperCase)
    .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
```

## Nashorn JavaScript Engine

### Overview
Nashorn provides JavaScript runtime for executing JavaScript code from Java applications.

### Examples
```java
ScriptEngineManager manager = new ScriptEngineManager();
ScriptEngine engine = manager.getEngineByName("nashorn");
engine.eval("print('Hello from JavaScript!');");
```

### Features
- JavaScript execution
- Java-JavaScript interop
- Script file loading
- JavaScript object manipulation

## Memory Management and Performance

### JVM Memory Structure in Java 8

1. **Heap Memory**
   ```
   Heap
   ├── Young Generation
   │   ├── Eden Space
   │   ├── Survivor Space 1
   │   └── Survivor Space 2
   └── Old Generation
   ```

2. **Memory Settings**
   ```bash
   # JVM Arguments
   -Xms2g                 # Initial heap size
   -Xmx4g                 # Maximum heap size
   -XX:MetaspaceSize=256m # Initial Metaspace size
   -XX:MaxMetaspaceSize=512m # Maximum Metaspace size
   ```

### Garbage Collection

1. **Available Collectors**
   ```bash
   # Serial GC
   -XX:+UseSerialGC

   # Parallel GC (Default in Java 8)
   -XX:+UseParallelGC
   -XX:ParallelGCThreads=4

   # CMS (Concurrent Mark Sweep)
   -XX:+UseConcMarkSweepGC

   # G1 GC
   -XX:+UseG1GC
   ```

2. **GC Monitoring**
   ```bash
   # Enable GC logging
   -XX:+PrintGCDetails
   -XX:+PrintGCDateStamps
   -Xloggc:gc.log
   ```

### Performance Optimization

1. **Lambda and Stream Optimization**
   ```java
   // Avoid excessive boxing/unboxing
   IntStream.range(0, 1000)  // Better than Stream<Integer>
           .filter(i -> i % 2 == 0)
           .sum();

   // Use parallel streams appropriately
   list.parallelStream()     // Only for CPU-intensive tasks
       .filter(heavy::compute)
       .collect(Collectors.toList());
   ```

2. **Collection Performance**
   ```java
   // ArrayList vs LinkedList
   List<String> arrayList = new ArrayList<>();  // Better for random access
   List<String> linkedList = new LinkedList<>(); // Better for insertions

   // HashMap initial capacity
   Map<String, String> map = new HashMap<>(initialCapacity);
   ```

3. **Memory Leaks Prevention**
   ```java
   // Proper resource cleanup
   try (Stream<String> stream = Files.lines(path)) {
       // Process stream
   }

   // Clear ThreadLocal
   private static final ThreadLocal<User> userContext = new ThreadLocal<>();
   // Clear when done
   userContext.remove();
   ```

### Profiling and Monitoring

1. **JVM Tools**
   ```bash
   # Memory dump
   jmap -dump:format=b,file=heap.bin <pid>

   # Thread dump
   jstack <pid>

   # JVM statistics
   jstat -gcutil <pid> 1000
   ```

2. **Visual VM Configuration**
   ```properties
   # visualvm.properties
   visualvm.display.name=Java8App
   visualvm.jmx.port=3333
   ```

### Performance Testing

1. **JMH Benchmarks**
   ```java
   @Benchmark
   @BenchmarkMode(Mode.AverageTime)
   @OutputTimeUnit(TimeUnit.MICROSECONDS)
   public void measureStreamPerformance() {
       // Benchmark code
   }
   ```

2. **Load Testing**
   ```xml
   <dependency>
       <groupId>org.apache.jmeter</groupId>
       <artifactId>ApacheJMeter_core</artifactId>
       <version>5.5</version>
       <scope>test</scope>
   </dependency>
   ```

### Best Practices

1. **Memory Management**
   - Use appropriate collection sizes
   - Clear collections when no longer needed
   - Avoid memory leaks in caches
   - Monitor heap usage

2. **Stream Operations**
   - Use primitive streams when possible
   - Close streams properly
   - Consider parallel processing overhead
   - Avoid infinite streams

3. **Resource Management**
   - Use try-with-resources
   - Implement AutoCloseable
   - Clear ThreadLocal variables
   - Monitor thread pools

### Monitoring Configuration

1. **JMX Setup**
   ```xml
   <!-- Enable JMX -->
   <jvmArguments>
       -Dcom.sun.management.jmxremote
       -Dcom.sun.management.jmxremote.port=9010
       -Dcom.sun.management.jmxremote.authenticate=false
       -Dcom.sun.management.jmxremote.ssl=false
   </jvmArguments>
   ```

2. **Metrics Collection**
   ```java
   // Custom metrics
   @Metric
   private Counter requestCounter;
   
   // Record metrics
   requestCounter.increment();
   ```

### Common Performance Issues

1. **Memory Leaks**
   - Static collections not cleared
   - Unclosed resources
   - ThreadLocal variables not removed
   - Listener references not removed

2. **CPU Usage**
   - Excessive object creation
   - Inefficient stream operations
   - Unnecessary boxing/unboxing
   - Thread contention

3. **I/O Operations**
   - Unbuffered I/O
   - Resource leaks
   - Blocking operations in loops
   - Large file operations

### Troubleshooting Tools

1. **Memory Analysis**
   ```bash
   # Generate heap dump
   jmap -dump:live,format=b,file=heap.bin <pid>

   # Analyze with MAT
   java -jar mat.jar heap.bin
   ```

2. **Thread Analysis**
   ```bash
   # Generate thread dump
   jstack -l <pid> > thread_dump.txt

   # CPU sampling
   jcmd <pid> JFR.start
   ```

3. **Performance Monitoring**
   ```bash
   # Basic monitoring
   jstat -gcutil <pid> 1000

   # Detailed monitoring
   jcmd <pid> VM.flags
   ```

### Performance Checklist

1. **Memory**
   - [ ] Appropriate heap size
   - [ ] GC configuration
   - [ ] Memory leak monitoring
   - [ ] Collection sizing

2. **Threading**
   - [ ] Thread pool sizing
   - [ ] Deadlock detection
   - [ ] Resource contention
   - [ ] Thread dump analysis

3. **Resource Usage**
   - [ ] I/O operations
   - [ ] Connection pooling
   - [ ] Cache configuration
   - [ ] Resource cleanup

## Testing

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=StreamExamplesTest

# Run with coverage
mvn clean test jacoco:report
```

### Test Categories
- Unit Tests: Basic feature testing
- Integration Tests: Complex scenarios
- Performance Tests: Stream and parallel operations

## Testing Configuration

### Maven Test Setup

1. **Surefire Plugin Configuration**
   ```xml
   <plugin>
       <groupId>org.apache.maven.plugins</groupId>
       <artifactId>maven-surefire-plugin</artifactId>
       <version>3.1.2</version>
       <configuration>
           <!-- Run in current JVM for Java 8 compatibility -->
           <forkCount>0</forkCount>
           <reuseForks>false</reuseForks>
           <!-- Disable parallel execution for thread-safety -->
           <parallel>none</parallel>
           <!-- Java 8 specific arguments -->
           <argLine>
               -Xmx512m
               -XX:MaxPermSize=256m
               -Djava.util.logging.config.file=src/test/resources/logging.properties
           </argLine>
           <!-- Include/Exclude specific test categories -->
           <includes>
               <include>**/*Test.java</include>
           </includes>
           <excludes>
               <exclude>**/*IntegrationTest.java</exclude>
           </excludes>
       </configuration>
   </plugin>
   ```

2. **Test Categories**
   ```java
   // Unit test category
   @Tag("unit")
   class LambdaExamplesTest {
       @Test
       void testLambdaExpression() {
           // Test implementation
       }
   }

   // Integration test category
   @Tag("integration")
   class StreamOperationsTest {
       @Test
       void testParallelStream() {
           // Test implementation
       }
   }
   ```

### Test Organization

1. **Package Structure**
   ```
   src/test/java/
   ├── com.java.features.java8
   │   ├── lambda/
   │   │   ├── LambdaExamplesTest.java
   │   │   └── FunctionalInterfaceTest.java
   │   ├── stream/
   │   │   ├── StreamExamplesTest.java
   │   │   └── CollectorExamplesTest.java
   │   ├── datetime/
   │   │   └── DateTimeExamplesTest.java
   │   └── optional/
   │       └── OptionalExamplesTest.java
   ```

2. **Test Resources**
   ```
   src/test/resources/
   ├── logging.properties
   ├── test-data/
   │   ├── stream-test-data.json
   │   └── datetime-test-data.csv
   └── junit-platform.properties
   ```

### Test Coverage Configuration

1. **JaCoCo Plugin Setup**
   ```xml
   <plugin>
       <groupId>org.jacoco</groupId>
       <artifactId>jacoco-maven-plugin</artifactId>
       <version>0.8.8</version>
       <configuration>
           <excludes>
               <!-- Exclude generated code -->
               <exclude>**/generated/**</exclude>
           </excludes>
           <rules>
               <rule>
                   <element>CLASS</element>
                   <limits>
                       <limit>
                           <counter>LINE</counter>
                           <value>COVEREDRATIO</value>
                           <minimum>0.80</minimum>
                       </limit>
                       <limit>
                           <counter>BRANCH</counter>
                           <value>COVEREDRATIO</value>
                           <minimum>0.70</minimum>
                       </limit>
                   </limits>
               </rule>
           </rules>
       </configuration>
   </plugin>
   ```

2. **Coverage Reports**
   - Location: `target/site/jacoco/index.html`
   - Metrics tracked:
     - Line coverage
     - Branch coverage
     - Method coverage
     - Class coverage

### Test Execution

1. **Running Tests**
   ```bash
   # Run all tests
   mvn test

   # Run specific test class
   mvn test -Dtest=LambdaExamplesTest

   # Run tests with coverage
   mvn clean test jacoco:report

   # Run tests by category
   mvn test -Dgroups="unit"
   ```

2. **Debugging Tests**
   ```bash
   # Debug tests with remote debugging
   mvn test -Dmaven.surefire.debug

   # Run tests with verbose output
   mvn test -X
   ```

### Test Best Practices

1. **Lambda Testing**
   - Test lambda behavior, not implementation
   - Verify functional interface contracts
   - Test edge cases and exceptions

2. **Stream Testing**
   ```java
   @Test
   void testStreamOperations() {
       List<String> input = Arrays.asList("a", "bb", "ccc");
       List<String> result = input.stream()
           .filter(s -> s.length() > 1)
           .collect(Collectors.toList());
       
       assertThat(result)
           .hasSize(2)
           .containsExactly("bb", "ccc");
   }
   ```

3. **DateTime Testing**
   ```java
   @Test
   void testDateTimeOperations() {
       // Use fixed clock for deterministic tests
       Clock fixedClock = Clock.fixed(
           Instant.parse("2023-01-01T10:00:00Z"),
           ZoneId.of("UTC")
       );
       LocalDateTime dateTime = LocalDateTime.now(fixedClock);
       
       assertThat(dateTime)
           .hasYear(2023)
           .hasMonth(1)
           .hasDayOfMonth(1);
   }
   ```

4. **Optional Testing**
   ```java
   @Test
   void testOptionalHandling() {
       Optional<String> optional = Optional.of("test");
       
       assertThat(optional)
           .isPresent()
           .hasValue("test");
   }
   ```

### Common Test Issues

1. **Thread Safety**
   - Issue: Parallel test execution conflicts
   - Solution: Use thread-safe collections and atomic operations
   ```java
   private final ConcurrentHashMap<String, String> testMap = new ConcurrentHashMap<>();
   private final AtomicInteger counter = new AtomicInteger();
   ```

2. **Resource Management**
   - Issue: Unclosed resources in tests
   - Solution: Use try-with-resources and @AfterEach cleanup
   ```java
   @AfterEach
   void cleanup() {
       // Clean up resources
       testFiles.forEach(File::delete);
   }
   ```

3. **Time-Dependent Tests**
   - Issue: Flaky tests due to time dependencies
   - Solution: Use fixed clock and avoid Thread.sleep
   ```java
   @Test
   void testTimeBasedOperation(@TempDir Path tempDir) {
       Clock fixedClock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
       // Use fixedClock in test
   }
   ```

### Performance Testing

1. **JMH Integration**
   ```xml
   <dependency>
       <groupId>org.openjdk.jmh</groupId>
       <artifactId>jmh-core</artifactId>
       <version>1.35</version>
       <scope>test</scope>
   </dependency>
   ```

2. **Benchmark Example**
   ```java
   @State(Scope.Thread)
   public class StreamBenchmark {
       @Benchmark
       public void measureStreamPerformance() {
           // Benchmark implementation
       }
   }
   ```

## Dependencies
```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.9.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Contributing
1. Fork the repository
2. Create a feature branch
3. Add tests for new features
4. Submit pull request

## Resources
- [Java 8 Documentation](https://docs.oracle.com/javase/8/docs/)
- [Stream API Tutorial](https://docs.oracle.com/javase/tutorial/collections/streams/)
- [DateTime API Guide](https://docs.oracle.com/javase/tutorial/datetime/)
