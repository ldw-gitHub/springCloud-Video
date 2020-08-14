package com.itcast.dw.config;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:redis.properties")
@Primary
public class JedisConfig {

	public Logger log = Logger.getLogger(getClass());

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.password}")
  private String password;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Value("${spring.redis.jedis.pool.max-idle}")
	private int maxIdle;

/*	@Value("${spring.redis.jedis.pool.max-wait}")
	private long maxWaitMillis;*/

	/*@Value("${spring.redis.password}")
	private String password;*/

	/*@Value("${spring.redis.block-when-exhausted}")
	private boolean blockWhenExhausted;*/

	@Bean
	@Primary
	public JedisPool redisPoolFactory() throws Exception {
		log.info("JedisPool注入成功！！");
		log.info("redis地址：" + host + ":" + port);
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		//jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		// 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
		//jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
		// 是否启用pool的jmx管理功能, 默认true
		jedisPoolConfig.setJmxEnabled(true);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout,password);
		return jedisPool;
	}

	/**
	 *  #redis配置开始
		# Redis数据库索引（默认为0）
		spring.redis.database=0
		# Redis服务器地址
		spring.redis.host=localhost
		# Redis服务器连接端口
		spring.redis.port=6379
		# Redis服务器连接密码（默认为空）
		spring.redis.password=123456
		# 连接池最大连接数（使用负值表示没有限制）
		spring.redis.jedis.pool.max-active=1024
		# 连接池最大阻塞等待时间（使用负值表示没有限制）
		spring.redis.jedis.pool.max-wait=10000
		# 连接池中的最大空闲连接
		spring.redis.jedis.pool.max-idle=200
		# 连接池中的最小空闲连接
		spring.redis.jedis.pool.min-idle=0
		# 连接超时时间（毫秒）
		spring.redis.timeout=10000
		#redis配置结束
		spring.redis.block-when-exhausted=true
	 */

}
