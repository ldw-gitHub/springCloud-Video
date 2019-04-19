/*package com.itcast.dw.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class CustomZuulFilter extends ZuulFilter {

	private Logger logger = Logger.getLogger(CustomZuulFilter.class);
	
	@Override
	public Object run() throws ZuulException {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 进入CustomZuulFilter  ");
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		logger.info("token验证成功"); 
		HttpServletResponse response = ctx.getResponse();
		response.setCharacterEncoding("utf-8"); // 设置字符集
		response.setContentType("text/html; charset=utf-8"); // 设置相应格式
		response.setStatus(200);
		ctx.setSendZuulResponse(true); //路由
		ctx.set("isSuccess", true);//其他filter可以看到状态
		return null;
		
	}

	@Override
	public boolean shouldFilter() {
		return true;// 是否执行该过滤器，此处为true，说明需要过滤
	}

	@Override
	public int filterOrder() {
		return 0;// 优先级为0，数字越大，优先级越低
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;// pre前置过滤器、route转发过滤器、post请求返回过滤器
	}

}
*/