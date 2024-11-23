package com.java.features.java8.lambda;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Comprehensive examples of Lambda expressions in Java 8
 */
public class LambdaExamples {

    // Custom functional interfaces
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

    @FunctionalInterface
    interface Transformer<T, R> {
        R transform(T t);
        
        default <V> Transformer<T, V> andThen(Transformer<R, V> after) {
            return t -> after.transform(transform(t));
        }
    }

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

    // Custom collectors and operations
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
     * Examples of custom method implementations using lambdas
     */
    public static class CustomOperations {
        
        // Custom reduce operation
        public static <T> Optional<T> reduceWithPredicate(
                List<T> list,
                Predicate<T> condition,
                BinaryOperator<T> reducer) {
            return list.stream()
                    .filter(condition)
                    .reduce(reducer);
        }

        // Custom mapping with validation
        public static <T, R> List<R> mapIfValid(
                List<T> input,
                Predicate<T> validator,
                Function<T, R> mapper) {
            return input.stream()
                    .filter(validator)
                    .map(mapper)
                    .collect(Collectors.toList());
        }

        // Custom grouping operation
        public static <T, K> Map<K, List<T>> groupByWithTransform(
                List<T> items,
                Function<T, K> keyExtractor,
                UnaryOperator<T> transformer) {
            return items.stream()
                    .map(transformer)
                    .collect(Collectors.groupingBy(keyExtractor));
        }

        // Custom partition operation
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
