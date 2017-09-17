/**
 * ShiroUserServiceImpl 2017/9/11 9:40
 * <p>
 * Copyright (C) HAND Enterprise Solutions Company Ltd.
 * All Rights Reserved
 */
package com.wang.learn.core.service.impl;

import com.wang.learn.core.dto.ShiroPermission;
import com.wang.learn.core.dto.ShiroRole;
import com.wang.learn.core.dto.ShiroUser;
import com.wang.learn.core.mapper.ShiroPermissionMapper;
import com.wang.learn.core.mapper.ShiroRoleMapper;
import com.wang.learn.core.mapper.ShiroUserMapper;
import com.wang.learn.core.service.ShiroUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gang.wang
 * @Title: ShiroUserServiceImpl
 * @Description: (描述此类的功能)
 * @date 2017/9/11 9:40
 */
@Service
public class ShiroUserServiceImpl implements ShiroUserService {

	@Autowired
	private ShiroUserMapper shiroUserMapper;

	@Autowired
	private ShiroPermissionMapper permissionMapper;

	@Autowired
	private ShiroRoleMapper roleMapper;

	@Override
	public ShiroUser selectUserByName(String userName) {
		return shiroUserMapper.selectUserByName(userName);
	}

	@Override
	public ShiroUser selectUserByPhone(String phone) {
		return shiroUserMapper.selectUserByPhone(phone);
	}

	@Override
	public ShiroUser selectUserByEmail(String email) {
		return shiroUserMapper.selectUserByEmail(email);
	}

	@Override
	public List<ShiroRole> getUserRoles(Long userId) {
		return roleMapper.selectUserRoles(userId);
	}

	@Override
	public List<ShiroPermission> getUserPermissions(Long userId) {
		return permissionMapper.selectUserPermissionById(userId);
	}

	@Override
	public void insert(ShiroUser user) {
		shiroUserMapper.insert(user);
	}
}
