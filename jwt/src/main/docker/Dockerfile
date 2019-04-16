FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD jwt.jar jwtapp.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jwtapp.jar","--spring.profiles.active=docker"]
EXPOSE 8081