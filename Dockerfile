FROM openjdk:17-jdk-alpine
LABEL authors="laila"

ARG JAR_FILE=target/*.jar

COPY target/IW2BR-0.0.1-SNAPSHOT.jar IW2BR.jar

ENTRYPOINT ["java", "-jar","/IW2BR.jar"]