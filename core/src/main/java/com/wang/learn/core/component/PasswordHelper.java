/**
 * PasswordHelper 2017/9/11 10:43
 * <p>
 * Copyright (C) HAND Enterprise Solutions Company Ltd.
 * All Rights Reserved
 */
package com.wang.learn.core.component;

import com.wang.learn.core.dto.ShiroUser;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author gang.wang
 * @Title: PasswordHelper
 * @Description: (描述此类的功能)
 * @date 2017/9/11 10:43
 */
@Component
public class PasswordHelper {

	private RandomNumberGenerator generator = new SecureRandomNumberGenerator();
	@Value("${shiro.passwordHelper.algorithName}")
	private String algorithName = "md5"; // default algorithName "md5"
	@Value("${shiro.passwordHelper.hasIterations}")
	private final int hasIterations = 2; // default hasIterations 2

	public void encryptPassword(ShiroUser user) {
		user.setSalt(generator.nextBytes().toHex());
		String password = new SimpleHash(algorithName, user.getPassWord(), user.getSalt(), hasIterations).toHex();
		user.setPassWord(password);
	}

	public String getAlgorithName() {
		return algorithName;
	}

	public PasswordHelper setAlgorithName(String algorithName) {
		this.algorithName = algorithName;
		return this;
	}

	public int getHasIterations() {
		return hasIterations;
	}

	public PasswordHelper setHasIterations(int hasIterations) {
		hasIterations = hasIterations;
		return this;
	}
}
