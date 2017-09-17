package com.wang.learn.core.mapper;

import com.wang.learn.core.dto.ShiroPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ShiroPermissionMapper {

	ShiroPermission selectPermissionById(@Param("permissionId") Integer permissionId);
	List<ShiroPermission> selectUserPermissionById(@Param("userId") Long userId);
	void insert(ShiroPermission permission);
}
