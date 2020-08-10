FROM openjdk:11

EXPOSE 8081

VOLUME /tmp
ADD ./target/B31_SpringJPAQuizApp-1.0.1.jar myapp.jar
RUN sh -c 'touch /myapp.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar"]
