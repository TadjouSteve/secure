FROM openjdk:17-jdk-slim

EXPOSE 9010

COPY target/secure-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
