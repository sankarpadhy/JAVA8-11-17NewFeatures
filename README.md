# Java Features Demo Project

This project demonstrates the evolution of Java features from version 8 through 17, organized in a modular structure with Docker-based development environments.

## Project Structure

```
Java8-11-17/
├── docker/                     # Docker configuration files
│   ├── java8/
│   ├── java9/
│   ├── java11/
│   └── java17/
├── java8-features/            # Java 8 feature demonstrations
├── java9-features/            # Java 9 feature demonstrations
├── java11-features/           # Java 11 feature demonstrations
├── java17-features/           # Java 17 feature demonstrations
├── docker-compose.yml         # Docker Compose configuration
└── pom.xml                    # Parent POM file
```

## Module Overview

### 1. java8-features
- Demonstrates Java 8 features including:
  - Lambda expressions
  - Stream API
  - Date/Time API
  - Optional class
  - Default methods

### 2. java9-features
- Showcases Java 9 enhancements:
  - Module system
  - Collection factory methods
  - Private interface methods
  - Stream API improvements

### 3. java11-features
- Highlights Java 11 additions:
  - Local variable syntax for lambda
  - HTTP Client API
  - String API enhancements
  - File API improvements

### 4. java17-features
- Latest Java features including:
  - Pattern matching
  - Records
  - Sealed classes
  - Text blocks

## Maven Structure

The project uses a profile-based Maven structure for managing different Java version features. Each module is activated through its corresponding profile.

### Parent POM (Root `pom.xml`)

```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.java.features</groupId>
    <artifactId>java-features-demo</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>
    
    <!-- Profile-based module activation -->
    <profiles>
        <profile>
            <id>java8</id>
            <modules>
                <module>java8-features</module>
            </modules>
        </profile>
        <profile>
            <id>java9</id>
            <modules>
                <module>java9-features</module>
            </modules>
        </profile>
        <profile>
            <id>java11</id>
            <modules>
                <module>java11-features</module>
            </modules>
        </profile>
        <profile>
            <id>java17</id>
            <modules>
                <module>java17-features</module>
            </modules>
        </profile>
    </profiles>
    
    <!-- Common properties used across all modules -->
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <junit.version>5.9.2</junit.version>
        <maven.compiler.plugin.version>3.11.0</maven.compiler.plugin.version>
        <maven.surefire.plugin.version>2.22.2</maven.surefire.plugin.version>
    </properties>
    
    <!-- Dependency Management - Versions for all modules -->
    <dependencyManagement>
        <dependencies>
            <!-- JUnit Jupiter API -->
            <dependency>
                <groupId>org.junit.jupiter</groupId>
                <artifactId>junit-jupiter-api</artifactId>
                <version>${junit.version}</version>
                <scope>test</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
    
    <!-- Common build configuration -->
    <build>
        <pluginManagement>
            <plugins>
                <!-- Compiler plugin configuration -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>${maven.compiler.plugin.version}</version>
                </plugin>
                
                <!-- Surefire plugin for tests -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>${maven.surefire.plugin.version}</version>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
</project>
```

### Parent POM Features

1. **Project Packaging (`<packaging>pom</packaging>`)**
   - Declares this as a parent/aggregator project
   - Does not produce a JAR/WAR artifact
   - Used to manage multiple child modules centrally

2. **Profile-Based Module Management (`<profiles>`)**
   - Enables selective module activation based on Java versions
   - Each profile (java8, java9, java11, java17) activates its corresponding module
   - Allows isolated building and testing of version-specific features
   - Example usage: `mvn clean install -P java8` to build only Java 8 features

3. **Centralized Property Management (`<properties>`)**
   - Defines version numbers and configuration values
   - Ensures consistency across all modules
   - Includes:
     * Java compiler versions
     * Dependencies versions (e.g., JUnit)
     * Plugin versions
     * Project-wide settings (e.g., encoding)

4. **Dependency Version Control (`<dependencyManagement>`)**
   - Centralizes dependency versions for all modules
   - Child modules inherit versions without explicitly declaring them
   - Prevents version conflicts across modules
   - Makes dependency updates easier to manage

5. **Plugin Configuration Management (`<pluginManagement>`)**
   - Standardizes build plugin configurations
   - Ensures consistent build behavior across modules
   - Includes:
     * Compiler settings for each Java version
     * Test execution configurations
     * Code coverage and quality tools
     * Resource handling

### Module POM (e.g., `java8-features/pom.xml`)

