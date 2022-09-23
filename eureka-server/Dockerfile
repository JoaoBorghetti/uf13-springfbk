# syntax=docker/dockerfile:1.2
FROM maven:3-eclipse-temurin-8-alpine AS build

COPY src/ /tmp/src/
COPY pom.xml /tmp/

ENV NAME eureka
ENV TYPE prod

WORKDIR /tmp

RUN --mount=type=bind,target=/root/.m2,source=/root/.m2,from=smartcommunitylab/af-eureka:8-alpine-cache mvn clean package -DskipTests

FROM amazoncorretto:8-alpine AS run

RUN addgroup -g 4000 eureka
RUN adduser -u 4000 -G eureka -h /home/eureka -D eureka

COPY --from=build --chown=eureka:eureka /tmp/target/eureka-server-0.0.1-SNAPSHOT.jar /tmp/eureka-server-0.0.1-SNAPSHOT.jar
EXPOSE 8761
USER eureka

ENTRYPOINT [ "java", "-jar", "/tmp/eureka-server-0.0.1-SNAPSHOT.jar" ]