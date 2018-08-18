package com.itcast.dw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@EnableFeignClients
@ComponentScan("com.itcast.dw")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class VideosApp {
	public static void main(String[] args) {
		SpringApplication.run(VideosApp.class, args);	
	}
}
