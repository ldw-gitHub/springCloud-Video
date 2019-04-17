package com.itcast.dw.constants;

/**
 * redis键
 * 
 * @author sky_luan
 */
public class RedisKey {

	/**
	 * 父键名:szbdc-onlinebook简写
	 */
	private static final String BASE = "so:";
	// ==================登录信息=========================================================

	/**
	 * 后台用户token信息 so:token-admin:${userId}
	 */
	public static final String ADMIN_JWT_TOKEN = BASE + "token-admin:";
	
	public static final Long expriedSessionTime = 60*30L;

}
