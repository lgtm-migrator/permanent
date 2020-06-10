FROM centos:7
RUN yum install -y git vim wget curl java-1.8.0-openjdk.x86_64 maven
RUN git clone https://github.com/qianxunclub/permanent.git
RUN cd permanent && mvn clean package -Dmaven.test.skip=true
RUN cp permanent/target/permanent-core-0.0.1-SNAPSHOT.jar app.jar
VOLUME /tmp
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]