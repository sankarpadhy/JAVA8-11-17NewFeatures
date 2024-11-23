package com.java.features.java17.sealed;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@DisplayName("Payment Processing with Sealed Classes")
class PaymentProcessingTest {

    // Real-world use case: Payment Processing System
    sealed interface Payment permits CreditCardPayment, PayPalPayment, CryptoPayment {
        String getTransactionId();
        double getAmount();
        PaymentStatus getStatus();
    }

    final class CreditCardPayment implements Payment {
        private final String transactionId;
        private final double amount;
        private final PaymentStatus status;
        private final String cardNumber;
        private final String cardHolderName;
        
        CreditCardPayment(String transactionId, double amount, 
                         String cardNumber, String cardHolderName) {
            this.transactionId = transactionId;
            this.amount = amount;
            this.status = PaymentStatus.PENDING;
            this.cardNumber = cardNumber;
            this.cardHolderName = cardHolderName;
        }
        
        @Override public String getTransactionId() { return transactionId; }
        @Override public double getAmount() { return amount; }
        @Override public PaymentStatus getStatus() { return status; }
        public String getCardNumber() { return cardNumber; }
        public String getCardHolderName() { return cardHolderName; }
    }

    final class PayPalPayment implements Payment {
        private final String transactionId;
        private final double amount;
        private final PaymentStatus status;
        private final String paypalEmail;
        
        PayPalPayment(String transactionId, double amount, String paypalEmail) {
            this.transactionId = transactionId;
            this.amount = amount;
            this.status = PaymentStatus.PENDING;
            this.paypalEmail = paypalEmail;
        }
        
        @Override public String getTransactionId() { return transactionId; }
        @Override public double getAmount() { return amount; }
        @Override public PaymentStatus getStatus() { return status; }
        public String getPaypalEmail() { return paypalEmail; }
    }

    final class CryptoPayment implements Payment {
        private final String transactionId;
        private final double amount;
        private final PaymentStatus status;
        private final String walletAddress;
        private final String cryptoCurrency;
        
        CryptoPayment(String transactionId, double amount, 
                     String walletAddress, String cryptoCurrency) {
            this.transactionId = transactionId;
            this.amount = amount;
            this.status = PaymentStatus.PENDING;
            this.walletAddress = walletAddress;
            this.cryptoCurrency = cryptoCurrency;
        }
        
        @Override public String getTransactionId() { return transactionId; }
        @Override public double getAmount() { return amount; }
        @Override public PaymentStatus getStatus() { return status; }
        public String getWalletAddress() { return walletAddress; }
        public String getCryptoCurrency() { return cryptoCurrency; }
    }

    enum PaymentStatus {
        PENDING, PROCESSING, COMPLETED, FAILED
    }

    class PaymentProcessor {
        public PaymentResult processPayment(Payment payment) {
            return switch (payment) {
                case CreditCardPayment cc when cc.getAmount() > 10000 ->
                    new PaymentResult(false, "Amount exceeds credit card limit");
                    
                case CreditCardPayment cc ->
                    processCreditCard(cc);
                    
                case PayPalPayment pp when !isValidEmail(pp.getPaypalEmail()) ->
                    new PaymentResult(false, "Invalid PayPal email");
                    
                case PayPalPayment pp ->
                    processPayPal(pp);
                    
                case CryptoPayment cp when !isSupportedCrypto(cp.getCryptoCurrency()) ->
                    new PaymentResult(false, "Unsupported cryptocurrency");
                    
                case CryptoPayment cp ->
                    processCrypto(cp);
            };
        }

        private PaymentResult processCreditCard(CreditCardPayment payment) {
            // Simulate credit card processing
            return new PaymentResult(true, "Credit card payment processed");
        }

        private PaymentResult processPayPal(PayPalPayment payment) {
            // Simulate PayPal processing
            return new PaymentResult(true, "PayPal payment processed");
        }

        private PaymentResult processCrypto(CryptoPayment payment) {
            // Simulate crypto processing
            return new PaymentResult(true, "Crypto payment processed");
        }

        private boolean isValidEmail(String email) {
            return email != null && email.contains("@");
        }

        private boolean isSupportedCrypto(String currency) {
            return List.of("BTC", "ETH", "USDT").contains(currency);
        }
    }

    record PaymentResult(boolean success, String message) {}

