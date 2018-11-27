FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD redis.jar redisapp.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/redisapp.jar","--spring.profiles.active=docker"]
EXPOSE 8765