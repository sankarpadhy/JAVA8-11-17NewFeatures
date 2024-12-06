package com.java.features.java8.nashorn;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.script.ScriptException;

/**
 * Tests for the NashornExample class.
 * Verifies JavaScript execution and integration features.
 */
public class NashornExampleTest {
    private NashornExample nashornExample;

    @Before
    public void setUp() {
        nashornExample = new NashornExample();
    }

    @Test
    public void testSimpleExpressionEvaluation() throws ScriptException {
        Object result = nashornExample.evaluateExpression("40 + 2");
        assertEquals(42.0, result);
    }

    @Test
    public void testStringManipulation() throws ScriptException {
        Object result = nashornExample.evaluateExpression("'Hello, ' + 'Nashorn!'");
        assertEquals("Hello, Nashorn!", result);
    }

    @Test
    public void testJavaScriptFunctionExecution() throws ScriptException, NoSuchMethodException {
        // Define a JavaScript function
        nashornExample.executeJavaScript(
            "function greet(name) { return 'Hello, ' + name + '!'; }"
        );

        // Call the function
        Object result = nashornExample.callJavaScriptFunction("greet", "Java");
        assertEquals("Hello, Java!", result);
    }

    @Test
    public void testMathOperations() throws ScriptException {
        Object result = nashornExample.evaluateExpression("Math.pow(2, 3)");
        assertEquals(8.0, result);
    }

    @Test
    public void testArrayManipulation() throws ScriptException {
        nashornExample.executeJavaScript(
            "var arr = [1, 2, 3, 4, 5];" +
            "var sum = arr.reduce(function(a, b) { return a + b; }, 0);"
        );
        Object result = nashornExample.evaluateExpression("sum");
        assertEquals(15.0, result);
    }

    @Test
    public void testObjectProperties() throws ScriptException {
        nashornExample.executeJavaScript(
            "var person = {" +
            "   name: 'John'," +
            "   age: 30," +
            "   toString: function() { return this.name + ' is ' + this.age; }" +
            "};"
        );
        Object result = nashornExample.evaluateExpression("person.toString()");
        assertEquals("John is 30", result);
    }

    @Test(expected = ScriptException.class)
    public void testInvalidJavaScript() throws ScriptException {
        nashornExample.evaluateExpression("invalid + syntax + here");
    }

    @Test
    public void testJavaScriptLoop() throws ScriptException {
        nashornExample.executeJavaScript(
            "var result = '';" +
            "for (var i = 0; i < 3; i++) {" +
            "    result += i + ',';" +
            "}"
        );
        Object result = nashornExample.evaluateExpression("result");
        assertEquals("0,1,2,", result);
    }

    @Test
    public void testJavaScriptJSON() throws ScriptException {
        nashornExample.executeJavaScript(
            "var jsonStr = JSON.stringify({name: 'Test', value: 42});"
        );
        Object result = nashornExample.evaluateExpression("jsonStr");
        assertEquals("{\"name\":\"Test\",\"value\":42}", result);
    }
}
