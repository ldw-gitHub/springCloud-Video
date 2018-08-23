package com.itcast.dw.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.itcast.dw.model.UserSession;

@FeignClient(value = "service-db")
public interface SessionService {

	@PostMapping(value = "/saveUserSession")
    int saveUserSession(@RequestBody UserSession us);
	
	@GetMapping(value = "/getOnlineSessionByToken/{sessiontoken}")
	UserSession getOnlineSessionByToken(@PathVariable("sessiontoken") String sessiontoken);
	
	@PostMapping(value = "/updateSession")
	int updateSession(@RequestBody UserSession us);
	
	
}
