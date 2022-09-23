# syntax=docker/dockerfile:1.2
FROM maven:3-eclipse-temurin-11-alpine AS build

COPY src/ /tmp/src/
COPY pom.xml /tmp/
# COPY --from=smartcommunitylab/af-purchase:11-alpine-cache /root/.m2 /root/.m2

ENV NAME purchase
ENV TYPE prod

WORKDIR /tmp

RUN --mount=type=bind,target=/root/.m2,source=/root/.m2,from=smartcommunitylab/af-purchase:11-alpine-cache mvn clean package -DskipTests

FROM amazoncorretto:11-alpine AS run

RUN addgroup -g 2000 purchase
RUN adduser -u 2000 -G purchase -h /home/purchase -D purchase

COPY --from=build --chown=purchase:purchase /tmp/target/purchase-0.0.1-SNAPSHOT.jar /tmp/purchase-0.0.1-SNAPSHOT.jar

USER purchase

ENTRYPOINT [ "java", "-jar", "/tmp/purchase-0.0.1-SNAPSHOT.jar" ]