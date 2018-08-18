package com.itcast.dw.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.itcast.dw.dao.UserMapper;
import com.itcast.dw.model.User;

@RestController
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/getUserByName/{name}")
	public User getUserByName(@PathVariable String name) {
		return userMapper.getUserByName(name);
	}
}
