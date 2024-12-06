package com.java.features.java9.reflection;

public class ReflectionExample {
    private String privateField = "This is private";
    
    private void privateMethod() {
        System.out.println("This is a private method");
    }
    
    // Internal class that will be accessed via reflection
    private static class InternalConfig {
        private String configValue = "default";
        
        private InternalConfig() {}
        
        private void updateConfig(String value) {
            this.configValue = value;
        }
    }
}
