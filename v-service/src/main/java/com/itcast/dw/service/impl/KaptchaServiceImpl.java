/**
 * 
 * @date 2019年4月22日
 */
package com.itcast.dw.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcast.dw.config.JedisUtils;
import com.itcast.dw.config.ProjectConfig;
import com.itcast.dw.constants.RedisKey;
import com.itcast.dw.service.KaptchaService;

/**
 * 验证码功能
 * @author liudawei
 */
@Service
public class KaptchaServiceImpl implements KaptchaService {
	
	@Autowired
	JedisUtils jedisUtils;
	@Autowired
	ProjectConfig projectConfig;

	@Override
	public String createToken(String kaptcha) {
		//生成一个token
		String token = UUID.randomUUID().toString();
		jedisUtils.setex(RedisKey.KAPTCHA + token, projectConfig.getKaptchaTokenTtl(), kaptcha,RedisKey.indexDB);
		return token;
	}

	@Override
	public void deleteByToken(String kaptchaToken) {
		jedisUtils.del(kaptchaToken);
	}

}
