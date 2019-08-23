/**
 * 
 * @date 2019年8月5日
 */
package com.itcast.dw.test.redismq;

import java.net.URLEncoder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.itcast.dw.config.JedisUtils;
import com.itcast.dw.constants.RedisKey;

/**
 * redis队列测试
 * @author liudawei
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisMQTest {
	
	@Autowired
	JedisUtils jedisUtil;
	
	public static void main(String[] args) {
		JSONObject j = new JSONObject();
		j.put("code", "1111");
		String encode = URLEncoder.encode(j.toJSONString());
		System.out.println(encode);
	}
	
	//@Test
	public void putMessage(){
		for(int i = 0;i < 10 ;i++){
			jedisUtil.rpush(RedisKey.indexDB, "msg:cesi1", i + "");
		}
		for(int i = 0;i < 10 ;i++){
			jedisUtil.rpush(RedisKey.indexDB, "msg:cesi2", i + "");
		}
	}
	
	@Test
	public void pullMessage(){
		for(int i = 0;i < 10 ;i++){
			System.out.println(jedisUtil.lpop(RedisKey.indexDB,"msg:cesi1"));
		}
		for(int i = 0;i < 10 ;i++){
			System.out.println(jedisUtil.lpop(RedisKey.indexDB,"msg:cesi2"));
		}
	}

}
