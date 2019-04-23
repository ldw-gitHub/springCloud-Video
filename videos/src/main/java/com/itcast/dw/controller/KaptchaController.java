/**
 * 
 * @date 2019年4月22日
 */
package com.itcast.dw.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.itcast.dw.info.ResultInfo;
import com.itcast.dw.service.KaptchaService;
import com.itcast.dw.util.JWTTokenUtils;

import sun.misc.BASE64Encoder;

/**
 * 一句话功能简述
 * @author liudawei
 */
@RestController
public class KaptchaController {
	
	@Autowired
	KaptchaService KaptchaService;
	
	 /**
     * 验证码工具
     */
    @Autowired
    DefaultKaptcha defaultKaptcha;
    
	@PostMapping(value = "/kaptcha")
	public ResultInfo<?> kaptcha(HttpServletResponse response) throws ServletException, IOException {

		// 生成文字验证码
		String text = defaultKaptcha.createText();
		// 生成图片验证码
		ByteArrayOutputStream outputStream = null; 
		BufferedImage image = defaultKaptcha.createImage(text);
		
		outputStream = new ByteArrayOutputStream();  
		ImageIO.write(image, "jpg", outputStream);  
	    
		// 对字节数组Base64编码  
		BASE64Encoder encoder = new BASE64Encoder();  
	    
		// 生成captcha的token
		Map<String,Object> rtMap = new HashMap<String,Object>();
		String kaptchaToken = KaptchaService.createToken(text);
		String path = encoder.encode(outputStream.toByteArray());
		path = "data:image/jpeg;base64," + path.replaceAll("\r\n", "");
		rtMap.put("img", path);
		response.addHeader(JWTTokenUtils.AUTHORIZATION, kaptchaToken);
		response.setDateHeader("Expires", 0); // Proxies.
		response.setHeader("Access-Control-Expose-Headers", "Authorization");
		response.setHeader("Access-Control-Allow-Headers", "Authorization,Content-Type");
		response.setHeader("Access-Control-Allow-Methods", "*");

		return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS,rtMap);
	}

}
