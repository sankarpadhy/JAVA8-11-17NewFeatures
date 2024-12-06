/**
 * Demonstrates Lambda expressions and functional interfaces introduced in Java 8.
 * This class showcases various ways to use lambda expressions for more
 * concise and functional programming in Java.
 *
 * Key concepts demonstrated:
 * 1. Functional Interfaces:
 *    - Single Abstract Method (SAM) interfaces
 *    - Built-in functional interfaces
 *    - Custom functional interfaces
 *
 * 2. Lambda Expression Forms:
 *    - () -> expression
 *    - parameter -> expression
 *    - (parameter1, parameter2) -> expression
 *    - (parameter1, parameter2) -> { statements; }
 *
 * 3. Method References:
 *    - Static: Class::staticMethod
 *    - Instance: instance::method
 *    - Constructor: Class::new
 *
 * 4. Common Functional Interfaces:
 *    - Function<T,R>: Transforms T to R
 *    - Predicate<T>: Tests T for condition
 *    - Consumer<T>: Accepts T, returns void
 *    - Supplier<T>: Supplies T, accepts nothing
 *    - UnaryOperator<T>: T -> T operation
 *    - BinaryOperator<T>: (T,T) -> T operation
 *
 * Example usage:
 * ```java
 * // Simple lambda
 * Runnable r = () -> System.out.println("Hello");
 *
 * // With parameters
 * Comparator<String> c = (s1, s2) -> s1.compareToIgnoreCase(s2);
 *
 * // Method reference
 * List<String> list = Arrays.asList("a", "b", "c");
 * list.forEach(System.out::println);
 *
 * // Custom functional interface
 * Validator<String> lengthCheck = 
 *     s -> s.length() > 5;
 * ```
 *
 * Best Practices:
 * - Keep lambda expressions short and readable
 * - Use method references when possible
 * - Leverage built-in functional interfaces
 * - Consider exception handling
 *
 * @see java.util.function
 * @see java.lang.FunctionalInterface
 * @since Java 8
 */
public class LambdaExamples {

    // Custom functional interfaces
    /**
     * A functional interface for validating objects of type T.
     * 
     * Sample usage:
     * ```java
     * Validator<String> lengthValidator = str -> str.length() > 5;
     * boolean isValid = lengthValidator.validate("Hello World"); // Returns: true
     * 
     * // Chaining validators
     * Validator<String> notEmptyValidator = str -> !str.isEmpty();
     * Validator<String> composite = lengthValidator.and(notEmptyValidator);
     * boolean isValidComposite = composite.validate("Hello"); // Returns: false
     * ```
     *
     * @param <T> The type of object to validate
     */
    @FunctionalInterface
    interface Validator<T> {
        boolean validate(T t);
        
        // Default method to chain validators
        default Validator<T> and(Validator<T> other) {
            return t -> validate(t) && other.validate(t);
        }
        
        // Static factory method
        static <T> Validator<T> of(Validator<T> validator) {
            return validator;
        }
    }

    /**
     * A functional interface for transforming objects from type T to type R.
     * 
     * Sample usage:
     * ```java
     * Transformer<String, Integer> lengthTransformer = String::length;
     * int length = lengthTransformer.transform("Hello"); // Returns: 5
     * 
     * // Chaining transformers
     * Transformer<Integer, String> intToString = Object::toString;
     * Transformer<String, String> composite = lengthTransformer.andThen(intToString);
     * String result = composite.transform("Hello"); // Returns: "5"
     * ```
     *
     * @param <T> The input type
     * @param <R> The result type
     */
    @FunctionalInterface
    interface Transformer<T, R> {
        R transform(T t);
        
        default <V> Transformer<T, V> andThen(Transformer<R, V> after) {
            return t -> after.transform(transform(t));
        }
    }

