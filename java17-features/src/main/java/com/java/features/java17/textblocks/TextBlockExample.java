package com.java.features.java17.textblocks;

/**
 * Demonstrates Text Blocks feature stabilized in Java 15 and enhanced in Java 17.
 * Text Blocks provide a clean and readable way to represent multi-line strings
 * while preserving formatting and reducing escape sequence usage.
 *
 * Key features demonstrated:
 * - Multi-line string literals with preserved indentation
 * - String formatting with .formatted()
 * - HTML content representation
 * - JSON configuration examples
 * - SQL query formatting
 * - String interpolation techniques
 *
 * Example usage:
 * ```java
 * String json = getJsonConfig("MyApp", "1.0.0", 8080);
 * String html = getHtmlTemplate("Welcome", "Hello World");
 * String sql = getComplexSqlQuery("users", 10);
 * ```
 */
public class TextBlockExample {

    // HTML example using text block
    public static String getHtmlTemplate(String title, String content) {
        return """
            <!DOCTYPE html>
            <html>
                <head>
                    <title>%s</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            margin: 20px;
                        }
                        .content {
                            padding: 10px;
                            border: 1px solid #ccc;
                        }
                    </style>
                </head>
                <body>
                    <div class="content">
                        %s
                    </div>
                </body>
            </html>
            """.formatted(title, content);
    }

    // SQL query example using text block
    public static String getComplexSqlQuery(String tableName, int limit) {
        return """
            SELECT u.id, u.name, u.email,
                   COUNT(o.id) as order_count,
                   SUM(o.total_amount) as total_spent
            FROM %s u
            LEFT JOIN orders o ON u.id = o.user_id
            WHERE u.status = 'ACTIVE'
              AND o.created_at >= DATE_SUB(NOW(), INTERVAL 1 YEAR)
            GROUP BY u.id, u.name, u.email
            HAVING order_count > 0
            ORDER BY total_spent DESC
            LIMIT %d
            """.formatted(tableName, limit);
    }

    // JSON example using text block
    public static String getJsonConfig(String appName, String version, int port) {
        return """
            {
                "application": {
                    "name": "%s",
                    "version": "%s",
                    "settings": {
                        "port": %d,
                        "environment": "production",
                        "features": {
                            "logging": true,
                            "metrics": true,
                            "tracing": false
                        }
                    },
                    "security": {
                        "enabled": true,
                        "ssl": true,
                        "allowed_origins": [
                            "https://example.com",
                            "https://api.example.com"
                        ]
                    }
                }
            }
            """.formatted(appName, version, port);
    }

    // XML example using text block
    public static String getXmlConfig(String serviceName, String endpoint, int timeout) {
        return """
            <?xml version="1.0" encoding="UTF-8"?>
            <configuration>
                <service name="%s">
                    <endpoint>%s</endpoint>
                    <settings>
                        <timeout>%d</timeout>
                        <retry>
                            <maxAttempts>3</maxAttempts>
                            <delay>1000</delay>
                        </retry>
                        <security>
                            <enabled>true</enabled>
                            <authentication>
                                <type>OAuth2</type>
                                <scope>read write</scope>
                            </authentication>
                        </security>
                    </settings>
                </service>
            </configuration>
            """.formatted(serviceName, endpoint, timeout);
    }
}
