package com.itcast.dw.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-redis")
public interface RedisService {
	
	@PostMapping( value = "/loginAddRedisKey")
	void loginAddRedisKey(@RequestParam Map<String,Object> parameterMap);
	
	@PostMapping( value = "/logoutDeleteRedisKey/{sessionToken}")
	void logoutDeleteRedisKey(@PathVariable("sessionToken") String sessionToken);

}
