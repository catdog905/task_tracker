# syntax=docker/dockerfile:1
FROM openjdk:16-alpine
WORKDIR /app

ADD /target/task_tracker-0.0.1-SNAPSHOT.jar app.jar

ENV JAVA_OPTS ""
ENTRYPOINT ["java", "-jar", "app.jar"]