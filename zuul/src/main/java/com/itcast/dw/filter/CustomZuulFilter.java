package com.itcast.dw.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class CustomZuulFilter extends ZuulFilter{
	
	private Logger logger = Logger.getLogger(CustomZuulFilter.class);

	@Override
	public Object run() throws ZuulException {
	     RequestContext ctx = RequestContext.getCurrentContext();
	        HttpServletRequest request = ctx.getRequest();
	        logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
	        String sessionId = request.getParameter("sessionId");
	        logger.info("sessionId:"+sessionId);
	        if (!sessionId.equals("success_token")){
	            //认证失败
	            logger.error("token验证失败");
	            HttpServletResponse response = ctx.getResponse();
	            response.setCharacterEncoding("utf-8");  //设置字符集
	            response.setContentType("text/html; charset=utf-8"); //设置相应格式
	            response.setStatus(401);
	            ctx.setSendZuulResponse(false); //不进行路由
	            try {
	                response.getWriter().write("token 验证失败"); //响应体
	            } catch (IOException e) {
	            	logger.error("response io异常");
	                e.printStackTrace();
	            }
	            ctx.setResponse(response);
	            return null;
	        }
	        logger.info("token验证成功");
	        return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
