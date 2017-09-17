/**
 * ShiroRoleMapper 2017/9/11 9:54
 * <p>
 * Copyright (C) HAND Enterprise Solutions Company Ltd.
 * All Rights Reserved
 */
package com.wang.learn.core.mapper;

import com.wang.learn.core.dto.ShiroRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gang.wang
 * @Title: ShiroRoleMapper
 * @Description: (描述此类的功能)
 * @date 2017/9/11 9:54
 */
@Mapper
public interface ShiroRoleMapper {

	ShiroRole selectRoleById(@Param("roleId")Integer roleId);
	List<ShiroRole> selectUserRoles(@Param("userId") Long userId);
	void insert(ShiroRole role);
}