```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    
    <!-- Parent POM reference -->
    <parent>
        <groupId>com.java.features</groupId>
        <artifactId>java-features-demo</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    
    <artifactId>java8-features</artifactId>
    
    <!-- Java 8 specific properties -->
    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
    
    <!-- Module specific dependencies -->
    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <!-- Version inherited from parent -->
        </dependency>
        
        <!-- Additional Java 8 specific dependencies -->
        <dependency>
            <groupId>com.google.code.findbugs</groupId>
            <artifactId>jsr305</artifactId>
            <version>3.0.2</version>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <!-- Compiler configuration for Java 8 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${maven.compiler.source}</source>
                    <target>${maven.compiler.target}</target>
                </configuration>
            </plugin>
            
            <!-- Test configuration -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <configuration>
                    <forkCount>0</forkCount>
                    <reuseForks>false</reuseForks>
                    <useSystemClassLoader>false</useSystemClassLoader>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### Key Concepts Explained

1. **Parent POM Features**:
   - `<packaging>pom</packaging>`: Indicates this is a parent project
   - `<profiles>`: Lists all child modules with their corresponding profiles
   - `<properties>`: Defines variables used across modules
   - `<dependencyManagement>`: Controls versions of dependencies
   - `<pluginManagement>`: Defines common plugin configurations

2. **Module POM Features**:
   - `<parent>`: References the parent POM
   - Module-specific `<properties>`: Override or add to parent properties
   - `<dependencies>`: Module-specific dependencies
   - Version-specific plugin configurations

3. **Dependency Management**:
   - Parent POM defines versions in `<dependencyManagement>`
   - Child modules don't need to specify versions for managed dependencies
   - Ensures version consistency across modules

4. **Build Configuration**:
   - Parent POM defines common plugin configurations
   - Modules can override or extend these configurations
   - Specific Java version settings in each module

5. **Test Configuration**:
   - Common test framework (JUnit 5) defined in parent
   - Module-specific Surefire plugin settings
   - Custom test configurations per Java version

This structure allows for:
- Consistent dependency versions across modules
- Version-specific Java configurations
- Centralized build management
- Module-specific customizations when needed

## Docker Environment

### Understanding Docker Concepts

1. **Docker Images vs Containers**:
   - An image is like a blueprint (e.g., OpenJDK 8)
   - A container is a running instance of that image
   - Multiple containers can run from the same image

2. **Volume Mounting**:
   - Allows sharing files between host and container
   - Changes in mounted files are immediately visible
   - Persists data even after container stops

3. **Docker Compose**:
   - Tool for defining multi-container applications
   - Uses YAML format for configuration
   - Manages networks, volumes, and services

### Project Docker Structure

```plaintext
docker/
├── java8/
│   └── Dockerfile.dev         # Java 8 development environment
├── java9/
│   └── Dockerfile.dev         # Java 9 development environment
├── java11/
│   └── Dockerfile.dev         # Java 11 development environment
└── java17/
    └── Dockerfile.dev         # Java 17 development environment
```

### Development Dockerfile Example (java8/Dockerfile.dev)

```dockerfile
# Base image with OpenJDK 8
FROM openjdk:8-jdk

# Install required tools
RUN apt-get update && apt-get install -y \
    git \
    vim \
    curl \
    wget \
    maven \
    && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy Maven files for dependency caching
COPY ./pom.xml /app/
COPY ./java8-features/pom.xml /app/java8-features/

# Download dependencies
RUN mvn dependency:go-offline -B -P java8

# Enable remote debugging
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
```

### Docker Compose Configuration (docker-compose.yml)

```yaml
version: '3.8'

services:
  # Java 8 Development Environment
  java8-dev:
    build:
      context: .
      dockerfile: docker/java8/Dockerfile.dev
    profiles: ["dev"]  # Only start when dev profile is active
    volumes:
      # Mount source code directory
      - .:/app
      # Share Maven repository
      - maven-repo:/root/.m2
    environment:
      # Maven memory settings
      - MAVEN_OPTS=-Xmx512m
    ports:
      # Application port
      - "8080:8080"
      # Debug port
      - "5005:5005"

  # Similar configurations for other Java versions...

volumes:
  maven-repo:  # Named volume for Maven repository
