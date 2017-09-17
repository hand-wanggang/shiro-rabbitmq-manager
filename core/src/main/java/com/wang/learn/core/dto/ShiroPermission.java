/**
 * ShiroPermission 2017/9/8 14:34
 * <p>
 * Copyright (C) HAND Enterprise Solutions Company Ltd.
 * All Rights Reserved
 */
package com.wang.learn.core.dto;

/**
 * @author gang.wang
 * @Title: ShiroPermission
 * @Description: (描述此类的功能)
 * @date 2017/9/8 14:34
 */
public class ShiroPermission {

	private Integer permissionId;
	private String  permissionName;
	private String  permissionDesc;
	private Boolean locked;

	public Integer getPermissionId() {
		return permissionId;
	}

	public ShiroPermission setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
		return this;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public ShiroPermission setPermissionName(String permissionName) {
		this.permissionName = permissionName;
		return this;
	}

	public String getPermissionDesc() {
		return permissionDesc;
	}

	public ShiroPermission setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc;
		return this;
	}

	public Boolean getLocked() {
		return locked;
	}

	public ShiroPermission setLocked(Boolean locked) {
		this.locked = locked;
		return this;
	}
}
