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
	private Long tokenTtl;
	/**
	 * JWT秘钥
	 */
	private String jwtSecurt;
	/**
	 * JWT有效期
	 */
	private Long jwtTtl;
	/**
	 * 验证码有效时间
	 */
	private Long kaptchaTokenTtl;
	/**
	 * 用户登入失败次数，统计时长
	 */
	private Long loginFailedNumberTtl;
	/**
	 * 用户登入失败次数
	 */
	private Long loginFailedNumber;


	public Long getKaptchaTokenTtl() {
		return kaptchaTokenTtl;
	}

	public void setKaptchaTokenTtl(Long kaptchaTokenTtl) {
		this.kaptchaTokenTtl = kaptchaTokenTtl;
	}

	public Long getLoginFailedNumberTtl() {
		return loginFailedNumberTtl;
	}

	public void setLoginFailedNumberTtl(Long loginFailedNumberTtl) {
		this.loginFailedNumberTtl = loginFailedNumberTtl;
	}

	public Long getLoginFailedNumber() {
		return loginFailedNumber;
	}

	public void setLoginFailedNumber(Long loginFailedNumber) {
		this.loginFailedNumber = loginFailedNumber;
	}

	public String getJwtSecurt() {
		return jwtSecurt;
	}

	public void setJwtSecurt(String jwtSecurt) {
		this.jwtSecurt = jwtSecurt;
	}

	public Long getJwtTtl() {
		return jwtTtl;
	}

	public void setJwtTtl(Long jwtTtl) {
		this.jwtTtl = jwtTtl;
	}

	public Long getTokenTtl() {
		return tokenTtl;
	}

	public void setTokenTtl(Long tokenTtl) {
		this.tokenTtl = tokenTtl;
	}


	
}
