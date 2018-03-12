package dao;

import model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
@author junmeng.xu
@date  2016年1月11日下午3:19:11
 */
public interface UserDao {
	
	User getUser(User user);
	
	void addUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(int UserId);

	void insert2(User user);

	void insert3(String sql);

	List<User> getUsers(@Param("begin") Integer begin, @Param("end") Integer end);

}	
