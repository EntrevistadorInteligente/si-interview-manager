FROM amazoncorretto:17-alpine3.18-jdk

EXPOSE 8082

RUN mkdir -p /app/

COPY target/generador-feedbackEntity-0.0.1-SNAPSHOT.jar /app/generador-feedbackEntity.jar

ENTRYPOINT ["java", "-jar", "/app/generador-feedbackEntity.jar"]
