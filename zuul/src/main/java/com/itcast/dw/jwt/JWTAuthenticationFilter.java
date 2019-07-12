package com.itcast.dw.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itcast.dw.config.JedisUtils;
import com.itcast.dw.config.ProjectConfig;
import com.itcast.dw.constants.RedisKey;
import com.itcast.dw.info.ResultInfo;
import com.itcast.dw.util.JWTTokenUtils;
import com.itcast.dw.util.JsonFormater;


/**
 * token的校验 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 * 
 * @author ldw
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

	/**
	 * 集成redis,来判断是否存在或者销毁，避免无状态的token非法请求
	 */
	JedisUtils jedisUtils;
	ProjectConfig projectConfig;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JedisUtils jedisUtils,ProjectConfig projectConfig) {
		super(authenticationManager);
		this.jedisUtils = jedisUtils;
		this.projectConfig = projectConfig;
	}


	/**
	 * 授权拦截
	 * 
	 * @see org.springframework.security.web.authentication.www.BasicAuthenticationFilter#doFilterInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("进入JWTAuthenticationFilter-doFilterInternal=====uri="+request.getRequestURI());
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
																					// 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setDateHeader("Expires", 0); // Proxies.
		response.setHeader("Access-Control-Expose-Headers", "Authorization");
		response.setHeader("Access-Control-Allow-Headers", "Authorization");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("X-Frame-Options", "SAMEORIGIN");// 解决IFrame拒绝的问题
		
		//解决跨域预请求的问题
		if(RequestMethod.OPTIONS.name().equals(request.getMethod())){
			logger.info(request.getRequestURI()+"===预请求放过 ====");
			return;
		}
		String header = request.getHeader(JWTTokenUtils.AUTHORIZATION);
		if (header == null || !header.startsWith(JWTTokenUtils.BEARER)) {
			logger.error(request.getRequestURI()+"===header=" + header);
			JsonFormater.writeJsonValue(response, new ResultInfo<>(ResultInfo.INVALID_TOKEN, ResultInfo.MSG_ADMIN_INVALID_TOKEN));
			return;
		}
		/**
		 * 检查redis缓存中是否存在
		 */
		String token = JWTTokenUtils.authorizationToToken(header);
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>token==" + token);
		String username = JWTTokenUtils.getUsernameFromToken(token, projectConfig.getJwtSecurt());
		if (StringUtils.isBlank(username) || !jedisUtils.exists(RedisKey.ADMIN_JWT_TOKEN + username)) {
			logger.error(request.getRequestURI()+"===username=" + username);
			JsonFormater.writeJsonValue(response, new ResultInfo<>(ResultInfo.INVALID_TOKEN, ResultInfo.MSG_ADMIN_INVALID_TOKEN));
			return;
		}
		String redisToken = jedisUtils.hget(RedisKey.ADMIN_JWT_TOKEN + username, "token");
		if (!token.equals(redisToken)) {
			logger.error(request.getRequestURI()+"===token不相等=");
			JsonFormater.writeJsonValue(response, new ResultInfo<>(ResultInfo.INVALID_TOKEN, ResultInfo.MSG_ADMIN_INVALID_TOKEN));
			return;
		}
		if (JWTTokenUtils.isTokenExpired(token, projectConfig.getJwtSecurt())) {
			logger.error(request.getRequestURI()+"===token过期=");
			JsonFormater.writeJsonValue(response, new ResultInfo<>(ResultInfo.INVALID_TOKEN, ResultInfo.MSG_ADMIN_INVALID_TOKEN));
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		logger.info(">>>>>>>>>>>>>>>>>>>>>>authentication = " + authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	/**
	 * 权限验证
	 * 
	 * @param request
	 * @return
	 * @date 2018年5月30日
	 * @author ldw
	 */
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String Authorization = request.getHeader(JWTTokenUtils.AUTHORIZATION);
		if (StringUtils.isNotBlank(Authorization) && Authorization.startsWith(JWTTokenUtils.BEARER)) {
			// parse the token.
			String username = JWTTokenUtils.getUsernameFromToken(JWTTokenUtils.authorizationToToken(Authorization), projectConfig.getJwtSecurt());
			if (StringUtils.isNotBlank(username)) {
				List<String> list = new ArrayList<>();
				return new UsernamePasswordAuthenticationToken(username, null,
						list.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
			}
			return null;
		}
		return null;
	}

}