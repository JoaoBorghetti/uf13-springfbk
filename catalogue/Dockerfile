# syntax=docker/dockerfile:1.2
FROM maven:3-eclipse-temurin-11-alpine AS build

COPY src/ /tmp/src/
COPY pom.xml /tmp/

ENV NAME catalogue
ENV TYPE prod

WORKDIR /tmp
# --mount=type=bind,target=/root/.m2,source=/root/.m2,from=smartcommunitylab/af-catalogue:11-alpine-cache
RUN mvn clean package -DskipTests

FROM amazoncorretto:11-alpine AS run

RUN addgroup --system catalogue
RUN adduser -G catalogue -h /home/catalogue -D catalogue

COPY --from=build --chown=catalogue:catalogue /tmp/target/catalogue-0.0.1-SNAPSHOT.jar /tmp/catalogue-0.0.1-SNAPSHOT.jar
EXPOSE 7777
USER catalogue

ENTRYPOINT [ "java", "-jar", "/tmp/catalogue-0.0.1-SNAPSHOT.jar" ]
HEALTHCHECK --interval=30s --timeout=3s --retries=3 --start-period=60s CMD wget --spider http://localhost:7777/actuator/health || exit 1