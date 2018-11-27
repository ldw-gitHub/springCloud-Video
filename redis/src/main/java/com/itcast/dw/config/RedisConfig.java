package com.itcast.dw.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableAutoConfiguration 
public class RedisConfig {
	
	private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);
	
    @Value("${spring.redis.host}")
    private  String redisHost;
 
    @Value("${spring.redis.port}")
    private  int redisPort;
 
    @Value("${spring.redis.password}")
    private  String password;
    
    @Value("${spring.redis.pool.max-active}")
    private  int max_active;
    
    @Value("${spring.redis.pool.max-idle}")
    private  int max_idle;
    
    @Value("${spring.redis.pool.min-idle}")
    private  int min_idle;
    
    @Value("${spring.redis.pool.max-wait}")
    private  Long wait;
    
    @Bean
    public JedisPoolConfig getRedisConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(max_active);
        config.setMaxIdle(max_idle);
        config.setMinIdle(min_idle);
        config.setMaxWaitMillis(wait);
        return config;
    }
 
    @Bean
    public JedisConnectionFactory getConnectionFactory(){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redisHost);
       // factory.setPassword(RedisPassword.of(password)+"");
        factory.setPort(redisPort);
        factory.afterPropertiesSet();
        factory.setUsePool(true);
        
        JedisPoolConfig config = getRedisConfig();
        factory.setPoolConfig(config);
        logger.info("JedisConnectionFactory bean init success.");
        return factory;
    }
 
    @Bean
    @Primary
    public RedisTemplate redisTemplate() {
        JedisConnectionFactory jedisConnectionFactory = getConnectionFactory();
        StringRedisTemplate template = new StringRedisTemplate(jedisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
	
	 
}
