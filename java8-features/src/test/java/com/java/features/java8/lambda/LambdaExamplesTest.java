package com.java.features.java8.lambda;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

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
        Predicate<Order> isPending = order -> "PENDING".equals(order.getStatus());
        Predicate<Order> isLargeOrder = order -> order.getAmount() > 100.0;

        // Processing pipeline
        List<Order> processableOrders = orders.stream()
            .filter(isPending.and(isLargeOrder))
            .collect(Collectors.toList());

        assertEquals(2, processableOrders.size());
        assertTrue(processableOrders.stream().allMatch(o -> o.getAmount() > 100.0));
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
        Map<String, String> loginData = new HashMap<>();
        loginData.put("userId", "12345");
        Event loginEvent = new Event("LOGIN", loginData);

        Map<String, String> purchaseData = new HashMap<>();
        purchaseData.put("amount", "99.99");
        purchaseData.put("productId", "PRD-001");
        Event purchaseEvent = new Event("PURCHASE", purchaseData);

        eventSystem.processEvent(loginEvent);
        eventSystem.processEvent(purchaseEvent);
    }

    // Supporting classes
    static class Event {
        private final String type;
        private final Map<String, String> data;

        public Event(String type, Map<String, String> data) {
            this.type = type;
            this.data = data;
        }

        public String getType() { return type; }
        public Map<String, String> getData() { return data; }
    }

    static class Order {
        private final Long id;
        private final Double amount;
        private final String status;

        public Order(Long id, Double amount, String status) {
            this.id = id;
            this.amount = amount;
            this.status = status;
        }

        public Long getId() { return id; }
        public Double getAmount() { return amount; }
        public String getStatus() { return status; }
    }

    static class Transaction {
        private final String type;
        private final Double amount;

        public Transaction(String type, Double amount) {
            this.type = type;
            this.amount = amount;
        }

        public String getType() { return type; }
        public Double getAmount() { return amount; }
    }

    static class EventSystem {
        private final Map<String, Consumer<Event>> handlers = new HashMap<>();

        public void registerHandler(String eventType, Consumer<Event> handler) {
            handlers.put(eventType, handler);
        }

        public void processEvent(Event event) {
            Consumer<Event> handler = handlers.get(event.getType());
            if (handler != null) {
                handler.accept(event);
            } else {
                throw new IllegalArgumentException("No handler registered for event type: " + event.getType());
            }
        }
    }
}