    @Test
    @DisplayName("Process Different Payment Types")
    void testPaymentProcessing() {
        PaymentProcessor processor = new PaymentProcessor();

        // Test Credit Card Payment
        Payment ccPayment = new CreditCardPayment(
            "CC-001", 500.0, "4111-1111-1111-1111", "John Doe"
        );
        PaymentResult ccResult = processor.processPayment(ccPayment);
        assertTrue(ccResult.success());

        // Test PayPal Payment
        Payment ppPayment = new PayPalPayment(
            "PP-001", 299.99, "john.doe@example.com"
        );
        PaymentResult ppResult = processor.processPayment(ppPayment);
        assertTrue(ppResult.success());

        // Test Crypto Payment
        Payment cryptoPayment = new CryptoPayment(
            "CR-001", 1.5, "0x742d35Cc6634C0532925a3b844Bc454e4438f44e", "ETH"
        );
        PaymentResult cryptoResult = processor.processPayment(cryptoPayment);
        assertTrue(cryptoResult.success());
    }

    @Test
    @DisplayName("Test Payment Validation Rules")
    void testPaymentValidation() {
        PaymentProcessor processor = new PaymentProcessor();

        // Test Credit Card limit
        Payment highAmountCC = new CreditCardPayment(
            "CC-002", 15000.0, "4111-1111-1111-1111", "John Doe"
        );
        PaymentResult ccResult = processor.processPayment(highAmountCC);
        assertFalse(ccResult.success());
        assertEquals("Amount exceeds credit card limit", ccResult.message());

        // Test invalid PayPal email
        Payment invalidPayPal = new PayPalPayment(
            "PP-002", 99.99, "invalid-email"
        );
        PaymentResult ppResult = processor.processPayment(invalidPayPal);
        assertFalse(ppResult.success());
        assertEquals("Invalid PayPal email", ppResult.message());

        // Test unsupported cryptocurrency
        Payment unsupportedCrypto = new CryptoPayment(
            "CR-002", 1.0, "0x742d35Cc6634C0532925a3b844Bc454e4438f44e", "DOGE"
        );
        PaymentResult cryptoResult = processor.processPayment(unsupportedCrypto);
        assertFalse(cryptoResult.success());
        assertEquals("Unsupported cryptocurrency", cryptoResult.message());
    }

    @Test
    @DisplayName("Test Payment Audit Trail")
    void testPaymentAuditTrail() {
        PaymentAuditor auditor = new PaymentAuditor();
        
        // Create different types of payments
        List<Payment> payments = List.of(
            new CreditCardPayment("CC-003", 750.0, "4111-1111-1111-1111", "Jane Doe"),
            new PayPalPayment("PP-003", 250.0, "jane.doe@example.com"),
            new CryptoPayment("CR-003", 2.0, "0x742d35Cc6634C0532925a3b844Bc454e4438f44e", "BTC")
        );
        
        // Generate audit entries
        List<AuditEntry> auditTrail = payments.stream()
            .map(auditor::createAuditEntry)
            .toList();
        
        assertEquals(3, auditTrail.size());
        assertTrue(auditTrail.stream().allMatch(entry -> entry.timestamp() != null));
    }

    class PaymentAuditor {
        public AuditEntry createAuditEntry(Payment payment) {
            String details = switch (payment) {
                case CreditCardPayment cc ->
                    String.format("Credit Card Payment: %s (Card: %s)",
                        cc.getTransactionId(),
                        maskCardNumber(cc.getCardNumber()));
                        
                case PayPalPayment pp ->
                    String.format("PayPal Payment: %s (Email: %s)",
                        pp.getTransactionId(),
                        pp.getPaypalEmail());
                        
                case CryptoPayment cp ->
                    String.format("Crypto Payment: %s (%s)",
                        cp.getTransactionId(),
                        cp.getCryptoCurrency());
            };
            
            return new AuditEntry(
                payment.getTransactionId(),
                LocalDateTime.now(),
                details,
                payment.getAmount()
            );
        }

        private String maskCardNumber(String cardNumber) {
            if (cardNumber == null || cardNumber.length() < 4) return "****";
            return "*".repeat(cardNumber.length() - 4) + 
                   cardNumber.substring(cardNumber.length() - 4);
        }
    }

    record AuditEntry(String transactionId, 
                     LocalDateTime timestamp,
                     String details,
                     double amount) {}
}
