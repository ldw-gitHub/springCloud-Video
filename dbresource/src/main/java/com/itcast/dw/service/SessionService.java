package com.itcast.dw.service;

import com.itcast.dw.model.UserSession;

public interface SessionService {

    int saveUserSession(UserSession us);
	
	UserSession getOnlineSessionByToken(String sessiontoken);
	
	int updateSession(UserSession us);
	
	
}
