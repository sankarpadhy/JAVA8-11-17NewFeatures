package com.java.features.java8.lambda;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LambdaBasicsTest {

    @Test
    void testIsPositive() {
        assertTrue(LambdaBasics.isPositive(5));
        assertFalse(LambdaBasics.isPositive(-5));
        assertFalse(LambdaBasics.isPositive(0));
    }

    @Test
    void testGetStringLength() {
        assertEquals(5, LambdaBasics.getStringLength("Hello"));
        assertEquals(0, LambdaBasics.getStringLength(""));
        assertEquals(10, LambdaBasics.getStringLength("HelloWorld"));
    }

    @Test
    void testProcessElements() {
        // This test just verifies that the method runs without throwing exceptions
        List<String> testList = Arrays.asList("Hello", "World");
        assertDoesNotThrow(() -> LambdaBasics.processElements(testList));
    }

    @Test
    void testGetRandomNumber() {
        int randomNum = LambdaBasics.getRandomNumber();
        assertTrue(randomNum > 0 && randomNum <= 100);
    }

    @Test
    void testIsPositiveAndEven() {
        assertTrue(LambdaBasics.isPositiveAndEven(4));
        assertFalse(LambdaBasics.isPositiveAndEven(3));
        assertFalse(LambdaBasics.isPositiveAndEven(-2));
        assertFalse(LambdaBasics.isPositiveAndEven(0));
    }

    @Test
    void testSortStrings() {
        List<String> input = Arrays.asList("Zebra", "apple", "Banana");
        List<String> sorted = LambdaBasics.sortStrings(input);
        assertEquals(Arrays.asList("apple", "Banana", "Zebra"), sorted);
    }
}
