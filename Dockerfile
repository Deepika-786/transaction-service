FROM openjdk:17-jdk-slim
LABEL maintainer="deepikachaudhary.577@gmail.com"
WORKDIR /app
COPY target/transaction-service-0.0.1-SNAPSHOT.jar /app/transaction-service.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "transaction-service.jar"]