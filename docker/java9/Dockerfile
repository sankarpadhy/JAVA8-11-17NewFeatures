FROM openjdk:9-jdk-slim

WORKDIR /app
COPY ../java9-features/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]