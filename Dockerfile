# Install maven
FROM maven:3.9.9-amazoncorretto-21-al2023 AS build
WORKDIR /app

# Install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Build java application
COPY src ./src
RUN mvn package -DskipTests

# Install Java
FROM amazoncorretto:21.0.7-al2023-headless
WORKDIR /app

# Copy from build into java env
COPY --from=build /app/target/*.jar app.jar

# Expose the port
EXPOSE 8080

# Run The application
ENTRYPOINT ["java", "-jar", "app.jar"]