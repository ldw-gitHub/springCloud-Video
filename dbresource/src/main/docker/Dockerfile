FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD dbresource.jar dbresourceapp.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/dbresourceapp.jar","--spring.profiles.active=docker"]
EXPOSE 8763