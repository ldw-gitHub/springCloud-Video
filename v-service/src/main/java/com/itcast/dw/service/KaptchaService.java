/**
 * 
 * @date 2019年4月22日
 */
package com.itcast.dw.service;

/**
 * 验证码功能
 * @author liudawei
 */

public interface KaptchaService {
	
	public String createToken(String kaptcha);
	
	void deleteByToken(String kaptchaToken);

}
