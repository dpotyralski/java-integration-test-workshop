FROM amazoncorretto:17

ADD build/libs/java-integration-test-introduction-0.0.1-SNAPSHOT.jar ./SimpleWebApp/service.jar
ENTRYPOINT java -jar ./SimpleWebApp/service.jar

EXPOSE 8080