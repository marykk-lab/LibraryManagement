FROM maven:latest AS builder
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim AS final
WORKDIR /app
LABEL authors="Maksym Osetsymskyi"

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 9000
ENTRYPOINT ["java", "-jar", "app.jar"]