    /**
     * A functional interface that accepts three arguments and produces a result.
     * 
     * Sample usage:
     * ```java
     * TriFunction<String, Integer, Boolean, String> formatter = 
     *     (str, num, upperCase) -> upperCase ? 
     *         String.format("%s: %d", str.toUpperCase(), num) :
     *         String.format("%s: %d", str, num);
     *         
     * String result = formatter.apply("Count", 42, true); // Returns: "COUNT: 42"
     * ```
     *
     * @param <T> Type of the first argument
     * @param <U> Type of the second argument
     * @param <V> Type of the third argument
     * @param <R> Type of the result
     */
    @FunctionalInterface
    interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }

    // Domain classes for examples
    static class Product {
        private String name;
        private BigDecimal price;
        private String category;
        private LocalDate expiryDate;

        public Product(String name, BigDecimal price, String category, LocalDate expiryDate) {
            this.name = name;
            this.price = price;
            this.category = category;
            this.expiryDate = expiryDate;
        }

        // Getters
        public String getName() { return name; }
        public BigDecimal getPrice() { return price; }
        public String getCategory() { return category; }
        public LocalDate getExpiryDate() { return expiryDate; }
    }

    /**
     * A custom collector class that accumulates items and performs actions during collection.
     * 
     * Sample usage:
     * ```java
     * CustomCollector<String> collector = new CustomCollector<>(
     *     item -> System.out.println("Processing: " + item),
     *     () -> System.out.println("All done!")
     * );
     * 
     * collector.accept("Item 1");  // Prints: Processing: Item 1
     * collector.accept("Item 2");  // Prints: Processing: Item 2
     * collector.finish();          // Prints: All done!
     * List<String> items = collector.getItems();  // Returns: ["Item 1", "Item 2"]
     * ```
     *
     * @param <T> The type of items to collect
     */
    static class CustomCollector<T> {
        private final List<T> items = new ArrayList<>();
        private final Consumer<T> accumulator;
        private final Runnable finisher;

        public CustomCollector(Consumer<T> accumulator, Runnable finisher) {
            this.accumulator = accumulator;
            this.finisher = finisher;
        }

        public void accept(T item) {
            accumulator.accept(item);
            items.add(item);
        }

        public void finish() {
            finisher.run();
        }

        public List<T> getItems() {
            return items;
        }
    }

    /**
     * Custom operations class containing utility methods for collection processing.
     */
    public static class CustomOperations {
        
        /**
         * Reduces a list of elements that match a condition using a reducer function.
         * 
         * Sample usage:
         * ```java
         * List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
         * Optional<Integer> sum = reduceWithPredicate(
         *     numbers,
         *     n -> n % 2 == 0,  // Only even numbers
         *     Integer::sum
         * );
         * // Returns: Optional[6] (2 + 4)
         * ```
         */
        public static <T> Optional<T> reduceWithPredicate(
                List<T> list,
                Predicate<T> condition,
                BinaryOperator<T> reducer) {
            return list.stream()
                    .filter(condition)
                    .reduce(reducer);
        }

        /**
         * Maps elements that satisfy a validator using a mapping function.
         * 
         * Sample usage:
         * ```java
         * List<String> words = Arrays.asList("hello", "hi", "world");
         * List<Integer> lengths = mapIfValid(
         *     words,
         *     str -> str.length() > 2,  // Only words longer than 2 chars
         *     String::length
         * );
         * // Returns: [5, 5] (lengths of "hello" and "world")
         * ```
         */
        public static <T, R> List<R> mapIfValid(
                List<T> input,
                Predicate<T> validator,
                Function<T, R> mapper) {
            return input.stream()
                    .filter(validator)
                    .map(mapper)
                    .collect(Collectors.toList());
        }

        /**
         * Groups items by a key after applying a transformation.
         * 
         * Sample usage:
         * ```java
         * List<String> words = Arrays.asList("hello", "hi", "world");
         * Map<Integer, List<String>> grouped = groupByWithTransform(
         *     words,
         *     String::length,
         *     String::toUpperCase
         * );
         * // Returns: {2: ["HI"], 5: ["HELLO", "WORLD"]}
         * ```
         */
        public static <T, K> Map<K, List<T>> groupByWithTransform(
                List<T> items,
                Function<T, K> keyExtractor,
                UnaryOperator<T> transformer) {
            return items.stream()
                    .map(transformer)
                    .collect(Collectors.groupingBy(keyExtractor));
        }

        /**
         * Partitions items into two groups after applying a transformation.
         * 
         * Sample usage:
         * ```java
         * List<String> words = Arrays.asList("hello", "hi", "world");
         * Map<Boolean, List<String>> partitioned = partitionWithTransform(
         *     words,
         *     str -> str.length() > 2,
         *     String::toUpperCase
         * );
         * // Returns: {true: ["HELLO", "WORLD"], false: ["HI"]}
         * ```
         */
        public static <T> Map<Boolean, List<T>> partitionWithTransform(
                List<T> items,
                Predicate<T> predicate,
                UnaryOperator<T> transformer) {
            return items.stream()
                    .map(transformer)
                    .collect(Collectors.partitioningBy(predicate));
        }
    }

    /**
     * Examples of lambda expressions with different types of operations
     */
    public static void demonstrateLambdaOperations() {
        List<Product> products = Arrays.asList(
            new Product("Apple", new BigDecimal("1.99"), "Fruit", LocalDate.now().plusDays(5)),
            new Product("Banana", new BigDecimal("0.99"), "Fruit", LocalDate.now().plusDays(3)),
            new Product("Milk", new BigDecimal("2.99"), "Dairy", LocalDate.now().plusDays(7))
        );

        // Custom validator composition
        Validator<Product> priceValidator = p -> p.getPrice().compareTo(BigDecimal.ZERO) > 0;
        Validator<Product> expiryValidator = p -> p.getExpiryDate().isAfter(LocalDate.now());
        Validator<Product> compositeValidator = priceValidator.and(expiryValidator);

        // Custom transformer composition
        Transformer<Product, String> nameTransformer = Product::getName;
        Transformer<String, Integer> lengthTransformer = String::length;
        Transformer<Product, Integer> compositeTransformer = nameTransformer.andThen(lengthTransformer);

        // Custom collector usage
        CustomCollector<Product> collector = new CustomCollector<>(
            p -> System.out.println("Processing: " + p.getName()),
            () -> System.out.println("Finished processing all products")
        );

        // Using custom operations
        BigDecimal totalPrice = products.stream()
            .filter(compositeValidator::validate)
            .map(Product::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Custom grouping with transformation
        Map<String, List<Product>> groupedProducts = CustomOperations.groupByWithTransform(
            products,
            Product::getCategory,
            p -> new Product(p.getName().toUpperCase(), p.getPrice(), p.getCategory(), p.getExpiryDate())
        );

        // Method reference examples
        Comparator<Product> priceComparator = Comparator.comparing(Product::getPrice);
        products.sort(priceComparator.thenComparing(Product::getName));

        // Complex lambda with exception handling
        products.forEach(product -> {
            try {
                if (product.getPrice().compareTo(new BigDecimal("2.00")) > 0) {
                    throw new Exception("Price too high!");
                }
                System.out.println("Processing: " + product.getName());
            } catch (Exception e) {
                System.err.println("Error processing " + product.getName() + ": " + e.getMessage());
            }
        });

        // Currying with lambdas
        Function<BigDecimal, Function<String, Product>> productCreator = 
            price -> name -> new Product(name, price, "Default", LocalDate.now());
        Product newProduct = productCreator
            .apply(new BigDecimal("1.99"))
            .apply("New Product");

        // Custom reduction with predicate
        Optional<Product> cheapestExpensiveProduct = CustomOperations.reduceWithPredicate(
            products,
            p -> p.getPrice().compareTo(new BigDecimal("2.00")) > 0,
            (p1, p2) -> p1.getPrice().compareTo(p2.getPrice()) < 0 ? p1 : p2
        );

        // Parallel processing with custom collector
        products.parallelStream().forEach(collector::accept);
        collector.finish();
    }

    /**
     * Examples of lambda expressions with generics and type inference
     */
    public static class GenericOperations<T> {
        private final List<T> items;

        public GenericOperations(List<T> items) {
            this.items = items;
        }

        public <R> List<R> transformAll(Function<T, R> transformer) {
            return items.stream()
                    .map(transformer)
                    .collect(Collectors.toList());
        }

        public List<T> filterAll(Predicate<T> predicate) {
            return items.stream()
                    .filter(predicate)
                    .collect(Collectors.toList());
        }

        public <R> R reduceAll(R identity, BiFunction<R, T, R> accumulator) {
            return items.stream()
                    .reduce(identity, accumulator, (r1, r2) -> r1);
        }
    }

    public static void main(String[] args) {
        demonstrateLambdaOperations();
    }
}
