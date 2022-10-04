FROM maven:3-eclipse-temurin-11-alpine AS build

COPY src/ /tmp/src/
COPY pom.xml /tmp/

ENV NAME user-service
ENV TYPE prod

WORKDIR /tmp
# --mount=type=bind,target=/root/.m2,source=/root/.m2,from=smartcommunitylab/config-server:8-alpine-cache 
RUN mvn clean package -DskipTests

#ENTRYPOINT [ "/bin/bash" ]
FROM amazoncorretto:11-alpine AS run

RUN addgroup -g 4000 user-service
RUN adduser -u 4000 -G user-service -h /home/user-service -D user-service

COPY --from=build --chown=config:config /tmp/target/user-service-0.0.1-SNAPSHOT.jar /tmp/user-service-0.0.1-SNAPSHOT.jar
EXPOSE 7888
USER user-service

ENTRYPOINT [ "java", "-jar", "/tmp/user-service-0.0.1-SNAPSHOT.jar" ]
HEALTHCHECK --interval=30s --timeout=3s --retries=3 --start-period=60s CMD wget --spider http://localhost:7888/actuator/health || exit 1