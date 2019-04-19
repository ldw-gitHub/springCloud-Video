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
