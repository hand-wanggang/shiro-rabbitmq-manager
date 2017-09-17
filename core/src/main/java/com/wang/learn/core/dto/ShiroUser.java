/**
 * ShiroUser 2017/9/8 14:28
 * <p>
 * Copyright (C) HAND Enterprise Solutions Company Ltd.
 * All Rights Reserved
 */
package com.wang.learn.core.dto;

/**
 * @author gang.wang
 * @Title: ShiroUser
 * @Description: (描述此类的功能)
 * @date 2017/9/8 14:28
 */
public class ShiroUser {

	private Long userId;
	private String userName;
	private String passWord;
	private String email;
	private String phone;
	private Boolean locked;
	private String salt;

	public Long getUserId() {
		return userId;
	}

	public ShiroUser setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public ShiroUser setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public ShiroUser setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public ShiroUser setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public Boolean getLocked() {
		return locked;
	}

	public ShiroUser setLocked(Boolean locked) {
		this.locked = locked;
		return this;
	}

	public String getSalt() {
		return salt;
	}

	public ShiroUser setSalt(String salt) {
		this.salt = salt;
		return this;
	}

	public String getPassWord() {
		return passWord;
	}

	public ShiroUser setPassWord(String passWord) {
		this.passWord = passWord;
		return this;
	}
}
