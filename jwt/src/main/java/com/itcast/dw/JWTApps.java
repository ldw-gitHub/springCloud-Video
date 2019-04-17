/**
 * 
 * @date 2019年4月16日
 */
package com.itcast.dw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * security用户校验
 * @author liudawei
 */
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.itcast.dw.dao")
@SpringBootApplication
public class JWTApps {

	public static void main(String[] args) {
		SpringApplication.run(JWTApps.class, args);
	}
}
