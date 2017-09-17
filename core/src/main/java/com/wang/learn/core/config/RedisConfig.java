/**
 * RedisConfig 2017/9/11 15:50
 * <p>
 * Copyright (C) HAND Enterprise Solutions Company Ltd.
 * All Rights Reserved
 */
package com.wang.learn.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author gang.wang
 * @Title: RedisConfig
 * @Description: (描述此类的功能)
 * @date 2017/9/11 15:50
 */
@Configuration
public class RedisConfig {

	@ConfigurationProperties(prefix = "spring.redis")
	@Bean
	public JedisConnectionFactory jedisConnectionFactory(){
		return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<?,?> redisTemplate(){
		return new StringRedisTemplate(jedisConnectionFactory());
	}
}
