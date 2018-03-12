package com.junmeng.repository;

import com.junmeng.dao.UserDao;
import com.junmeng.entity.User;

/**
@author junmeng.xu
@date  2016年6月1日下午3:44:14
 */
public class UserRepository {

	private UserDao userDao;
	
	public User findUser(String info){
		return userDao.findUser(info);
	}

	public int regist(User user) {
		return userDao.regist(user);
	}
	
	public int updateLastLoginTime(String pkey){
		return userDao.updateLastLoginTime(pkey);
	}
	
	
	
	
	
	
	
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	
	
	
}
