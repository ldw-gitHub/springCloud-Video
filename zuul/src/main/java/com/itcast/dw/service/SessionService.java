package com.itcast.dw.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.itcast.dw.model.UserSession;

@FeignClient(value = "service-db")
public interface SessionService {

	@GetMapping("/getOnlineSessionByToken/{sessiontoken}")
	UserSession getOnlineSessionByToken(@PathVariable (value = "sessiontoken") String sessiontoken);
}
