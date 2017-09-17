/**
 * ShiroUserService 2017/9/11 9:33
 * <p>
 * Copyright (C) HAND Enterprise Solutions Company Ltd.
 * All Rights Reserved
 */
package com.wang.learn.core.service;

import com.wang.learn.core.dto.ShiroPermission;
import com.wang.learn.core.dto.ShiroRole;
import com.wang.learn.core.dto.ShiroUser;

import java.util.List;

/**
 * @author gang.wang
 * @Title: ShiroUserService
 * @Description: (描述此类的功能)
 * @date 2017/9/11 9:33
 */
public interface ShiroUserService {

	ShiroUser selectUserByName( String userName);

	ShiroUser selectUserByPhone( String phone);

	ShiroUser selectUserByEmail( String email);

	List<ShiroRole> getUserRoles(Long userId);

	List<ShiroPermission> getUserPermissions(Long userId);

	void insert(ShiroUser user);
}
