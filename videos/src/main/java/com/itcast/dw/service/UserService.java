package com.itcast.dw.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.itcast.dw.model.User;

@FeignClient(value = "service-db")
public interface UserService {
	
	@GetMapping("/getUserByName/{name}")
	User getUserByName(@PathVariable (value = "name") String name);

}

