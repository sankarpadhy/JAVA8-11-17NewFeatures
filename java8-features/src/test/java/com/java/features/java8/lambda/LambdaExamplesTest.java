package com.java.features.java8.lambda;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@DisplayName("Lambda Expressions Real-World Examples")
class LambdaExamplesTest {

    // Real-world use case: Order processing system
    @Test
    @DisplayName("Order Processing Pipeline with Lambda")
    void testOrderProcessingPipeline() {
        // Simulating an e-commerce order processing system
        List<Order> orders = Arrays.asList(
            new Order(1L, 150.0, "PENDING"),
            new Order(2L, 75.0, "PENDING"),
            new Order(3L, 300.0, "CANCELLED"),
            new Order(4L, 450.0, "PENDING")
        );

        // Business rules as predicates
        Predicate<Order> isPending = order -> "PENDING".equals(order.status());
        Predicate<Order> isLargeOrder = order -> order.amount() > 100.0;

        // Processing pipeline
        List<Order> processableOrders = orders.stream()
            .filter(isPending.and(isLargeOrder))
            .collect(Collectors.toList());

        assertEquals(2, processableOrders.size());
        assertTrue(processableOrders.stream().allMatch(o -> o.amount() > 100.0));
    }

    // Real-world use case: Document processing system
    @Test
    @DisplayName("Document Processing with Function Composition")
    void testDocumentProcessing() {
        // Document processing functions
        Function<String, String> removeSpecialChars = str -> str.replaceAll("[^a-zA-Z0-9\\s]", "");
        Function<String, String> convertToLowerCase = String::toLowerCase;
        Function<String, String> normalizeSpaces = str -> str.trim().replaceAll("\\s+", " ");

        // Compose document processing pipeline
        Function<String, String> documentProcessor = removeSpecialChars
            .andThen(convertToLowerCase)
            .andThen(normalizeSpaces);

        String input = "  Hello,    World!  #2023  ";
        String processed = documentProcessor.apply(input);

        assertEquals("hello world 2023", processed);
    }

    // Real-world use case: Financial calculations
    @Test
    @DisplayName("Financial Calculations with Method References")
    void testFinancialCalculations() {
        List<Transaction> transactions = Arrays.asList(
            new Transaction("STOCK", 1000.0),
            new Transaction("BOND", 5000.0),
            new Transaction("STOCK", 3000.0)
        );

        // Calculate total using method reference
        double totalAmount = transactions.stream()
            .mapToDouble(Transaction::getAmount)
            .sum();

        assertEquals(9000.0, totalAmount);

        // Filter by type using method reference
        List<Transaction> stockTransactions = transactions.stream()
            .filter(t -> "STOCK".equals(t.getType()))
            .collect(Collectors.toList());

        assertEquals(2, stockTransactions.size());
    }

    // Real-world use case: Event handling system
    @Test
    @DisplayName("Event Handler Registration")
    void testEventHandling() {
        EventSystem eventSystem = new EventSystem();

        // Register event handlers using lambdas
        eventSystem.registerHandler("LOGIN", event -> 
            assertTrue(event.getData().containsKey("userId")));

        eventSystem.registerHandler("PURCHASE", event -> {
            assertTrue(event.getData().containsKey("amount"));
            assertTrue(event.getData().containsKey("productId"));
        });

        // Simulate events
        Event loginEvent = new Event("LOGIN", Map.of("userId", "12345"));
        Event purchaseEvent = new Event("PURCHASE", Map.of(
            "amount", "99.99",
            "productId", "PRD-001"
        ));

        eventSystem.processEvent(loginEvent);
        eventSystem.processEvent(purchaseEvent);
    }

    // Supporting classes
    record Order(Long id, Double amount, String status) {}
    record Transaction(String type, Double amount) {
        public String getType() { return type; }
        public Double getAmount() { return amount; }
    }
}
