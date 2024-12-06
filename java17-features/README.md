# Java 17 Features Module

This module demonstrates the key features and enhancements introduced in Java 17 (LTS).

## 1. Sealed Classes

### Basic Usage
```java
public sealed class Shape 
    permits Circle, Rectangle, Triangle {
    private final String name;
    
    public Shape(String name) {
        this.name = name;
    }
}

public final class Circle extends Shape {
    private final double radius;
    
    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }
}

public final class Rectangle extends Shape {
    private final double width;
    private final double height;
    
    public Rectangle(String name, double width, double height) {
        super(name);
        this.width = width;
        this.height = height;
    }
}

public final class Triangle extends Shape {
    private final double base;
    private final double height;
    
    public Triangle(String name, double base, double height) {
        super(name);
        this.base = base;
        this.height = height;
    }
}
```

### Pattern Matching with Sealed Classes
```java
public double calculateArea(Shape shape) {
    return switch (shape) {
        case Circle c -> Math.PI * c.radius() * c.radius();
        case Rectangle r -> r.width() * r.height();
        case Triangle t -> 0.5 * t.base() * t.height();
    };
}
```

## 2. Pattern Matching for switch

### Type Patterns
```java
public String formatValue(Object obj) {
    return switch (obj) {
        case Integer i -> String.format("int %d", i);
        case Long l -> String.format("long %d", l);
        case Double d -> String.format("double %f", d);
        case String s -> String.format("String %s", s);
        case null -> "null";
        default -> obj.toString();
    };
}
```

### Guard Patterns
```java
public String categorizeNumber(Number number) {
    return switch (number) {
        case Integer i when i < 0 -> "negative integer";
        case Integer i when i > 0 -> "positive integer";
        case Integer i -> "zero";
        case Double d when d < 0.0 -> "negative double";
        case Double d when d > 0.0 -> "positive double";
        case Double d -> "zero";
        default -> "unknown number type";
    };
}
```

## 3. Records

### Basic Record
```java
public record Point(int x, int y) {
    // Compact constructor
    public Point {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException(
                "Coordinates cannot be negative");
        }
    }
    
    // Additional methods
    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }
}
```

### Records with Generics
```java
public record Pair<T, U>(T first, U second) {
    public <V> Pair<V, U> mapFirst(Function<T, V> mapper) {
        return new Pair<>(mapper.apply(first), second);
    }
    
    public <V> Pair<T, V> mapSecond(Function<U, V> mapper) {
        return new Pair<>(first, mapper.apply(second));
    }
}
```

### Records as DTOs
```java
public record UserDTO(
    String id,
    String username,
    String email,
    LocalDateTime createdAt
) {
    // Custom serialization method
    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("id", id)
            .add("username", username)
            .add("email", email)
            .add("createdAt", createdAt.toString())
            .build();
    }
}
```

## 4. Text Blocks

### Multiline Strings
```java
String query = """
    SELECT u.id, u.name, u.email
    FROM users u
    JOIN orders o ON u.id = o.user_id
    WHERE o.status = 'PENDING'
    ORDER BY o.created_at DESC
    """;

String html = """
    <html>
        <body>
            <h1>Welcome</h1>
            <p>This is a text block example.</p>
        </body>
    </html>
    """;
```

### JSON Formation
```java
String json = """
    {
        "name": "John Doe",
        "age": 30,
        "address": {
            "street": "123 Main St",
            "city": "Springfield",
            "state": "IL"
        },
        "hobbies": [
            "reading",
            "hiking",
            "photography"
        ]
    }
    """;
```

### Advanced Text Block Examples
```java
// HTML Template with Styling
String html = """
    <!DOCTYPE html>
    <html>
        <head>
            <title>%s</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 20px;
                }
                .content {
                    padding: 10px;
                    border: 1px solid #ccc;
                }
            </style>
        </head>
        <body>
            <div class="content">
                %s
            </div>
        </body>
    </html>
    """.formatted(title, content);

// Complex SQL Query
String sql = """
    SELECT u.id, u.name, u.email,
           COUNT(o.id) as order_count,
           SUM(o.total_amount) as total_spent
    FROM %s u
    LEFT JOIN orders o ON u.id = o.user_id
    WHERE u.status = 'ACTIVE'
      AND o.created_at >= DATE_SUB(NOW(), INTERVAL 1 YEAR)
    GROUP BY u.id, u.name, u.email
    HAVING order_count > 0
    ORDER BY total_spent DESC
    LIMIT %d
    """.formatted(tableName, limit);
```

