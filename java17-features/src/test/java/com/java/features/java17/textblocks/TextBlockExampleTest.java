package com.java.features.java17.textblocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Text Blocks Examples")
class TextBlockExampleTest {

    @Test
    @DisplayName("HTML template generation")
    void testHtmlTemplate() {
        String html = TextBlockExample.getHtmlTemplate("Test Page", "Hello, World!");
        
        // Verify basic structure
        assertTrue(html.contains("<!DOCTYPE html>"));
        assertTrue(html.contains("<title>Test Page</title>"));
        assertTrue(html.contains("Hello, World!"));
        
        // Verify formatting
        assertTrue(html.contains("font-family: Arial, sans-serif"));
        assertTrue(html.contains("class=\"content\""));
    }

    @Test
    @DisplayName("SQL query generation")
    void testSqlQuery() {
        String sql = TextBlockExample.getComplexSqlQuery("users", 10);
        
        // Verify query structure
        assertTrue(sql.contains("SELECT"));
        assertTrue(sql.contains("FROM users"));
        assertTrue(sql.contains("LIMIT 10"));
        
        // Verify proper formatting
        assertTrue(sql.contains("LEFT JOIN"));
        assertTrue(sql.contains("GROUP BY"));
        assertTrue(sql.contains("HAVING"));
    }

    @Test
    @DisplayName("JSON configuration generation")
    void testJsonConfig() {
        String json = TextBlockExample.getJsonConfig("MyApp", "1.0.0", 8080);
        
        // Verify JSON structure
        assertTrue(json.contains("\"name\": \"MyApp\""));
        assertTrue(json.contains("\"version\": \"1.0.0\""));
        assertTrue(json.contains("\"port\": 8080"));
        
        // Verify nested objects
        assertTrue(json.contains("\"security\""));
        assertTrue(json.contains("\"features\""));
        assertTrue(json.contains("\"allowed_origins\""));
    }

    @Test
    @DisplayName("XML configuration generation")
    void testXmlConfig() {
        String xml = TextBlockExample.getXmlConfig("UserService", "http://api.example.com", 5000);
        
        // Verify XML structure
        assertTrue(xml.contains("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
        assertTrue(xml.contains("<service name=\"UserService\">"));
        assertTrue(xml.contains("<endpoint>http://api.example.com</endpoint>"));
        assertTrue(xml.contains("<timeout>5000</timeout>"));
        
        // Verify nested elements
        assertTrue(xml.contains("<security>"));
        assertTrue(xml.contains("<authentication>"));
        assertTrue(xml.contains("<type>OAuth2</type>"));
    }
}
