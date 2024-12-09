# Use a Maven image to build the application
FROM maven:3.9.5-eclipse-temurin-17 as build

WORKDIR /app
COPY . /app

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Use a JDK image to run the application
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
COPY --from=build /app/target/professor-0.0.1-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]