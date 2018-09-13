package com.itcast.dw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableEurekaClient
@EnableRedisRepositories
@ComponentScan("com.itcast.dw")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RedisApp {
	
	public static void main(String[] args) {
		SpringApplication.run(RedisApp.class, args);
	}

}
