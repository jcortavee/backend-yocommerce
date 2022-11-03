# BUILDER IMAGE
FROM openjdk:17-jdk-alpine as builder

ARG SERVICE_NAME=products-management

WORKDIR /app/$SERVICE_NAME

COPY pom.xml /app
COPY ./$SERVICE_NAME/.mvn .
COPY ./$SERVICE_NAME/mvwn .
COPY ./$SERVICE_NAME/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target

COPY ./$SERVICE_NAME/src .

RUN ./mvnw clean package -DskipTests


# FINAL IMAGE
FROM openjdk:17-jdk-alpine

ARG SERVICE_NAME
ARG SERVICE_VERSION=0.0.1

ENV PORT 9000
EXPOSE $PORT

WORKDIR /app
RUN mkdir "./logs"

COPY --from=builder /app/$SERVICE_NAME/target/$SERVICE_NAME-$SERVICE_VERSION.jar .

ENTRYPOINT ["java", "-jar", "./$SERVICE_NAME-$SERVICE_VERSION.jar"]