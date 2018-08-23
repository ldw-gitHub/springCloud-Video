package com.itcast.dw.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.itcast.dw.model.UserSession;
import com.itcast.dw.service.SessionService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class CustomZuulFilter extends ZuulFilter {

	private Logger logger = Logger.getLogger(CustomZuulFilter.class);
	
	@Autowired
	private SessionService sessionservice;

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		String sessiontoken = request.getParameter("sessiontoken");
		logger.info("sessiontoken:" + sessiontoken);

		UserSession us = sessionservice.getOnlineSessionByToken(sessiontoken);
		if (us != null) {
			logger.info("token验证成功"); 
			return null;
		} else {
			// 认证失败
			logger.error("token验证失败");
			HttpServletResponse response = ctx.getResponse();
			response.setCharacterEncoding("utf-8"); // 设置字符集
			response.setContentType("text/html; charset=utf-8"); // 设置相应格式
			response.setStatus(401);
			ctx.setSendZuulResponse(false); // 不进行路由
			try {
				response.getWriter().write("token 验证失败"); // 响应体
			} catch (IOException e) {
				logger.error("response io异常");
				e.printStackTrace();
			}
			ctx.setResponse(response);
			return null;
		}

	}

	@Override
	public boolean shouldFilter() {
		return true;// 是否执行该过滤器，此处为true，说明需要过滤
	}

	@Override
	public int filterOrder() {
		return 1;// 优先级为0，数字越大，优先级越低
	}

	@Override
	public String filterType() {
		return FilterConstants.ROUTE_TYPE;// pre前置过滤器、route转发过滤器、post请求返回过滤器
	}

}
