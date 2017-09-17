package com.wang.learn.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wang.learn.core.component.PasswordHelper;
import com.wang.learn.core.dto.ShiroUser;
import com.wang.learn.core.service.ShiroUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoreApplicationTests {

	@Autowired
	private ShiroUserService userService;

	@Autowired
	private PasswordHelper passwordHelper;

	@Test
	public void contextLoads() {

	}

	@Test
	public void testCreateUser(){
		ShiroUser user = new ShiroUser();
		user.setUserName("wg");
		user.setEmail("15056008995@163.com");
		user.setPhone("15555446371");
		user.setPassWord("asdf19765");
		passwordHelper.encryptPassword(user);
		userService.insert(user);
	}
}
