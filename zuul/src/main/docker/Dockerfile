FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD zuul.jar zuulapp.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/zuulapp.jar","--spring.profiles.active=docker"]
EXPOSE 8081