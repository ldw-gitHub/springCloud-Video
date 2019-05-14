package com.itcast.dw.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.itcast.dw.info.BusinessException;
import com.itcast.dw.info.JsonFormater;
import com.itcast.dw.model.ResultInfo;

/**
 * 
 * 自定义异常拦截统一处理
 * @author liudawei
 */
@RestControllerAdvice
public class ExceptionCustomHandler {
	
	Logger logger = Logger.getLogger(ExceptionCustomHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	public void defaultErrorHandler(HttpServletResponse response,HttpServletRequest request,Object handler,
			Exception ex){
		// 打印异常信息：
		logger.error("!!!!!!!!请求出现异常!!!!!!!!!!!!!!：" + request.getRequestURI());
		logger.error("!!!!!!!!请求出现异常!!!!!!!!!!!!!!：" + request.getRequestURI() + "==" + ExceptionUtils.getMessage(ex));
		if (ex != null) {
			Integer code = ResultInfo.FAILURE;
			String msg = "";
			if (ex instanceof BusinessException) {
				code = ((BusinessException) ex).getCode();
				msg = ex.getMessage();
			} else {
				msg = "第三方接口请求失败异常";
			}
			logger.error(code + "==========" + msg);
			JsonFormater.writeJsonValue(response, new ResultInfo<>(code, msg));
		}
	}

}
