# syntax=docker/dockerfile:1.2
FROM maven:3-eclipse-temurin-11-alpine AS build

COPY src/ /tmp/src/
COPY pom.xml /tmp/

ENV NAME purchase
ENV TYPE prod

WORKDIR /tmp
# --mount=type=bind,target=/root/.m2,source=/root/.m2,from=smartcommunitylab/af-catalogue:11-alpine-cache
RUN mvn clean package -DskipTests

FROM amazoncorretto:11-alpine AS run

RUN addgroup -g 3000 purchase
RUN adduser -u 3000 -G purchase -h /home/purchase -D purchase

COPY --from=build --chown=purchase:purchase /tmp/target/purchase-0.0.1-SNAPSHOT.jar /tmp/purchase-0.0.1-SNAPSHOT.jar
EXPOSE 7788
USER purchase

ENTRYPOINT [ "java", "-jar", "/tmp/purchase-0.0.1-SNAPSHOT.jar" ]
HEALTHCHECK --interval=30s --timeout=3s --retries=3 --start-period=60s CMD wget --spider http://localhost:7788/actuator/health || exit 1