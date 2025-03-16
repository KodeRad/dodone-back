FROM openjdk:21
ARG JAR_FILE=target/dodone-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} dodone-back.jar
ENTRYPOINT ["java","-jar","/dodone-back.jar"]
EXPOSE 8080