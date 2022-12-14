# syntax=docker/dockerfile:1.2
FROM maven:3-eclipse-temurin-11-alpine AS build

COPY src/ /tmp/src/
COPY pom.xml /tmp/

ENV NAME eureka
ENV TYPE prod

WORKDIR /tmp
# --mount=type=bind,target=/root/.m2,source=/root/.m2,from=smartcommunitylab/af-eureka:8-alpine-cache
RUN  mvn clean package -DskipTests

FROM amazoncorretto:11-alpine AS run

RUN addgroup -g 4000 eureka
RUN adduser -u 4000 -G eureka -h /home/eureka -D eureka

COPY --from=build --chown=eureka:eureka /tmp/target/eureka-server-0.0.1-SNAPSHOT.jar /tmp/eureka-server-0.0.1-SNAPSHOT.jar
EXPOSE 8761
USER eureka

ENTRYPOINT [ "java", "-jar", "/tmp/eureka-server-0.0.1-SNAPSHOT.jar" ]
HEALTHCHECK --interval=30s --timeout=3s --retries=3 --start-period=60s CMD wget --spider http://localhost:8761/actuator/health || exit 1