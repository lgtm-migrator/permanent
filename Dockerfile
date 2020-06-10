FROM openjdk:8-jdk-alpine
ADD target/permanent-core-0.0.1-SNAPSHOT.jar app.jar
VOLUME /tmp
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]