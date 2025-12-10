# --- Build Stage ---
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /workspace

COPY pom.xml .
COPY src ./src

RUN mvn -B -DskipTests clean package

# --- Run Stage ---
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy ANY jar from target folder
COPY --from=build /workspace/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
