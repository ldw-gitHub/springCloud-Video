package com.itcast.dw.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itcast.dw.dao.SessionMapper;
import com.itcast.dw.model.UserSession;

@RestController
public class SessionController {

	@Autowired
	private SessionMapper sessionMapper;
	
	@GetMapping("/getOnlineSessionByToken/{sessiontoken}")
	public UserSession getOnlineSessionByToken(@PathVariable String sessiontoken) {
		return sessionMapper.getOnlineSessionByToken(sessiontoken);
	}
	
	@PostMapping(value = "/saveUserSession")
	public int saveUserSession(@RequestBody UserSession us) {
		return sessionMapper.saveUserSession(us);
	}
	
	@PostMapping(value = "/updateSession")
	public int updateSession(@RequestBody UserSession us) {
		return sessionMapper.updateSession(us);
	}
	
	
	
	
	
	
}
