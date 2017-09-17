/**
 * DataSourceConfig 2017/9/8 14:51
 * <p>
 * Copyright (C) HAND Enterprise Solutions Company Ltd.
 * All Rights Reserved
 */
package com.wang.learn.core.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gang.wang
 * @Title: DataSourceConfig
 * @Description: (描述此类的功能)
 * @date 2017/9/8 14:51
 */
@Configuration
public class DataSourceConfig {

	/* 数据库连接池配置 */
	@ConfigurationProperties(prefix = "dataSource")
	@Bean
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	/* 配置数据源 */
	@Bean
	public HikariDataSource dataSource(HikariConfig hikariConfig) {
		return new HikariDataSource(hikariConfig);
	}
}