```

### Key Features Explained

1. **Development Profiles**
   ```yaml
   profiles: ["dev"]
   ```
   - Allows selective service startup
   - Start specific environments: `docker compose --profile dev up java8-dev`
   - Useful for running multiple Java versions

2. **Volume Mounting**
   ```yaml
   volumes:
     - .:/app                 # Source code mounting
     - maven-repo:/root/.m2   # Maven repository
   ```
   - `.:/app`: Mounts current directory to /app in container
   - `maven-repo:/root/.m2`: Shares Maven dependencies across containers
   - Changes to source code are immediately reflected

3. **Maven Repository Caching**
   ```yaml
   volumes:
     maven-repo:  # Named volume definition
   ```
   - Persists downloaded dependencies
   - Speeds up subsequent builds
   - Shared across all Java version containers

4. **Debug Port Mapping**
   ```yaml
   ports:
     - "5005:5005"  # Host:Container
   ```
   - Enables remote debugging
   - Different port for each Java version
   - Format: "HOST_PORT:CONTAINER_PORT"

5. **Environment Variables**
   ```yaml
   environment:
     - MAVEN_OPTS=-Xmx512m
     - JAVA_TOOL_OPTIONS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005
   ```
   - Configure JVM settings
   - Set build tool options
   - Enable debugging features

### Docker Networking and Container Communication

1. **Port Mapping**
   ```yaml
   ports:
     - "8080:8080"  # App port
     - "5005:5005"  # Debug port
   ```
   - Format: "HOST_PORT:CONTAINER_PORT"
   - `8080:8080`: Maps container's app port to host
   - `5005:5005`: Maps container's debug port to host
   - Different host ports for each Java version to avoid conflicts

2. **Container Networks**
   - Docker Compose creates a default network
   - All services can communicate with each other
   - Each container gets a DNS name matching its service name

3. **Service Discovery**
   ```yaml
   services:
     java8-dev:
       hostname: java8-dev
     java9-dev:
       hostname: java9-dev
   ```
   - Containers can reference each other by service name
   - Automatic DNS resolution within the network
   - Useful for microservices architecture

4. **Network Isolation**
   ```yaml
   networks:
     java-dev-net:
       driver: bridge
   ```
   - Creates isolated network for services
   - Improves security
   - Controls container communication

### Advanced Docker Features

1. **Health Checks**
   ```yaml
   services:
     java8-dev:
       healthcheck:
         test: ["CMD", "curl", "-f", "http://localhost:8080/health"]
         interval: 30s
         timeout: 10s
         retries: 3
   ```
   - Monitors container health
   - Automatically restarts unhealthy containers
   - Useful for production environments

2. **Resource Limits**
   ```yaml
   services:
     java8-dev:
       deploy:
         resources:
           limits:
             cpus: '2'
             memory: 2G
           reservations:
             cpus: '1'
             memory: 1G
   ```
   - Controls container resource usage
   - Prevents resource exhaustion
   - Important for multi-container setups

3. **Dependency Management**
   ```yaml
   services:
     java8-dev:
       depends_on:
         - database
   ```
   - Defines service startup order
   - Ensures dependencies are ready
   - Manages complex application stacks

### Development Tips

1. **Using Docker Compose Profiles**
   ```bash
   # Start all development environments
   docker compose --profile dev up

   # Start specific Java version
   docker compose --profile dev up java8-dev

   # Run tests in all environments
   docker compose --profile test up
   ```

2. **Debugging in Container**
   ```bash
   # View container logs
   docker compose logs -f java8-dev

   # Execute command in running container
   docker compose exec java8-dev mvn test

   # Open shell in container
   docker compose exec java8-dev /bin/bash
   ```

3. **Volume Management**
   ```bash
   # List volumes
   docker volume ls

   # Clean up unused volumes
   docker volume prune

   # Inspect volume
   docker volume inspect maven-repo
   ```

### Common Issues and Solutions

1. **Port Conflicts**
   - Error: "port is already allocated"
   - Solution: Change host port mapping
   - Check running containers: `docker ps`

2. **Volume Permissions**
   - Issue: Cannot write to mounted volumes
   - Solution: Check file permissions
   - Use `chown` in Dockerfile if needed

3. **Memory Issues**
   - Problem: Container crashes with OOM
   - Solution: Increase memory limits
   - Monitor with `docker stats`

### Security Best Practices

1. **Container Security**
   - Use official base images
   - Keep images updated
   - Scan for vulnerabilities

2. **Network Security**
   - Limit exposed ports
   - Use network isolation
   - Implement proper firewalls

3. **Data Security**
   - Protect sensitive data in volumes
   - Use secrets management
   - Regular security audits

## Running the Project

### Development Mode

Start a specific Java version environment:
```bash
# For Java 8 features
docker compose --profile dev up --build java8-dev

# For Java 9 features
docker compose --profile dev up --build java9-dev

# For Java 11 features
docker compose --profile dev up --build java11-dev

