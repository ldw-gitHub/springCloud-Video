FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD videos.jar videosapp.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/videosapp.jar"]