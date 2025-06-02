# Stage 1: Build the JAR using Gradle
FROM gradle:8.5-jdk21 AS builder
COPY . /app
WORKDIR /app
RUN chmod +x gradlew && ./gradlew build

# Stage 2: Run the app
FROM amazoncorretto:21.0.4-alpine3.18
WORKDIR /app
COPY --from=builder /app/build/libs/resource-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]