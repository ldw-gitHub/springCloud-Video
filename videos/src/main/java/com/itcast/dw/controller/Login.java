package com.itcast.dw.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.itcast.dw.common.CommonUtil;
import com.itcast.dw.model.User;
import com.itcast.dw.service.UserService;

@RestController
public class Login {
	
	private static Logger log = LoggerFactory.getLogger(Login.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response) {
		
		JSONObject obj = new JSONObject();
		
		//判断用户名密码
		String name  = request.getParameter("username");
		String password  = request.getParameter("password");
		
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
		/*	HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			Cookie cc = new Cookie("sessionId", CommonUtil.getSessionKey());
			cc.setMaxAge(60*60*24);
			cc.setHttpOnly(false);
			
			response.addCookie(cc);*/
		
			
			log.info("login success" + user.getUsername());
			obj.put("msg", "0001");
			obj.put("user", user);
			obj.put("accessToken", CommonUtil.getSessionKey());
			
		}
		
		return obj.toJSONString();
	}
	


}
