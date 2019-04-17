package com.itcast.dw.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itcast.dw.dao.UserMapper;
import com.itcast.dw.model.User;
import com.itcast.dw.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userMapper;

	@Override
	public User getUserByName(String name) {
		return userMapper.getUserByName(name);
	}

}
