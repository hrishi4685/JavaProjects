FROM openjdk:17-jdk-alpine
COPY target/ApiGateway.jar /app/ApiGateway.jar
WORKDIR /app
CMD ["java", "-jar", "ApiGateway.jar"]