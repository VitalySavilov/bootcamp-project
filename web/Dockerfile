FROM openjdk:17-jdk-slim

WORKDIR /home/app

EXPOSE 8080

COPY target/web-0.0.1-SNAPSHOT.jar /home/app/web-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","web-0.0.1-SNAPSHOT.jar"]