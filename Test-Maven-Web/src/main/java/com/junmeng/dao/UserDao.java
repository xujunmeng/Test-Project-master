package com.junmeng.dao;

import org.apache.ibatis.annotations.Param;

import com.junmeng.entity.User;

/**
@author junmeng.xu
@date  2016年6月1日下午3:50:49
 */
public interface UserDao {

	User findUser(@Param("info") String info);
	
	int regist(User user);
	
	int updateLastLoginTime(@Param("pkey") String pkey);
}
