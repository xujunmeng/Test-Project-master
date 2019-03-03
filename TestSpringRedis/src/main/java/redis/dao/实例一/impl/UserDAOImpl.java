package redis.dao.实例一.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import redis.dao.实例一.UserDAO;
import redis.obj.User;

import java.util.List;

/**
 * @author junmeng.xu
 * @date 2016年4月14日下午5:51:36
 */
public class UserDAOImpl implements UserDAO {

	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	public void testSave(User user) {
		redisTemplate.opsForValue().set("key1", user, 20);
	}

	public void saveUser(User user) {
		
		redisTemplate.execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize("user:uid" + user.getId());
				byte[] value = redisTemplate.getStringSerializer().serialize(user.getName());
				connection.set(key, value);
				return null;
			}
		});
	}
	
	@Override
	public List<String> addList(String str) {
		redisTemplate.execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(redisTemplate.getStringSerializer().serialize("cd"), redisTemplate.getStringSerializer().serialize(str));
				return null;
			}
		});
		return null;
	}

	public User getUser(long id) {
		
		return redisTemplate.execute(new RedisCallback<User>() {

			@Override
			public User doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize("user:uid" + id);
				if(connection.exists(key)){
					byte[] value = connection.get(key);
					String name = redisTemplate.getStringSerializer().deserialize(value);
					User user = new User();
					user.setName(name);
					user.setId(id);
					return user;
				}
				return null;
			}
		});
	}



}
