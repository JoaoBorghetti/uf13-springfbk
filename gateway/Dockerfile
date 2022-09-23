# syntax=docker/dockerfile:1.2
FROM maven:3-eclipse-temurin-11-alpine AS build

COPY src/ /tmp/src/
COPY pom.xml /tmp/

ENV NAME gateway
ENV TYPE prod

WORKDIR /tmp
# --mount=type=bind,target=/root/.m2,source=/root/.m2,from=smartcommunitylab/af-eureka:8-alpine-cache
RUN  mvn clean package -DskipTests

FROM amazoncorretto:11-alpine AS run

RUN addgroup -g 4000 gateway
RUN adduser -u 4000 -G gateway -h /home/gateway -D gateway

COPY --from=build --chown=gateway:gateway /tmp/target/gateway-0.0.1-SNAPSHOT.jar /tmp/gateway-0.0.1-SNAPSHOT.jar
EXPOSE 8080
USER gateway

ENTRYPOINT [ "java", "-jar", "/tmp/gateway-0.0.1-SNAPSHOT.jar" ]