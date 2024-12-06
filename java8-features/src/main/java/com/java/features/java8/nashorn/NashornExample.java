package com.java.features.java8.nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.Invocable;
import java.io.FileReader;
import java.io.IOException;

/**
 * Demonstrates the Nashorn JavaScript engine introduced in Java 8.
 * Nashorn provides a lightweight, high-performance JavaScript runtime
 * environment integrated with the JVM.
 *
 * Key features demonstrated:
 * - JavaScript code execution from Java
 * - Java-JavaScript interoperability
 * - Script evaluation and bindings
 * - JavaScript function invocation
 * - Error handling between Java and JavaScript
 *
 * Note: Nashorn was deprecated in Java 11 and removed in Java 15.
 * For modern JavaScript execution in Java, consider using GraalJS.
 *
 * Example usage:
 * ```java
 * ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
 * 
 * // Execute JavaScript code
 * engine.eval("print('Hello from JavaScript!')");
 * 
 * // Pass Java objects to JavaScript
 * Bindings bindings = engine.createBindings();
 * bindings.put("message", "Hello");
 * engine.eval("print(message)", bindings);
 * ```
 *
 * @see javax.script.ScriptEngine
 * @see javax.script.ScriptEngineManager
 * @see javax.script.Bindings
 * @deprecated Nashorn JavaScript engine is deprecated since Java 11
 */
public class NashornExample {
    private final ScriptEngine engine;

    public NashornExample() {
        this.engine = new ScriptEngineManager().getEngineByName("nashorn");
    }

    /**
     * Evaluates a simple JavaScript expression.
     *
     * @param expression JavaScript expression to evaluate
     * @return Result of the evaluation
     * @throws ScriptException if the expression cannot be evaluated
     */
    public Object evaluateExpression(String expression) throws ScriptException {
        Object result = engine.eval(expression);
        // Convert numeric results to Double to match JavaScript behavior
        if (result instanceof Number) {
            return ((Number) result).doubleValue();
        }
        return result;
    }

    /**
     * Executes JavaScript code from a string.
     *
     * @param code JavaScript code to execute
     * @throws ScriptException if the code cannot be executed
     */
    public void executeJavaScript(String code) throws ScriptException {
        engine.eval(code);
    }

    /**
     * Calls a JavaScript function with parameters.
     *
     * @param functionName Name of the JavaScript function to call
     * @param args Arguments to pass to the function
     * @return Result of the function call
     * @throws ScriptException if the function cannot be called
     * @throws NoSuchMethodException if the function doesn't exist
     */
    public Object callJavaScriptFunction(String functionName, Object... args) 
            throws ScriptException, NoSuchMethodException {
        Invocable invocable = (Invocable) engine;
        return invocable.invokeFunction(functionName, args);
    }

    /**
     * Loads and executes a JavaScript file.
     *
     * @param filePath Path to the JavaScript file
     * @throws ScriptException if the file cannot be executed
     * @throws IOException if the file cannot be read
     */
    public void loadJavaScriptFile(String filePath) throws ScriptException, IOException {
        try (FileReader reader = new FileReader(filePath)) {
            engine.eval(reader);
        }
    }

    /**
     * Gets the underlying script engine.
     *
     * @return The Nashorn ScriptEngine instance
     */
    public ScriptEngine getEngine() {
        return engine;
    }
}
