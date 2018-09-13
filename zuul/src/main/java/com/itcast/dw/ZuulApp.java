package com.itcast.dw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@EnableZuulProxy
@EnableFeignClients
@ComponentScan("com.itcast.dw")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ZuulApp {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApp.class, args);
	}
	
}
