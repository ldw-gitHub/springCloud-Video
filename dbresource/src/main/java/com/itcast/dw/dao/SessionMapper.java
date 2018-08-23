package com.itcast.dw.dao;

import com.itcast.dw.model.UserSession;

public interface SessionMapper {

	UserSession getOnlineSessionByToken(String sessiontoken);
	
	int saveUserSession(UserSession us);
	
	int updateSession(UserSession us);
}
