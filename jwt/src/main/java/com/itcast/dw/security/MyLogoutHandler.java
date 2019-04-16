package com.itcast.dw.security;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import com.framework.config.ProjectConfig;
import com.framework.config.RedisUtils;
import com.framework.constants.ActionType;
import com.framework.constants.FunctionType;
import com.framework.constants.RedisKey;
import com.framework.info.ResultInfo;
import com.framework.model.system.SystemLogModel;
import com.framework.service.system.SystemLogService;
import com.framework.utils.IPUtils;
import com.framework.utils.JWTTokenUtils;
import com.framework.utils.JsonFormater;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 退出登录后的拦截处理
 * 
 * @author sky_luan
 */
@Component("myLogoutHandler")
public class MyLogoutHandler implements LogoutHandler {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	RedisUtils redisUtils;
	@Autowired
	ProjectConfig projectConfig;
	@Autowired
	SystemLogService systemLogService;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub
		logger.info("======退出登录========");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setDateHeader("Expires", 0); // Proxies.
		response.setHeader("Access-Control-Expose-Headers", "Authorization");
		response.setHeader("Access-Control-Allow-Headers", "Authorization,Content-Type");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("X-Frame-Options", "SAMEORIGIN");// 解决IFrame拒绝的问题(同源)
		String principal = null;
		if (null != authentication) {
			principal = (String) authentication.getPrincipal();
		}
		String header = request.getHeader(JWTTokenUtils.AUTHORIZATION);
		if (header == null || !header.startsWith(JWTTokenUtils.BEARER)) {
			JsonFormater.writeJsonValue(response,
					new ResultInfo<>(ResultInfo.INVALID_TOKEN, ResultInfo.MSG_FORBIDOM));
			return;
		}
		String token = JWTTokenUtils.authorizationToToken(header);
		Long userId = Long.parseLong(JWTTokenUtils.getUsernameFromToken(token, projectConfig.getJwtSecurt()));
		logger.info("用户退出userId:" + userId);
		/**
		 * 业务处理 删除Redis的缓存和security的信息
		 */
		Map<String, Object> userMap = null;
		if (null != userId) {
			userMap = redisUtils.hgetAll(RedisKey.ADMIN_JWT_TOKEN + userId);
			redisUtils.remove(RedisKey.ADMIN_JWT_TOKEN + userId);
		}
		if (StringUtils.isNotBlank(principal)) {
			new SecurityContextLogoutHandler().logout(request, response,
					new UsernamePasswordAuthenticationToken(principal, null));
		}
		String account = null != userMap?(null != userMap.get("account")?(String)userMap.get("account"):null):null; 
		String userName = null != userMap?(null != userMap.get("userName")?(String)userMap.get("userName"):null):null; 
		logger.info(account+"======退出登录成功========");
		JsonFormater.writeJsonValue(response, new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS));
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String ip = IPUtils.getIpAddr(request);
		//记录登出记录
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					systemLogService.insert(new SystemLogModel(FunctionType.USER_LOGOUT.getValue(),ActionType.LOGOUT.getName(),ip,
							"用户登出",userId,userName));
					System.err.println(account + "====" + userName + "=====" + ip );
//					operateLogService.addLog(new OperateLogModel(userId, userName, OperateLogType.LOGOUT.getValue(), "用户操作", account + "-用户退出系统", ip));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}