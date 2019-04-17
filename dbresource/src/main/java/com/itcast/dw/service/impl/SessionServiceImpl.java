package com.itcast.dw.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itcast.dw.dao.SessionMapper;
import com.itcast.dw.model.UserSession;
import com.itcast.dw.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService {
	
	@Resource
	private SessionMapper sessionMapper;

	@Override
	public int saveUserSession(UserSession us) {
		return sessionMapper.saveUserSession(us);
	}

	@Override
	public UserSession getOnlineSessionByToken(String sessiontoken) {
		return sessionMapper.getOnlineSessionByToken(sessiontoken);
	}

	@Override
	public int updateSession(UserSession us) {
		return sessionMapper.updateSession(us);
	}

}
