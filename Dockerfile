FROM alpine:latest as build

RUN apk update
RUN apk add openjdk17

COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-alpine

EXPOSE 9000

COPY --from=build ./build/libs/Examen-Mutante-ML-0.0.1-SNAPSHOT.jar ./docker.jar

ENTRYPOINT ["java", "-jar", "docker.jar"]