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
9. [Map Enhancements](#map-enhancements)
10. [Base64 Encoding/Decoding](#base64-encodingdecoding)
11. [Type Inference Improvements](#type-inference-improvements)

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
- `LambdaExamples`: Collection of lambda usage patterns and functional interfaces
- `Vehicle`: Demonstrates lambda usage with default methods

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

// Complex operations
int sumOfSquares = numbers.stream()
    .filter(n -> n % 2 == 0)
    .mapToInt(n -> n * n)
    .sum();
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
public interface Vehicle {
    default String startEngine() {
        return "Starting engine of " + getBrand();
    }
    
    String getBrand();
}

public class Car implements Vehicle {
    private String brand;
    
    @Override
    public String getBrand() {
        return brand;
    }
}
```

### Key Classes
- `Vehicle`: Interface with default methods
- `Car`: Basic implementation
- `ElectricCar`: Custom implementation overriding defaults

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

### Examples
```java
CompletableFuture<String> future = CompletableFuture
    .supplyAsync(() -> "Hello")
    .thenApply(String::toUpperCase)
    .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));

// Parallel processing
List<CompletableFuture<String>> futures = inputs.stream()
    .map(input -> CompletableFuture.supplyAsync(() -> processInput(input)))
    .collect(Collectors.toList());

CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
    .thenRun(() -> System.out.println("All tasks completed"));
```

## Nashorn JavaScript Engine

### Overview
Nashorn provides JavaScript runtime for executing JavaScript code from Java applications.

### Examples
```java
// Basic evaluation
ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
engine.eval("print('Hello from JavaScript!');");

// Java-JavaScript interop
engine.put("javaString", "Hello from Java!");
engine.eval("print(javaString);");

// Complex operations
engine.eval(
    "var person = {" +
    "   name: 'John'," +
    "   age: 30," +
    "   toString: function() { " +
    "       return this.name + ' is ' + this.age + ' years old';" +
    "   }" +
    "};"
);
```

## Map Enhancements

### Overview
Java 8 introduced several new methods to the Map interface for more convenient operations.

### Key Features
- `computeIfAbsent()`: Compute value if key is absent
- `computeIfPresent()`: Compute value if key is present
- `merge()`: Merge entries
- `forEach()`: Iterate over entries
- `getOrDefault()`: Get value with default

### Examples
```java
// Compute if absent
map.computeIfAbsent("key", k -> "value");

// Merge values
map.merge("key", "value", (v1, v2) -> v1 + "-" + v2);

// ForEach with BiConsumer
map.forEach((key, value) -> System.out.println(key + "=" + value));
```

## Base64 Encoding/Decoding

### Overview
Java 8 introduced built-in support for Base64 encoding and decoding.

### Examples
```java
// Basic encoding/decoding
String encoded = Base64.getEncoder().encodeToString("Hello".getBytes());
byte[] decoded = Base64.getDecoder().decode(encoded);

// URL-safe encoding
String urlEncoded = Base64.getUrlEncoder().encodeToString("Hello?".getBytes());

// MIME encoding
String mimeEncoded = Base64.getMimeEncoder().encodeToString("Hello\nWorld".getBytes());
```

## Type Inference Improvements

### Overview
Java 8 improved type inference in generic method calls and lambda expressions.

### Examples
```java
// Better constructor reference
List<String> list = createList(ArrayList::new);

// Improved method reference
List<Integer> numbers = Arrays.asList(1, 2, 3);
List<String> strings = transformList(numbers, Object::toString);

// Generic method with intersection types
<T extends Comparable<T> & Cloneable> Optional<T> findMax(List<T> list);
```

## Project Structure

```
java8-features/
├── src/main/java/com/java/features/java8/
│   ├── annotations/
│   │   └── AnnotationExamples.java
│   ├── base64/
│   │   └── Base64Examples.java
│   ├── concurrent/
│   │   └── ConcurrentExamples.java
│   ├── datetime/
│   │   └── DateTimeExamples.java
│   ├── defaultmethods/
│   │   ├── Vehicle.java
│   │   ├── Car.java
│   │   └── ElectricCar.java
│   ├── generics/
│   │   └── GenericsExample.java
│   ├── lambda/
│   │   └── LambdaExamples.java
│   ├── map/
│   │   └── MapExamples.java
│   ├── nashorn/
│   │   └── NashornExample.java
│   ├── optional/
│   │   └── OptionalExamples.java
│   └── streams/
│       └── StreamsExample.java
```

Each package contains comprehensive examples and documentation for specific Java 8 features. The examples include practical use cases, best practices, and detailed comments explaining the concepts.
