package com.itcast.dw.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.itcast.dw.common.CommonUtil;
import com.itcast.dw.model.User;
import com.itcast.dw.model.UserSession;
import com.itcast.dw.service.SessionService;
import com.itcast.dw.service.UserService;

@RestController
public class Login {
	
	private static Logger log = LoggerFactory.getLogger(Login.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	private SessionService sessionService;
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		JSONObject obj = new JSONObject();
		String sessionToken  = request.getParameter("sessionToken");
		UserSession us = sessionService.getOnlineSessionByToken(sessionToken);
		
		if(us == null) {
			obj.put("success", true);
			return obj.toJSONString();
		}
		
		us.setIsonline(0);
		us.setUpdatetime(new Date());
		
		sessionService.updateSession(us);
		
		obj.put("success", true);
		return obj.toJSONString();
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		
		JSONObject obj = new JSONObject();
		
		//判断用户名密码
		String name  = request.getParameter("username");
		String password  = request.getParameter("password");
		int remenberme  = Integer.parseInt(request.getParameter("remenberme"));
		
		User user = userService.getUserByName(name);
		
		if(user == null){
			obj.put("msg", "username error");
			return obj.toJSONString();
			
		}else{
			if(!(password.equals(user.getPassword()))){
				obj.put("msg", "password error");
				return obj.toJSONString();
			}
			
			//将用户信息存入session
			String sessionToken = CommonUtil.getSessionKey();
			
			UserSession us = new UserSession();
			us.setCreatetime(new Date());
			us.setIsonline(1);
			us.setRemenberme(remenberme);
			us.setSessiontoken(sessionToken);
			us.setUpdatetime(new Date());
			us.setUserid(user.getId());
			
			sessionService.saveUserSession(us);
			
			log.info("login success" + user.getUsername());
			obj.put("msg", "0001");
			obj.put("user", user);
			obj.put("accessToken", sessionToken);
			
		}
		
		return obj.toJSONString();
	}
	


}
