package com.itcast.dw.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.itcast.dw.config.JedisUtils;
import com.itcast.dw.config.ProjectConfig;
import com.itcast.dw.constants.RedisKey;
import com.itcast.dw.info.ResultInfo;
import com.itcast.dw.model.User;
import com.itcast.dw.service.UserService;
import com.itcast.dw.util.IPUtils;
import com.itcast.dw.util.JWTTokenUtils;
import com.itcast.dw.util.JsonFormater;


/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法 attemptAuthentication
 * ：接收并解析用户凭证。 successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 * 
 * @author ldw
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

	Logger logger = LoggerFactory.getLogger(getClass());

	private AuthenticationManager authenticationManager;
	private JedisUtils jedisUtils;
	private UserService userService;
	private ProjectConfig projectConfig;
	
	public JWTLoginFilter(AuthenticationManager authenticationManager, JedisUtils jedisUtils, UserService userService,ProjectConfig projectConfig) {
		this.authenticationManager = authenticationManager;
		this.jedisUtils = jedisUtils;
		this.userService = userService;
		this.projectConfig = projectConfig;
	}
	
	/**
	 * 接收并解析用户凭证(non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#attemptAuthentication(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setDateHeader("Expires", 0); // Proxies.
		response.setHeader("Access-Control-Expose-Headers", "Authorization");
		response.setHeader("Access-Control-Allow-Headers", "Authorization,Content-Type");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("X-Frame-Options", "SAMEORIGIN");// 解决IFrame拒绝的问题(同源)
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>进入JWTLoginFilter-attemptAuthentication,getMethod=" + request.getMethod());
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		// TODO 登录前置处理 如登入次数判断等等
		
		//验证码判断
		String kaptchaToken = request.getHeader(JWTTokenUtils.AUTHORIZATION);
		String kaptcha = request.getParameter("kaptcha");
		if(kaptchaToken == null || kaptcha == null){
			JsonFormater.writeJsonValue(response, new ResultInfo<>(ResultInfo.INVALID_PARAM, ResultInfo.KAPTCHA_IS_NULL));
			return null;
		}
		String saveKaptcha = jedisUtils.get(RedisKey.KAPTCHA + kaptchaToken,RedisKey.indexDB);
		if(saveKaptcha == null){
			logger.info("saveKaptcha == " + saveKaptcha);
			JsonFormater.writeJsonValue(response, new ResultInfo<>(ResultInfo.INVALID_PARAM, ResultInfo.KAPTCHA_EXPRRIE));
			return null;
		}
		if(!saveKaptcha.equalsIgnoreCase(kaptcha)){
			logger.info("saveKaptcha  equalsIgnoreCase == " + saveKaptcha);
			JsonFormater.writeJsonValue(response, new ResultInfo<>(ResultInfo.INVALID_PARAM, ResultInfo.KAPTCHA_NOT_REAL));
			return null;
		}
		//登入错误次数判断
		String failedNumber = jedisUtils.get(RedisKey.LOGIN_FAILED_NUMBER + username,RedisKey.indexDB);
		
		if(failedNumber != null){
			Long failedNumbers = jedisUtils.incr(RedisKey.LOGIN_FAILED_NUMBER + username);
			if(failedNumbers > projectConfig.getLoginFailedNumber() ){
				JsonFormater.writeJsonValue(response, new ResultInfo<>(ResultInfo.INVALID_PARAM, ResultInfo.MSG_LOGIN_FAILED));
				return null;
			}
		}
		
		// ...
		logger.info("loginUser==" + username + "=========开始登录======================");
		return authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));
	}

	/**
	 * 用户成功登录后，这个方法会被调用，我们在这个方法里生成token(non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#successfulAuthentication(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain,
	 *      org.springframework.security.core.Authentication)
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>进入JWTLoginFilter-successfulAuthentication");
		JWTUser jwtUser = (com.itcast.dw.jwt.JWTUser) auth.getPrincipal();

		//再修改
		User userByName = userService.getUserByName(jwtUser.getUsername());
		Map<String, String> redisUserMap = new HashMap<String,String>();
		redisUserMap.put("userId", userByName.getId() + "");
		redisUserMap.put("userName", userByName.getUsername());
		
		String userId = userByName.getId() + "";
		String token = JWTTokenUtils.generateToken(userId, projectConfig.getJwtTtl(), projectConfig.getJwtSecurt());
		redisUserMap.put("token", token);
		redisUserMap.put("loginIp", IPUtils.getIpAddr(req));
		redisUserMap.put("loginTime", System.currentTimeMillis()+"");
		logger.info("==redisUserMap=" + redisUserMap);
		jedisUtils.hmset(RedisKey.ADMIN_JWT_TOKEN + userId, redisUserMap, projectConfig.getTokenTtl());
		res.addHeader(JWTTokenUtils.AUTHORIZATION, JWTTokenUtils.tokenToAuthorization(token));
		redisUserMap.remove("token");
		
		//删除验证码
		String kaptchaToken = req.getHeader(JWTTokenUtils.AUTHORIZATION);
		jedisUtils.del(RedisKey.KAPTCHA + kaptchaToken);
				
		JsonFormater.writeJsonValue(res, new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, redisUserMap));
		logger.info("==jwtUser.getUsername()=" + jwtUser.getUsername() + "=========登录成功===");
		
		return;
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		logger.info("===========验证失败=============");
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		//在redis中存储用户登入失败次数
		String username = obtainUsername(request);
		String failedNumber = jedisUtils.get(RedisKey.LOGIN_FAILED_NUMBER + username,RedisKey.indexDB);
		try {
			if(failedNumber != null){
				jedisUtils.incr(RedisKey.LOGIN_FAILED_NUMBER + username);
			}else{
				jedisUtils.setex(RedisKey.LOGIN_FAILED_NUMBER + username, 1 + "", projectConfig.getLoginFailedNumberTtl());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		JsonFormater.writeJsonValue(response, new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_ERROR_LOGIN));
		// 调回上一次页面 super.unsuccessfulAuthentication(request, response, failed);
	}

}