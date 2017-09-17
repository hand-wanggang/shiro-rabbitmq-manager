/**
 * UserRealm 2017/9/11 9:32
 * <p>
 * Copyright (C) HAND Enterprise Solutions Company Ltd.
 * All Rights Reserved
 */
package com.wang.learn.core.realm;

import com.wang.learn.core.dto.ShiroPermission;
import com.wang.learn.core.dto.ShiroRole;
import com.wang.learn.core.dto.ShiroUser;
import com.wang.learn.core.service.ShiroUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gang.wang
 * @Title: UserRealm
 * @Description: (描述此类的功能)
 * @date 2017/9/11 9:32
 */
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private ShiroUserService shiroUserService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String principal = (String) principals.getPrimaryPrincipal();
		ShiroUser user = shiroUserService.selectUserByName(principal);
		if (null == user) {
			user = shiroUserService.selectUserByEmail(principal);
		}
		if (null == user) {
			user = shiroUserService.selectUserByPhone(principal);
		}
		if (null == user) {
			throw new RuntimeException("用户不存在");
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> roles = new HashSet<>();
		Set<String> permissions = new HashSet<>();
		List<ShiroRole> roleList = shiroUserService.getUserRoles(user.getUserId());
		List<ShiroPermission> permissionList = shiroUserService.getUserPermissions(user.getUserId());
		if (CollectionUtils.isNotEmpty(roleList)) {
			roleList.forEach(item -> {
				roles.add(item.getRoleName());
			});
		}
		if (CollectionUtils.isNotEmpty(permissionList)) {
			permissionList.forEach(item -> {
				permissions.add(item.getPermissionName());
			});
		}
		info.setRoles(roles);
		info.setStringPermissions(permissions);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String principal = (String) token.getPrincipal();
		ShiroUser user = shiroUserService.selectUserByName(principal);
		if (null == user) {
			user = shiroUserService.selectUserByEmail(principal);
		}
		if (null == user) {
			user = shiroUserService.selectUserByPhone(principal);
		}
		if (null == user) {
			throw new RuntimeException("用户不存在");
		}
		// 开始身份验证
		AuthenticationInfo info = new SimpleAuthenticationInfo(principal, user.getPassWord(),
				ByteSource.Util.bytes(user.getSalt()), getName());
		return info;
	}
}
