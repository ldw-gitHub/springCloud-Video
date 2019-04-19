package com.itcast.dw.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.itcast.dw.common.CommonUtil;
import com.itcast.dw.config.RedisUtils;
import com.itcast.dw.info.ResultInfo;
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
	
	@Autowired
	private RedisUtils redisUtils;
	
	@PostMapping("/logout")
	public ResultInfo<?> logout(HttpServletRequest request) {
		JSONObject obj = new JSONObject();
		String sessionToken  = request.getParameter("sessionToken");
		UserSession us = sessionService.getOnlineSessionByToken(sessionToken);
		
		if(us == null) {
			return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
		}
		
		us.setIsonline(0);
		us.setUpdatetime(new Date());
		
		sessionService.updateSession(us);
		//删除redis sessionToken
		redisUtils.remove(sessionToken);
		return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
	}
	
	@PostMapping("/login")
	public ResultInfo<?> login(HttpServletRequest request) {
		
		JSONObject obj = new JSONObject();
		
		//判断用户名密码
		String name  = request.getParameter("username");
		String password  = request.getParameter("password");
		int remenberme  = Integer.parseInt(request.getParameter("remenberme"));
		
		User user = userService.getUserByName(name);
		
		if(user == null){
			return new ResultInfo<>(ResultInfo.FAILURE, "用户不存在");
			
		}else{
			if(!(password.equals(user.getPassword()))){
				return new ResultInfo<>(ResultInfo.FAILURE, "密码错误");
			}
			
			String sessionToken = CommonUtil.getSessionKey();
			
			//redis存入登入信息
			redisUtils.set(sessionToken, user.getId()+"", 1800L);
			
			//将用户信息存入session
			UserSession us = new UserSession();
			us.setCreatetime(new Date());
			us.setIsonline(1);
			us.setRemenberme(remenberme);
			us.setSessiontoken(sessionToken);
			us.setUpdatetime(new Date());
			us.setUserid(user.getId());
			
			sessionService.saveUserSession(us);
			
			user.setPassword("");
			log.info("login success" + user.getUsername());
			obj.put("user", user);
			obj.put("accessToken", sessionToken);
			
		}
		
		return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS,obj);
	}
	


}
