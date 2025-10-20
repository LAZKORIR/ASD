# Use lightweight Maven with JDK 17 (Alpine)
FROM maven:3.9.8-eclipse-temurin-17-alpine

# Set working directory
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the Spring Boot JAR (skip tests for speed)
RUN mvn clean package -DskipTests

# Expose the application port
EXPOSE 8080

# Run the Spring Boot app
CMD ["java", "-jar", "target/api-gateway-1.0.jar"]
