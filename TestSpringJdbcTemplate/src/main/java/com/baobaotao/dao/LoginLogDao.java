package com.baobaotao.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.baobaotao.domain.LoginLog;

/**
 * @author junmeng.xu
 * @date 2015年12月28日下午4:24:57
 */
@Repository
public class LoginLogDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertLoginLog(LoginLog loginLog) {
		String sqlStr = " insert into t_login_log(user_id,ip,login_datetime) values(?,?,?) ";
		jdbcTemplate.update(sqlStr, new Object[] { loginLog.getUserId(),
				loginLog.getIp(), loginLog.getLoginDate() });

	}
}
