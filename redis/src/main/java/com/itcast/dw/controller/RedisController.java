package com.itcast.dw.controller;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
public class RedisController {
	
	private Logger log = LoggerFactory.getLogger(RedisController.class);
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Resource(name = "redisTemplate")
	private ValueOperations<String, Object> vOps;
	
	private static long esprieTime = 60*30;
	
	/**
	 * 验证是否登入
	 * @param request
	 * @return
	 */
	@RequestMapping("/judgeTokenId")
	public JSONObject judgeTokenId(@RequestParam Map<String,Object> parameterMap){
		JSONObject response = new JSONObject();
		
		String getsessionToken = parameterMap.get("sessionToken") + "";
		int userId = Integer.parseInt(parameterMap.get("userId") + "");
		
		String tokenIdValue = String.valueOf(vOps.get(getsessionToken));
		if(tokenIdValue == null || "null".equals(tokenIdValue)){//tokenId已过期
			response.put("msg", "0002");
			response.put("success", false);
			return response;
		}
		
		int redisUserId = Integer.parseInt(tokenIdValue);
		
		if(userId == redisUserId){
			//验证成功，重新生成一个tokenId,删除原先的tokenid
			setCacheValueAndTime(getsessionToken,userId,esprieTime);
			response.put("success", true);
		}else{
			response.put("success", false);
			response.put("msg", "0002"); //userId与value不等
		}
		
		return response;
	}
	
	@RequestMapping("/loginAddRedisKey")
	public JSONObject loginAddRedisKey(@RequestParam Map<String,Object> parameterMap){
		JSONObject response = new JSONObject();
		int userId = Integer.parseInt(parameterMap.get("userId")+ "");
		String sessionToken = parameterMap.get("sessionToken") + "";
		setCacheValueAndTime(sessionToken, userId, esprieTime);
		
		response.put("success", true);
		return response;
	}
	
	@RequestMapping("/logoutDeleteRedisKey/{sessionToken}")
	public JSONObject logoutDeleteRedisKey(@PathVariable("sessionToken") String sessionToken){
		JSONObject response = new JSONObject();
		deleteCacheBykey(sessionToken);
		
		response.put("success", true);
		return response;
	}
	
	/**
	 * 设置key
	 * @param key
	 * @param value
	 */
	public void setCacheValue(String key,int value){
		vOps.set(key, value);
	}
	
	/**
	 * 设置key和过期时间
	 * @param key
	 * @param value
	 * @param time
	 */
	public void setCacheValueAndTime(String key,int value,long time){
		vOps.set(key, value, time, TimeUnit.SECONDS);
	}
	
	/**
	 * 删除某个key
	 * @param key
	 */
	public void deleteCacheBykey(String key){
		vOps.getOperations().delete(key);
	}
	
	/**
	 * 获取过期时间
	 * @param key
	 * @return
	 */
	public long getExprieTime(String key){
		return redisTemplate.getExpire(key);
	}
	
	/**
	 * 获取过期时间秒
	 * @param key
	 * @return
	 */
	public long getExprieTimeForSeconds(String key){
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}
	
	public long getExprieTimeForMin(String key){
		return redisTemplate.getExpire(key,TimeUnit.MINUTES);
	}
	
	/**
	 * 设置一个自增的数据
	 * @param key
	 * @param time
	 */
	public void incrementTime(String key,long time){
		vOps.increment(key, time);
	}
	
	
	
	@RequestMapping("/tests")
	public String testes(){
		String key = "111";
		//setCacheValueAndTime(key, "1", esprieTime);
		JSONObject response = new JSONObject();
		response.put("time",getExprieTime(key));
		response.put("minite",getExprieTimeForMin(key));
		response.put("seconds",getExprieTimeForSeconds(key));
		System.out.println(vOps.get(key));
		response.put("key",vOps.get(key));
		return response.toJSONString();
	}
	

}
