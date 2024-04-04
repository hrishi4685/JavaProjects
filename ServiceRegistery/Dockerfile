FROM openjdk:17-jdk-alpine
COPY target/ServiceRegistry.jar /app/ServiceRegistry.jar
WORKDIR /app
CMD ["java", "-jar", "ServiceRegistry.jar"]