## 5. Enhanced Switch Expressions

### Advanced Pattern Matching Examples
```java
// Pattern matching with type patterns and guards
public static String formatValue(Object obj) {
    return switch (obj) {
        case null -> "null";
        case String s -> "String: " + s.toLowerCase();
        case Integer i -> "Integer: " + (i * 2);
        case Long l -> "Long: " + (l + 1);
        case Double d -> "Double: " + String.format("%.2f", d);
        case Number n -> "Number: " + n.toString();
        case int[] arr -> "Int Array of length: " + arr.length;
        default -> "Unknown type: " + obj.getClass().getSimpleName();
    };
}

// Pattern matching with guards and multiple conditions
public static String classifyNumber(Number number) {
    return switch (number) {
        case null -> "Invalid number: null";
        case Integer i && i < 0 -> "Negative integer: " + i;
        case Integer i && i == 0 -> "Zero";
        case Integer i && i > 0 -> "Positive integer: " + i;
        case Double d && d.isNaN() -> "Not a number";
        case Double d && d.isInfinite() -> "Infinite";
        case Double d && d < 0 -> "Negative double: " + String.format("%.2f", d);
        case Double d -> "Positive double: " + String.format("%.2f", d);
        default -> "Other number type: " + number.getClass().getSimpleName();
    };
}
```

## Common Issues and Solutions

### Sealed Classes Issues
1. **Problem**: Compilation error with unsealed subclass
   **Solution**: Ensure all subclasses are either final, sealed, or non-sealed
   ```java
   public sealed interface Service 
       permits UserService, ProductService {
   }
   
   public final class UserService implements Service {
   }
   
   public non-sealed class ProductService implements Service {
   }
   ```

### Pattern Matching Issues
1. **Problem**: Exhaustiveness checking in switch expressions
   **Solution**: Cover all possible cases or provide a default
   ```java
   public String describe(Shape shape) {
       return switch (shape) {
           case Circle c -> "circle";
           case Rectangle r -> "rectangle";
           case Triangle t -> "triangle";
           // No default needed - all cases covered
       };
   }
   ```

## Best Practices

1. **Sealed Classes**
   - Use for modeling domain-specific hierarchies
   - Keep the hierarchy shallow
   - Consider using with pattern matching

2. **Records**
   - Use for immutable data carriers
   - Keep methods focused on data representation
   - Consider validation in compact constructors

3. **Pattern Matching**
   - Use type patterns for cleaner instanceof checks
   - Combine with sealed classes for exhaustive matching
   - Use guards for complex conditions

4. **Text Blocks**
   - Maintain consistent indentation
   - Use for SQL queries, HTML, JSON
   - Consider string formatting when needed

## Performance Considerations

### Record Performance
```java
// Efficient record usage
public record LargeData(
    byte[] content,
    Map<String, String> metadata
) {
    // Lazy initialization of expensive computations
    public String getChecksum() {
        return computeChecksum(content);
    }
}
```

### Pattern Matching Performance
```java
// Efficient pattern matching
public int processValue(Object value) {
    // More specific cases first
    return switch (value) {
        case Integer i -> i;
        case Number n -> n.intValue();
        case String s -> Integer.parseInt(s);
        default -> 0;
    };
}
```

## Running the Examples

```bash
# Compile and run
mvn clean install
mvn exec:java -Dexec.mainClass="com.java.features.java17.sealed.SealedClassDemo"

# Run specific tests
mvn test -Dtest=RecordTest
```

## Further Reading
- [Java 17 Documentation](https://docs.oracle.com/en/java/javase/17/docs/api/)
- [Sealed Classes Guide](https://docs.oracle.com/en/java/javase/17/language/sealed-classes-and-interfaces.html)
- [Pattern Matching Guide](https://docs.oracle.com/en/java/javase/17/language/pattern-matching.html)
- [Records Guide](https://docs.oracle.com/en/java/javase/17/language/records.html)
