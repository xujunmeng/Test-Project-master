package com.baobaotao.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baobaotao.domain.User;

/**
@author junmeng.xu
@date  2015年12月28日下午5:20:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestUserService {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testHasMatchUser(){
		boolean b1 = userService.hasMatchUser("admin1", "123456");
		System.out.println(b1);
	
	}
	@Test
	public void testFindUserByUserName(){
		List<String> userNames = new ArrayList<String>();
		userNames.add("admin1");
		userNames.add("admin2");
		userNames.add("admin3");
		userNames.add("admin4");
		userNames.add("admin5");
		userNames.add("admin6");
		List<User> findUserByUserNameList = userService.findUserByUserNameList(userNames);
		for (User user : findUserByUserNameList) {
			System.out.println(user);
		}
	}
	
}
