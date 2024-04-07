FROM eclipse-temurin:21-jre-alpine
EXPOSE 8080
ADD /build/libs/*.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]