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
	
	public static final Long EXPRIED_SESSION_TIME = 60*30L;
	
	//===================验证码===========================================================
	/**
	 * 存放验证码信息，设置过期时间
	 */
	public final static String KAPTCHA = BASE + "kaptcha:";
	/**
	 * 登入密码错误次数，统计一天内次数
	 */
	public final static String LOGIN_FAILED_NUMBER = BASE + "login-failed-number:";

}
