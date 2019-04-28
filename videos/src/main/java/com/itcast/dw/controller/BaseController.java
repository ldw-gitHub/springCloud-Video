package com.itcast.dw.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.itcast.dw.config.ProjectConfig;
import com.itcast.dw.config.RedisUtils;
import com.itcast.dw.constants.RedisKey;
import com.itcast.dw.info.BusinessException;
import com.itcast.dw.info.ResultInfo;
import com.itcast.dw.model.AdminUser;
import com.itcast.dw.util.JWTTokenUtils;

/**
 * 基础控制类
 */
public class BaseController {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	final protected String charset = "UTF-8";
	final protected String produces = "application/json;charset=" + charset;

	@Autowired
	protected ProjectConfig projectConfig;
	@Autowired
	protected RedisUtils redisUtils;
	
	/**
	 * 根据JWT Token获取当前登录对象 未找到或者未查询到则会抛出异常
	 * 
	 * @param request
	 * @return SysUserModel
	 * @author sky_luan
	 * @throws Exception
	 */
	protected AdminUser getCurrentUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String header = request.getHeader(JWTTokenUtils.AUTHORIZATION);
		if (StringUtils.isBlank(header)) {
			return null;
		}
		String token = JWTTokenUtils.authorizationToToken(header);
		String userId = JWTTokenUtils.getUsernameFromToken(token, projectConfig.getJwtSecurt());
		// String userId = "-1";
		Map<String, Object> map = redisUtils.hgetAll(RedisKey.ADMIN_JWT_TOKEN + userId);
		if (null == map || map.isEmpty()) {
			throw new BusinessException(ResultInfo.INVALID_TOKEN, ResultInfo.MSG_INVALID_TOKEN);
			// TODO redis缓存中如果不存在则从mysql中查找（测试）
			// map = loginUserService.queryMapByAccount(username);
		}
//		redisUtils.setTTL(RedisKey.ADMIN_JWT_TOKEN + userId, projectConfig.getTokenTtl());
		AdminUser adminUser = new AdminUser();
		adminUser.setUserId(Integer.parseInt((String)map.get("userId")));
		adminUser.setUserName(map.get("userName") != null ? map.get("userName").toString() : null);
		return adminUser;
	}

}
