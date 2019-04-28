package com.itcast.dw.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.itcast.dw.info.BusinessException;
import com.itcast.dw.info.ResultInfo;
import com.itcast.dw.util.JsonFormater;


/**
 * 
 * 自定义异常拦击统一处理
 * 
 * @author sky_luan
 */
@RestControllerAdvice
public class ExceptionCustomHandler {

	Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(value = Exception.class)
	public void defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		// 打印异常信息：
		// logger.error("!!!!!!!!请求出现异常!!!!!!!!!!!!!!：" + request.getRequestURI(), ex);
		logger.error("!!!!!!!!请求出现异常!!!!!!!!!!!!!!：" + request.getRequestURI() + ex.getMessage());
		logger.error("!!!!!!!!请求出现异常!!!!!!!!!!!!!!：" + request.getRequestURI() + ExceptionUtils.getMessage(ex));
		if (ex != null) {
			Integer code = ResultInfo.FAILURE;
			String msg = "";
			if (ex instanceof BusinessException) {
				code = ((BusinessException) ex).getCode();
				msg = ex.getMessage();
			}  else if (ex instanceof NullPointerException) {
				msg = "数据异常";
			} else if (ex instanceof IOException) {
				msg = "文件读写异常";
			}else {
				msg = "Internal Server Error";
			}
			JsonFormater.writeJsonValue(response, new ResultInfo<>(code, msg));
		}
	}

}