/**
 * ShiroConfig 2017/9/11 10:58
 * <p>
 * Copyright (C) HAND Enterprise Solutions Company Ltd.
 * All Rights Reserved
 */
package com.wang.learn.core.config;

import com.wang.learn.core.dao.MyShiroSessionDao;
import com.wang.learn.core.realm.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authz.SslFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.LinkedHashMap;

/**
 * @author gang.wang
 * @Title: ShiroConfig
 * @Description: (描述此类的功能)
 * @date 2017/9/11 10:58
 */
@Configuration
public class ShiroConfig {

	@Autowired
	private RedisTemplate redisTemplate;


	// 配置hash密码匹配器
	@ConfigurationProperties(prefix = "shiro.hashedCredentialsMatcher")
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher(){
		return new HashedCredentialsMatcher();
	}


	@ConfigurationProperties(prefix = "shiro.session")
	@Bean
	public SimpleCookie cookie(){
		return  new SimpleCookie();
	}

	@Bean
	public UserRealm realm(){
		UserRealm realm = new UserRealm();
		realm.setCredentialsMatcher(hashedCredentialsMatcher());
		return realm;
	}

	// 配置安全管理器
	@Bean
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(realm());
		securityManager.setSessionManager(sessionManager());
		securityManager.setCacheManager(cacheManager());
		SecurityUtils.setSecurityManager(securityManager);
		return securityManager;
	}

	// 配置session的持久化
	@Bean
	public MyShiroSessionDao sessionDao(RedisTemplate redisTemplate){
		MyShiroSessionDao sessionDao = new MyShiroSessionDao();
		sessionDao.setRedisTemplate(redisTemplate);
		return sessionDao;
	}

	// 使用默认的会话管理器
	@Bean
	public DefaultWebSessionManager sessionManager(){
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionIdCookie(cookie());
		sessionManager.setSessionDAO(sessionDao(redisTemplate));
		return sessionManager;
	}

	// 缓存管理
	@Bean
	public EhCacheManager cacheManager(){
		return new EhCacheManager();
	}

	// 访问资源的权限拦截
	@Bean(name="shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
		ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
		bean.getFilters().put("ssl",sslFilter());
		bean.setSecurityManager(manager);
		//配置登录的url和登录成功的url
		bean.setLoginUrl("/login");
		bean.setSuccessUrl("/home");
		//配置访问权限
		LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/logout*","anon");
		filterChainDefinitionMap.put("/*", "authc");//表示需要认证才可以访问
		/*
		filterChainDefinitionMap.put("/*", "authc");//表示需要认证才可以访问
		filterChainDefinitionMap.put("/**", "authc");//表示需要认证才可以访问
		filterChainDefinitionMap.put("/*.*", "authc");*/
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}

	// 添加ssl过滤器
	@ConfigurationProperties(prefix = "shiro.ssl")
	@Bean
	public SslFilter sslFilter(){
		return new SslFilter();
	}

	//-----------------------------------------开启Shiro注解支持------------------------
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	@DependsOn(value = "lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
		proxyCreator.setProxyTargetClass(Boolean.TRUE);
		return proxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}
