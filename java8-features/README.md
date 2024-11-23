# Java 8 Features Module

This module demonstrates the key features introduced in Java 8, with practical examples and detailed explanations.

## 1. Lambda Expressions and Functional Interfaces

### Custom Functional Interfaces
```java
@FunctionalInterface
interface Validator<T> {
    boolean validate(T t);
    
    // Default method to chain validators
    default Validator<T> and(Validator<T> other) {
        return t -> validate(t) && other.validate(t);
    }
}
```

### Lambda Expression Examples
```java
// Basic lambda
Predicate<String> isNotEmpty = s -> !s.isEmpty();

// Method reference
List<String> names = Arrays.asList("Alice", "Bob");
names.forEach(System.out::println);

// Currying
Function<BigDecimal, Function<String, Product>> productCreator = 
    price -> name -> new Product(name, price, "Default");
```

## 2. Stream API

### Basic Operations
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Filtering and mapping
List<Integer> doubledEvens = numbers.stream()
    .filter(n -> n % 2 == 0)
    .map(n -> n * 2)
    .collect(Collectors.toList());

// Reduction
int sum = numbers.stream()
    .reduce(0, Integer::sum);
```

### Custom Collectors
```java
public class CustomCollector<T> {
    private final List<T> items = new ArrayList<>();
    private final Consumer<T> accumulator;
    
    public void accept(T item) {
        accumulator.accept(item);
        items.add(item);
    }
}
```

## 3. Optional API

### Creation and Usage
```java
// Creating Optional
Optional<String> optional = Optional.ofNullable(getValue());

// Chaining operations
String result = optional
    .filter(s -> s.length() > 5)
    .map(String::toUpperCase)
    .orElse("DEFAULT");
```

## 4. Date/Time API

### Basic Usage
```java
// Current date/time
LocalDateTime now = LocalDateTime.now();
LocalDate today = LocalDate.now();
LocalTime time = LocalTime.now();

// Date manipulation
LocalDate future = today.plusDays(10);
Period period = Period.between(today, future);
```

## 5. JavaScript Engine (Nashorn)

### Script Evaluation
```java
ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
engine.eval("print('Hello from JavaScript!');");

// Java-JavaScript interop
engine.put("javaString", "Hello");
engine.eval("print(javaString + ' from JavaScript');");
```

## 6. Concurrent Utilities

### StampedLock Example
```java
private double x, y;
private final StampedLock lock = new StampedLock();

public void move(double deltaX, double deltaY) {
    long stamp = lock.writeLock();
    try {
        x += deltaX;
        y += deltaY;
    } finally {
        lock.unlockWrite(stamp);
    }
}
```

### LongAdder Usage
```java
LongAdder counter = new LongAdder();
counter.increment();
counter.add(10);
long total = counter.sum();
```

## 7. Base64 Encoding/Decoding

### Basic Usage
```java
String encoded = Base64.getEncoder()
    .encodeToString("Hello".getBytes());
String decoded = new String(Base64.getDecoder()
    .decode(encoded));
```

## 8. Enhanced Annotations

### Repeating Annotations
```java
@Repeatable(Schedules.class)
@interface Schedule {
    String dayOfMonth();
    int hour() default 12;
}

@Schedule(dayOfMonth="1", hour=8)
@Schedule(dayOfMonth="15", hour=13)
class ReportGenerator { }
```

## 9. Map API Enhancements

### New Methods
```java
Map<String, Integer> map = new HashMap<>();

// Compute if absent
map.computeIfAbsent("key", k -> k.length());

// Merge
map.merge("key", 1, Integer::sum);
```

## 10. Improved Type Inference

### Generic Methods
```java
// Diamond operator with anonymous classes
List<String> list = new ArrayList<>() {
    @Override
    public boolean add(String s) {
        return super.add(s.toUpperCase());
    }
};
```

## Common Issues and Solutions

### Lambda Expression Issues
1. **Problem**: Type inference fails with complex lambda expressions
   **Solution**: Explicitly specify parameter types
   ```java
   Function<String, Integer> f = (String s) -> s.length();
   ```

2. **Problem**: Lambda capturing values
   **Solution**: Use effectively final variables or instance fields
   ```java
   int multiplier = 10; // effectively final
   numbers.stream().map(n -> n * multiplier);
   ```

### Stream API Issues
1. **Problem**: Stream has already been operated upon
   **Solution**: Create new stream for each operation
   ```java
   List<String> list = Arrays.asList("a", "b");
   Stream<String> stream = list.stream(); // Create new stream when needed
   ```

2. **Problem**: Parallel stream performance
   **Solution**: Use parallel streams only for CPU-intensive tasks with large datasets
   ```java
   // Good use case
   hugeList.parallelStream()
       .filter(complexPredicate)
       .map(complexMapping)
       .collect(Collectors.toList());
   ```

## Best Practices

1. **Lambda Expressions**
   - Keep lambdas small and focused
   - Extract complex logic to methods
   - Use method references when possible

2. **Stream API**
   - Use appropriate terminal operations
   - Consider performance implications of parallel streams
   - Close streams that wrap I/O resources

3. **Optional**
   - Don't use Optional as method parameter
   - Use Optional for return values that might be null
   - Don't use Optional.get() without isPresent() check

4. **Date/Time API**
   - Use appropriate temporal classes
   - Consider time zones in calculations
   - Use formatters for string conversion

## Running the Examples

```bash
# Compile and run all examples
mvn clean install
mvn exec:java -Dexec.mainClass="com.java.features.java8.lambda.LambdaExamples"

# Run specific tests
mvn test -Dtest=LambdaExamplesTest
```

## Further Reading
- [Java 8 Documentation](https://docs.oracle.com/javase/8/docs/api/)
- [Lambda Expressions Tutorial](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
- [Stream API Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html)
