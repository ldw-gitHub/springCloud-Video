package com.itcast.dw.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目配置
 */
@Component
@ConfigurationProperties(prefix = "project.config")
public class ProjectConfig {
	/**
	 * Token登录的有效期
	 */
	private int tokenTtl;
	/**
	 * JWT秘钥
	 */
	private String jwtSecurt;
	/**
	 * JWT有效期
	 */
	private int jwtTtl;
	/**
	 * 验证码有效时间
	 */
	private int kaptchaTokenTtl;
	/**
	 * 用户登入失败次数，统计时长
	 */
	private int loginFailedNumberTtl;
	/**
	 * 用户登入失败次数
	 */
	private int loginFailedNumber;


	public int getKaptchaTokenTtl() {
		return kaptchaTokenTtl;
	}

	public void setKaptchaTokenTtl(int kaptchaTokenTtl) {
		this.kaptchaTokenTtl = kaptchaTokenTtl;
	}

	public int getLoginFailedNumberTtl() {
		return loginFailedNumberTtl;
	}

	public void setLoginFailedNumberTtl(int loginFailedNumberTtl) {
		this.loginFailedNumberTtl = loginFailedNumberTtl;
	}

	public int getLoginFailedNumber() {
		return loginFailedNumber;
	}

	public void setLoginFailedNumber(int loginFailedNumber) {
		this.loginFailedNumber = loginFailedNumber;
	}

	public String getJwtSecurt() {
		return jwtSecurt;
	}

	public void setJwtSecurt(String jwtSecurt) {
		this.jwtSecurt = jwtSecurt;
	}

	public int getJwtTtl() {
		return jwtTtl;
	}

	public void setJwtTtl(int jwtTtl) {
		this.jwtTtl = jwtTtl;
	}

	public int getTokenTtl() {
		return tokenTtl;
	}

	public void setTokenTtl(int tokenTtl) {
		this.tokenTtl = tokenTtl;
	}


	
}
