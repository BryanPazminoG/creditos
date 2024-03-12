FROM openjdk:21

WORKDIR /app

COPY target/creditos-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "creditos-0.0.1-SNAPSHOT.jar"]

