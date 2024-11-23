FROM openjdk:8-jdk-slim

WORKDIR /app
COPY ../java8-features/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
