package com.itcast.dw.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 *
 */
public class JWTTokenUtils implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 请求信息头部
	 */
	public static final String AUTHORIZATION = "Authorization";
	
	/**
	 * Authorization头部
	 */
	public static final String BEARER = "Bearer ";
	
    /**
     * 从头部Authorization中提取token
     * @param Authorization
     * @return token
     * @date 2018年5月31日
     * @author sky_luan
     */
	public static String authorizationToToken(String Authorization){
		return Authorization.replace(BEARER, "");
	}
	
	/**
	 * 从token到
	 * @param token
	 * @return 
	 * @date 2018年5月31日
	 * @author sky_luan
	 */
	public static String tokenToAuthorization(String token){
		return BEARER + token;
	}
	
    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @param ttl 令牌有效期
     * @param secret 令牌秘钥
     * @return 令牌
     */
    private static String generateToken(Map<String, Object> claims, Long ttl, String secret) {
    	Date expirationDate = new Date(System.currentTimeMillis() + ttl * 1000);
    	return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

	/**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @param secret 秘钥
     * @return 数据声明
     */
    private static Claims getClaimsFromToken(String token, String secret) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成令牌
     *
     * @param username 用户名
     * @return 令牌
     */
    public static String generateToken(String username, Long ttl, String secret) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put("sub", username);
        claims.put("created", new Date());
        return generateToken(claims, ttl, secret);
    }
    
    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @param secret 秘钥
     * @return 用户名
     */
    public static String getUsernameFromToken(String token, String secret) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token, secret);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @param secret 秘钥
     * @return 是否过期
     */
    public static Boolean isTokenExpired(String token, String secret) {
        try {
            Claims claims = getClaimsFromToken(token, secret);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @param ttl 有效期
     * @param secret 秘钥
     * @return 新令牌
     */
    public static String refreshToken(String token, Long ttl, String secret) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token, secret);
            claims.put("created", new Date());
            refreshedToken = generateToken(claims, ttl, secret);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

}