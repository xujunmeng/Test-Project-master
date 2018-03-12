package com.baobaotao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baobaotao.dao.LoginLogDao;
import com.baobaotao.dao.UserDao;
import com.baobaotao.domain.LoginLog;
import com.baobaotao.domain.User;

/**
@author junmeng.xu
@date  2015年12月28日下午5:13:25
 */
@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private LoginLogDao loginLogDao;
	
	private final Integer CRED = 5;
	
	public boolean hasMatchUser(String userName , String password){
		int matchCount = userDao.getMatchCount(userName, password);
		return matchCount > 0 ;
	}
	
	public User findUserByUserName(String userName){
		return userDao.findUserByUserName(userName);
	}
	
	public List<User> findUserByUserNameList(List<String> userNames){
		return userDao.findUserByUserNameList(userNames);
	}
	
	public void loginSuccess(User user){
		user.setCredits(CRED + user.getCredits());
		LoginLog loginLog = new LoginLog();
		loginLog.setUserId(user.getUserId());
		loginLog.setIp(user.getLastIp());
		loginLog.setLoginDate(user.getLastVisit());
		userDao.updateLoginInfo(user);
		loginLogDao.insertLoginLog(loginLog);
	}
	
	
}
