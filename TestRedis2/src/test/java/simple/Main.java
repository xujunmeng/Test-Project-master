package simple;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Response;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;

/**
@author junmeng.xu
@date  2016年4月20日下午1:43:39
 */
public class Main extends BaseTestCase {

	@Autowired
	public ShardedJedisPool shardedJedisPool;
	
	ShardedJedis jedis;
	
	@Before
	public void Before(){
		jedis = shardedJedisPool.getResource();
	}
	
	/**
	 * redis存储字符串
	 */
	@Test
	public void test(){
		jedis.set("xjmxjm", "heihaheiha");
		System.out.println(jedis.get("xjmxjm"));
		System.out.println(jedis.exists("xjmxjm"));
		jedis.append("xjmxjm", "添加的数据");
		jedis.append("xjmxjm", "的数据");
	}
	//删除某个键
	@Test
	public void test2(){
		Long del = jedis.del("xjm");
		System.out.println(del);
	}
	//进行加一操作
	@Test
	public void test3(){
		Long incr = jedis.incr("xjmxjmx");
		System.out.println(incr);
	}
	/**
	 * redis操作map
	 */
	@Test
	public void test4(){
		//添加数据
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "xinxin");
		map.put("age", "22");
		map.put("qq", "123456");
		String hmset = jedis.hmset("user",map);
		System.out.println(hmset);
		
	}
	//返回map中的所有值
	@Test
	public void test5(){
		Set<String> hkeys = jedis.hkeys("user");
		for (String str : hkeys) {
			System.out.println(str);
			
		}
		List<String> hvals = jedis.hvals("user");
		for (String str : hvals) {
			System.out.println(str);
			
		}

	}
	@Test
	public void test6(){
		ShardedJedisPipeline pipelined = jedis.pipelined();
		Response<Set<String>> hkeys = pipelined.hkeys("user");
		Set<String> set = hkeys.get();
		for (String string : set) {
			System.out.println(string);
		}
	}
	//jedis操作set
	@Test
	public void test7(){
//		jedis.sadd("set1", "liuling");
//		jedis.sadd("set1", "xinxin");
//		jedis.sadd("set1", "ling");
//		jedis.sadd("set1", "zhangxinxin");
//		jedis.sadd("set1", "who");
		System.out.println(jedis.smembers("set1"));
		Boolean sismember = jedis.sismember("set2", "who");
		System.out.println(sismember);
	}

	@Test
	public void test555(){
		String user_coupon_remind = jedis.get("USER_COUPON_REMIND");
		System.out.println(user_coupon_remind);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
