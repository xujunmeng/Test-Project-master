package com.baobaotao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.baobaotao.domain.User;

/**
 * @author junmeng.xu
 * @date 2015年12月28日下午3:50:52
 */
@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	public int getMatchCount(String userName, String password) {
		String sqlStr = " select count(*) from t_user where user_name = ? and password = ? ";
		return jdbcTemplate.queryForInt(sqlStr, new Object[] { userName,
				password });
	}

	public List<User> findUserByUserNameList(List<String> userNames){
		String substring = dealListToStr(userNames);
		String sql = " select user_id , user_name , password from t_user where user_name in " + substring;
		final List<User> users = new ArrayList<User>();
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				User user = new User();
				user.setUserId(rs.getInt("user_Id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}
			
		});
		return users;
	}
	private String dealListToStr(List<String> sids) {
		String strs = "";
		for (String string : sids) {
			strs += "'"+string+"',";
		}
		String substring = "("+strs.substring(0, strs.length()-1)+")";
		return substring;
	}
	
	public User findUserByUserName(final String userName) {

		String sqlStr = " select user_id , user_name , credits from t_user where user_name = ? ";
		final User user = new User();
		jdbcTemplate.query(sqlStr, new Object[] { userName },
				new RowCallbackHandler() {

					public void processRow(ResultSet rs) throws SQLException {
						user.setUserId(rs.getInt("user_id"));
						user.setUserName(userName);
						user.setCredits(rs.getInt("credits"));
					}
				});
		return user;
	}

	public void updateLoginInfo(User user) {
		String sqlStr = " update t_user set last_visit = ? , last_ip = ? , credits = ? where user_id = ? ";
		jdbcTemplate.update(
				sqlStr,
				new Object[] { user.getLastVisit(), user.getLastIp(),
						user.getCredits(), user.getUserId() });
	}
}
