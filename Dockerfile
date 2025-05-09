FROM openjdk:21-jdk-slim

LABEL authors="Maksym Osetsymskyi"
WORKDIR /app

COPY target/LibraryManagement-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 5432

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
