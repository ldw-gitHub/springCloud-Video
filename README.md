# 基于springcloud的视频播放系统

zuul:8081

eureka:8761

video:8762

---jwt:8763

---elasticsearch:8764

---config:8766

#  debug level:
    #org.springframework.web: DEBUG
    开启在测试启动时报错
    java.io.FileNotFoundException: class path resource [templates/] cannot be resolved to absolute file path because it does not reside in the file system: jar:file:/D:/mavenPro/org/springframework/cloud/spring-cloud-netflix-eureka-server/2.0.0.RELEASE/spring-cloud-netflix-eureka-server-2.0.0.RELEASE.jar!/templates/
#  关闭eureka 注册

   registerWithEureka: false
    fetchRegistry: false