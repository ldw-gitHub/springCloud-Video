package com.itcast.dw.dao;

import org.apache.ibatis.annotations.Param;

import com.itcast.dw.model.User;

public interface UserMapper {
	
	User getUserByName(@Param("name") String name);

}
