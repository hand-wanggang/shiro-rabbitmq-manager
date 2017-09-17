/**
 * ShiroRole 2017/9/8 14:32
 * <p>
 * Copyright (C) HAND Enterprise Solutions Company Ltd.
 * All Rights Reserved
 */
package com.wang.learn.core.dto;

/**
 * @author gang.wang
 * @Title: ShiroRole
 * @Description: (描述此类的功能)
 * @date 2017/9/8 14:32
 */
public class ShiroRole {

	private Integer roleId;
	private String roleName;
	private String roleDesc;
	private Boolean locked;

	public Integer getRoleId() {
		return roleId;
	}

	public ShiroRole setRoleId(Integer roleId) {
		this.roleId = roleId;
		return this;
	}

	public String getRoleName() {
		return roleName;
	}

	public ShiroRole setRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public ShiroRole setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
		return this;
	}

	public Boolean getLocked() {
		return locked;
	}

	public ShiroRole setLocked(Boolean locked) {
		this.locked = locked;
		return this;
	}
}
