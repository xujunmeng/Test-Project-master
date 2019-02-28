package redis.dao.实例一;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.BaseTestCase;
import redis.dao.实例一.UserDAO;
import redis.obj.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
@author junmeng.xu
@date  2016年5月6日上午11:40:24
 */
public class UserDaoTest extends BaseTestCase {

	@Autowired
	UserDAO userDAO;

	@Test
	public void test(){
		List<String> addList = userDAO.addList("asdfaasdfasdfasdf");
		System.out.println(addList);
	}

	@Test
	public void test2(){
		Map<String, String> param = new HashMap<>();
		param.put("from", "2016-11-04");
		String from = param.getOrDefault("from", "");
		System.out.println(from);
	}

	@Test
	public void test3() {
		User user1 = new User();
		user1.setId(6);
		user1.setName("xujunmneg6");
		userDAO.saveUser(user1);
		User user2 = userDAO.getUser(6);
		System.out.println(user2.getName());
	}

}
