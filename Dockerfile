FROM gradle:8-alpine AS build
COPY src /home/app/src
COPY build.gradle settings.gradle /home/app/
WORKDIR /home/app
RUN gradle clean build -x test --no-daemon

# Package stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /home/app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]