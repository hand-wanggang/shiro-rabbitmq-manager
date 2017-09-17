package com.wang.learn.core.mapper;

import com.wang.learn.core.dto.ShiroUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShiroUserMapper {

	ShiroUser selectUserByName(@Param("userName") String userName);

	ShiroUser selectUserByPhone(@Param("phone") String phone);

	ShiroUser selectUserByEmail(@Param("email") String email);

	ShiroUser insert(ShiroUser user);
}
