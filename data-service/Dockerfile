FROM openjdk:17.0.2-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV SERVER_PORT=80
ENV API_SCHEDULING_BASICURI=http://scheduling-server:80/api
ENV API_PRODUCT_BASICURI=http://product-server:80/api
ENV API_PROUCE_BASICURI=http://produce-cerver:80/api
ENTRYPOINT ["java","-jar","/app.jar"]