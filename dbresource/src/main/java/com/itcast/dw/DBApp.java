package com.itcast.dw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.itcast.dw")
public class DBApp {
	public static void main(String[] args) {
		SpringApplication.run(DBApp.class, args);	
	}
}
