/**
 * 
 * @date 2019年4月16日
 */
package com.itcast.dw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * security用户校验
 * @author liudawei
 */
@EnableEurekaClient
@EnableFeignClients
@ComponentScan("com.itcast.dw")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JWTApps {

	public static void main(String[] args) {
		SpringApplication.run(JWTApps.class, args);
	}
}
