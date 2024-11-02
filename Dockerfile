FROM openjdk:latest
COPY ./target/sem.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem.jar"]