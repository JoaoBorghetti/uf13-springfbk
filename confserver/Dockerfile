FROM maven:3-eclipse-temurin-11-alpine AS build

COPY src/ /tmp/src/
COPY pom.xml /tmp/

ENV NAME config-server
ENV TYPE prod

WORKDIR /tmp
# --mount=type=bind,target=/root/.m2,source=/root/.m2,from=smartcommunitylab/config-server:8-alpine-cache 
RUN mvn clean package -DskipTests

#ENTRYPOINT [ "/bin/bash" ]
FROM amazoncorretto:11-alpine AS run

RUN addgroup --system config
RUN adduser -G config -h /home/config -D config

COPY --from=build --chown=config:config /tmp/target/config-server-0.0.1-SNAPSHOT.jar /tmp/config-server-0.0.1-SNAPSHOT.jar
EXPOSE 9999
USER config

ENTRYPOINT [ "java", "-jar", "/tmp/config-server-0.0.1-SNAPSHOT.jar" ]
HEALTHCHECK --interval=30s --timeout=3s --retries=3 --start-period=30s CMD wget --spider http://localhost:9999/actuator/health || exit 1