package com.itcast.dw.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.itcast.dw.service.RedisService;
import com.itcast.dw.util.IgnoreUrl;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class CustomZuulFilter extends ZuulFilter {

	private Logger logger = Logger.getLogger(CustomZuulFilter.class);
	
	@Autowired
	private RedisService redisService;
	
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		 String url = request.getRequestURL().toString();
		 url = url.substring(url.lastIndexOf("/") + 1,url.length());
		 
		 if((boolean) IgnoreUrl.filterUrl.get(url)){//判断请求是否需要验证登入
				String sessionToken = request.getParameter("sessionToken");
				int userId = Integer.parseInt(request.getParameter("userId"));

				Map<String,Object> parameterMap = new HashMap<String,Object>();
				parameterMap.put("sessionToken", sessionToken);
				parameterMap.put("userId", userId);
				JSONObject redisResponse = redisService.judgeTokenId(parameterMap);
				
				HttpServletResponse response = ctx.getResponse();
				response.setCharacterEncoding("utf-8"); // 设置字符集
				response.setContentType("text/html; charset=utf-8"); // 设置相应格式
				if (redisResponse.getBoolean("success")) {
					logger.info("token验证成功"); 
					response.setStatus(200);
					ctx.setSendZuulResponse(true); //路由
					ctx.set("isSuccess", true);//其他filter可以看到状态
					return null;
				} else {
					// 认证失败
					logger.error("token验证失败");
					response.setStatus(200);
					ctx.setSendZuulResponse(false); // 不进行路由
					ctx.set("isSuccess", false);
					ctx.setResponseBody("{\"success\":false,\"msg\":\"0002\"}");
					ctx.setResponse(response);
					return null;
				}
		 }else{
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
		return FilterConstants.PRE_TYPE;// pre前置过滤器、route转发过滤器、post请求返回过滤器
	}

}