# For Java 17 features
docker compose --profile dev up --build java17-dev
```

### Build All Modules
```bash
docker compose --profile dev up --build java8-dev java9-dev java11-dev java17-dev
```

## Testing

Each module contains unit tests demonstrating the features:
- Tests are run using JUnit 5
- Surefire plugin configured for each Java version
- Preview features enabled where needed

## Testing Configuration

### Maven Surefire Plugin

1. **Basic Configuration**
   ```xml
   <plugin>
       <groupId>org.apache.maven.plugins</groupId>
       <artifactId>maven-surefire-plugin</artifactId>
       <version>3.1.2</version>
       <configuration>
           <!-- Disable forking to avoid JVM startup overhead -->
           <forkCount>0</forkCount>
           <!-- Run tests in current JVM -->
           <reuseForks>false</reuseForks>
           <!-- Disable parallel execution for stability -->
           <parallel>none</parallel>
           <!-- Configure JVM arguments for tests -->
           <argLine>
               --enable-preview
               -XX:+ShowCodeDetailsInExceptionMessages
           </argLine>
       </configuration>
   </plugin>
   ```

2. **Version-Specific Configurations**
   ```xml
   <!-- Java 8 Configuration -->
   <profile>
       <id>java8</id>
       <build>
           <plugins>
               <plugin>
                   <artifactId>maven-surefire-plugin</artifactId>
                   <configuration>
                       <!-- Java 8 specific settings -->
                       <argLine>-Xmx512m</argLine>
                   </configuration>
               </plugin>
           </plugins>
       </build>
   </profile>

   <!-- Java 17 Configuration -->
   <profile>
       <id>java17</id>
       <build>
           <plugins>
               <plugin>
                   <artifactId>maven-surefire-plugin</artifactId>
                   <configuration>
                       <!-- Enable preview features -->
                       <argLine>
                           --enable-preview
                           --add-opens java.base/java.lang=ALL-UNNAMED
                       </argLine>
                   </configuration>
               </plugin>
           </plugins>
       </build>
   </profile>
   ```

### JUnit 5 Integration

1. **Dependencies**
   ```xml
   <dependencies>
       <!-- JUnit Jupiter API -->
       <dependency>
           <groupId>org.junit.jupiter</groupId>
           <artifactId>junit-jupiter-api</artifactId>
           <version>5.9.2</version>
           <scope>test</scope>
       </dependency>
       <!-- JUnit Jupiter Engine -->
       <dependency>
           <groupId>org.junit.jupiter</groupId>
           <artifactId>junit-jupiter-engine</artifactId>
           <version>5.9.2</version>
           <scope>test</scope>
       </dependency>
       <!-- JUnit Jupiter Params -->
       <dependency>
           <groupId>org.junit.jupiter</groupId>
           <artifactId>junit-jupiter-params</artifactId>
           <version>5.9.2</version>
           <scope>test</scope>
       </dependency>
   </dependencies>
   ```

2. **Test Examples**
   ```java
   // Basic Test
   @Test
   void testBasicFeature() {
       // Test implementation
   }

   // Parameterized Test
   @ParameterizedTest
   @ValueSource(strings = {"value1", "value2"})
   void testWithParameters(String value) {
       // Test with parameters
   }

   // Conditional Test (Version Specific)
   @Test
   @EnabledOnJre(JRE.JAVA_17)
   void testJava17Feature() {
       // Java 17 specific test
   }
   ```

### Preview Features

1. **Enabling Preview Features**
   ```xml
   <plugin>
       <groupId>org.apache.maven.plugins</groupId>
       <artifactId>maven-compiler-plugin</artifactId>
       <configuration>
           <compilerArgs>
               <!-- Enable preview features -->
               <arg>--enable-preview</arg>
               <!-- Show preview feature warnings -->
               <arg>-Xlint:preview</arg>
           </compilerArgs>
       </configuration>
   </plugin>
   ```

2. **Version-Specific Preview Features**
   - Java 14: Records (Preview)
   - Java 15: Sealed Classes (Preview)
   - Java 16: Pattern Matching (Preview)
   - Java 17: Pattern Matching for switch (Preview)

### Test Execution

1. **Running Tests**
   ```bash
   # Run all tests
   mvn test

   # Run specific test class
   mvn test -Dtest=MyTestClass

   # Run with specific profile
   mvn test -P java8

   # Skip tests
   mvn install -DskipTests
   ```

2. **Test Categories**
   ```java
   // Tag for unit tests
   @Tag("unit")
   class UnitTest {
       // Unit test implementation
   }

   // Tag for integration tests
   @Tag("integration")
   class IntegrationTest {
       // Integration test implementation
   }
   ```

3. **Running Specific Test Categories**
   ```xml
   <configuration>
       <groups>unit</groups>
       <excludedGroups>integration</excludedGroups>
   </configuration>
   ```

### Test Reports

1. **Surefire Reports**
   - Location: `target/surefire-reports`
   - Format: XML and plain text
   - Contains test execution details
   - Includes failure information

2. **Coverage Reports with JaCoCo**
   ```xml
   <plugin>
       <groupId>org.jacoco</groupId>
       <artifactId>jacoco-maven-plugin</artifactId>
       <version>0.8.8</version>
       <executions>
           <execution>
               <id>prepare-agent</id>
               <goals>
                   <goal>prepare-agent</goal>
               </goals>
           </execution>
           <execution>
               <id>report</id>
               <phase>test</phase>
               <goals>
                   <goal>report</goal>
               </goals>
           </execution>
       </executions>
   </plugin>
   ```

   **Report Locations:**
   - HTML Report: `target/site/jacoco/index.html`
   - XML Report: `target/site/jacoco/jacoco.xml`
   - CSV Report: `target/site/jacoco/jacoco.csv`

   **Report Contents:**
   - Line Coverage
   - Branch Coverage
   - Complexity Metrics
   - Method Coverage
   - Class Coverage

   **Viewing Reports:**
   ```bash
   # Generate coverage report
   mvn clean test jacoco:report

   # Open HTML report in browser (Windows)
   start target/site/jacoco/index.html
   ```

   **Coverage Enforcement:**
   ```xml
   <execution>
       <id>check</id>
       <goals>
           <goal>check</goal>
       </goals>
       <configuration>
           <rules>
               <rule>
                   <element>PACKAGE</element>
                   <limits>
                       <limit>
                           <counter>LINE</counter>
                           <value>COVEREDRATIO</value>
                           <minimum>0.80</minimum>
                       </limit>
                   </limits>
               </rule>
           </rules>
       </configuration>
   </execution>
   ```

3. **Aggregate Reports**
   ```xml
   <execution>
       <id>report-aggregate</id>
       <phase>verify</phase>
       <goals>
           <goal>report-aggregate</goal>
       </goals>
       <configuration>
           <dataFileIncludes>
               <dataFileInclude>**/jacoco.exec</dataFileInclude>
           </dataFileIncludes>
           <outputDirectory>target/site/jacoco-aggregate</outputDirectory>
       </configuration>
   </execution>
   ```
   - Location: `target/site/jacoco-aggregate/index.html`
   - Combines coverage from all modules
   - Useful for multi-module projects

4. **Coverage Badges**
   ```xml
   <plugin>
       <groupId>com.github.terma</groupId>
       <artifactId>gradle-badges</artifactId>
       <version>1.2.1</version>
       <configuration>
           <subject>coverage</subject>
           <status>${jacoco.coverage.percentage}%</status>
           <color>green</color>
           <path>target/coverage-badge.svg</path>
       </configuration>
   </plugin>
   ```
   - Creates coverage badge for README
   - Updates automatically with builds
   - Location: `target/coverage-badge.svg`

5. **IDE Integration**
   - IntelliJ IDEA: Coverage tab in Run window
   - Eclipse: EclEmma plugin
   - VSCode: Coverage Gutters extension

6. **Continuous Integration**
   ```yaml
   # GitHub Actions example
   - name: Generate Coverage Report
     run: mvn clean verify
   
   - name: Upload Coverage Report
     uses: actions/upload-artifact@v2
     with:
       name: coverage-report
       path: target/site/jacoco
   ```
   - Automated coverage reporting
   - Historical trend analysis
   - Pull request coverage checks

### Best Practices

1. **Test Organization**
   - Group related tests in test classes
   - Use meaningful test names
   - Follow naming conventions
   - Separate unit and integration tests

2. **Test Isolation**
   - Each test should be independent
   - Clean up test resources
   - Use `@BeforeEach` and `@AfterEach`
   - Avoid test interdependencies

3. **Performance**
   - Minimize test execution time
   - Use appropriate test scopes
   - Configure parallel execution when safe
   - Profile test execution time

4. **Maintenance**
   - Keep tests up to date
   - Remove obsolete tests
   - Document test requirements
   - Version control test data

### Troubleshooting

1. **Common Issues**
   - Test execution timeout
   - Memory constraints
   - Version compatibility
   - Resource conflicts

2. **Solutions**
   - Adjust timeout settings
   - Configure memory settings
   - Check Java version compatibility
   - Clean test resources

3. **Debugging**
   ```xml
   <configuration>
       <debugger>true</debugger>
       <debugPort>5005</debugPort>
   </configuration>
   ```
