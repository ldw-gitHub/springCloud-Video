package com.itcast.dw.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

@FeignClient(value = "service-redis")
public interface RedisService {
	
	@PostMapping( value = "/judgeTokenId")
	JSONObject judgeTokenId(@RequestParam Map<String,Object> parameterMap);
	
}
