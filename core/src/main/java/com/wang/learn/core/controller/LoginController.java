/**
 * LoginController 2017/9/11 11:15
 * <p>
 * Copyright (C) HAND Enterprise Solutions Company Ltd.
 * All Rights Reserved
 */
package com.wang.learn.core.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gang.wang
 * @Title: LoginController
 * @Description: (描述此类的功能)
 * @date 2017/9/11 11:15
 */
@Controller
@RequestMapping("/")
public class LoginController {

	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login")
	public String login(String principal, String password){
		Assert.notNull(principal,"用户名不能为空");
		Assert.notNull(password,"密码不能为空");
		Subject subject = SecurityUtils.getSubject();
		AuthenticationToken token = new UsernamePasswordToken(principal,password.toCharArray());
		try {
			subject.login(token);
		}catch (Exception ex){
			LOG.debug(ex.getMessage());
			return "error";
		}
		return "home";
	}
}
