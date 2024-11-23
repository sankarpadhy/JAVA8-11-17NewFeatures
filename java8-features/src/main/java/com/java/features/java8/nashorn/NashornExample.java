package com.java.features.java8.nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.Invocable;
import java.io.FileReader;
import java.io.IOException;

/**
 * Demonstrates the Nashorn JavaScript Engine features in Java 8
 */
public class NashornExample {

    private final ScriptEngine engine;

    public NashornExample() {
        this.engine = new ScriptEngineManager().getEngineByName("nashorn");
    }

    /**
     * Evaluates a simple JavaScript expression
     */
    public Object evaluateJavaScript(String script) throws ScriptException {
        return engine.eval(script);
    }

    /**
     * Demonstrates calling JavaScript functions from Java
     */
    public Object callJavaScriptFunction(String functionName, Object... args) throws ScriptException, NoSuchMethodException {
        // Define a sample JavaScript function
        engine.eval("function greet(name) { return 'Hello, ' + name + '!'; }");
        engine.eval("function sum(a, b) { return a + b; }");

        return ((Invocable) engine).invokeFunction(functionName, args);
    }

    /**
     * Demonstrates passing Java objects to JavaScript
     */
    public void passingJavaObjects() throws ScriptException {
        engine.put("javaString", "Hello from Java!");
        engine.eval("print(javaString);");
    }

    /**
     * Demonstrates loading and executing JavaScript file
     */
    public Object loadJavaScriptFile(String filePath) throws ScriptException, IOException {
        try (FileReader reader = new FileReader(filePath)) {
            return engine.eval(reader);
        }
    }

    /**
     * Demonstrates JavaScript array manipulation
     */
    public Object manipulateJavaScriptArray() throws ScriptException {
        return engine.eval("var arr = [1, 2, 3]; " +
                         "arr.map(function(x) { return x * 2; });");
    }

    /**
     * Demonstrates JavaScript object creation and manipulation
     */
    public Object createJavaScriptObject() throws ScriptException {
        return engine.eval(
            "var person = {" +
            "   name: 'John'," +
            "   age: 30," +
            "   toString: function() { " +
            "       return this.name + ' is ' + this.age + ' years old';" +
            "   }" +
            "};" +
            "person.toString();");
    }
}
