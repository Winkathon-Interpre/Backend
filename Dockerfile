# Build
FROM gradle:8.10.0-jdk17 AS build

WORKDIR /app

COPY . .
RUN gradle bootJar

# Runtime
FROM eclipse-temurin:17

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
