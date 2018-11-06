FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD eureka.jar eurekaapp.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eurekaapp.jar"]