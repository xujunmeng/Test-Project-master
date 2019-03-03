package redis.dao.实例一;


import redis.obj.User;

import java.util.List;

/**
@author junmeng.xu
@date  2016年4月14日下午5:52:17
 */
public interface UserDAO {

	User getUser(long id);

	void saveUser(User user);

	//add
	List<String> addList(String str);
}
