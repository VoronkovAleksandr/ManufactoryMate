FROM openjdk:17.0.2-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/manufactorymate
ENV SERVER_PORT: 80
ENTRYPOINT ["java","-jar","/app.jar"]