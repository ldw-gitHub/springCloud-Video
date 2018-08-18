package com.itcast.dw.dao;

import com.itcast.dw.model.User;

public interface UserMapper {
	User getUserByName(String name);

}
