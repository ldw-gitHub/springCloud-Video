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

	/**
	 * 前端用户token信息 so:token-api:${idCard}
	 */
	public static final String API_JWT_TOKEN = BASE + "token-api:";

	// ===================缓存========================================================
	/**
	 * 缓存业务大类 so:book-type:${bookTypeId}
	 */
	public final static String WEB_BOOK_TYPE = BASE + "book-type:";
	/**
	 * 缓存事项 so:book-item:${szItemNo}
	 */
	public final static String WEB_BOOK_ITEM = BASE + "book-item:";
	/**
	 * 缓存首页的目录树
	 */
	public final static String WEB_MENU_ITEM = BASE + "menu-item";
	/**
	 * 预约规则-系统配置
	 */
	public final static String WEB_BOOK_RULE = BASE + "config:book";
	
	/**
	 * 登记所 so:registration:${id}
	 */
	public final static String WEB_REGISTRATION = BASE + "registration:";
	/**
	 * 区域 area:${id}
	 */
	public final static String WEB_AREA = BASE + "area:";
	/**
	 * 区域列表 area:list
	 */
	public final static String WEB_AREA_LIST = WEB_AREA + "list";
	/**
	 * 时间段 work-time:${id}
	 */
	public final static String WEB_WORK_TIME = BASE + "work-time:";
	// ===================业务========================================================
	
	/**
	 * 判断预约提交是否重复 + MD5(提交信息)
	 */
	public final static String BOOK_INFO_REPEAT = BASE + "book-info:";
	
	/**
	 * 生成预约流水号 so:book-code:yyyyMMdd
	 */
	public final static String BOOK_CODE = BASE + "book-code:";
	/**
	 * 失约统计时段
	 */
	public final static String BOOK_RELEASE = BASE + "book-release:";
	// ===================叫号系统========================================================
	/**
	 * 叫号系统的access_token   so:call:appId
	 */
	public final static String CALL_SYSTEM = BASE + "call:";
}
