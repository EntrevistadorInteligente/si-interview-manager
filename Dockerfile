FROM amazoncorretto:17-alpine3.18-jdk

EXPOSE 8082

RUN mkdir -p /app/

COPY target/generador-feedback-0.0.1-SNAPSHOT.jar /app/generador-feedback.jar

ENTRYPOINT ["java", "-jar", "/app/generador-feedback.jar"]
