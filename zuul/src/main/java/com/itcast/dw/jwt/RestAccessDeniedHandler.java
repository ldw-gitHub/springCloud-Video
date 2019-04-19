package com.itcast.dw.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.itcast.dw.info.ResultInfo;
import com.itcast.dw.util.JsonFormater;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定403返回值
 * 在自定义拦截器里做了处理,此处不执行
 * @author sky_luan Created on 2017/12/9 20:10.
 */
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		logger.info("/--------403-------------/-");
		response.setStatus(HttpStatus.OK.value());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JsonFormater.writeJsonValue(response, new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FORBIDOM));
	}

}