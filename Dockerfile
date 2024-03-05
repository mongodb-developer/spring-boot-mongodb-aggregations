FROM amazoncorretto:21
WORKDIR /app
COPY target/springboot-mongo-atlas-0.0.1-SNAPSHOT.jar Application.jar
EXPOSE 8080
CMD ["java", "-jar", "Application.jar"]
