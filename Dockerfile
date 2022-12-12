# syntax=docker/dockerfile:1.3
ARG TARGET_VERSION=11
FROM maven:3-eclipse-temurin-${TARGET_VERSION}-alpine AS build
ARG TARGET_VERSION
COPY src/ /tmp/src/
COPY pom.xml /tmp/

WORKDIR /tmp

RUN mvn clean package -DskipTests

ARG TARGET_VERSION

FROM amazoncorretto:${TARGET_VERSION}-alpine AS run

RUN addgroup -g 4000 spring
RUN adduser -u 4000 -G spring -h /home/spring -D spring

ARG ARTIFACT_ID=artifact-id
ARG VERSION=0.0.1-SNAPSHOT

COPY --from=build --chown=spring:spring /tmp/target/${ARTIFACT_ID}-${VERSION}.jar app.jar

USER spring

ENTRYPOINT ["java","-jar","app.jar"]