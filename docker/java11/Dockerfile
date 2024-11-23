FROM openjdk:11-jdk-slim

WORKDIR /app
COPY ../java11-features/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
