package com.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author junmeng.xu
 * @date 2016年5月9日下午5:25:13
 */
public class TestRedis {

	private Jedis jedis;

	//操作list   开始
	@Test
	public void setd(){
		jedis = new Jedis("192.168.250.200", 6379);
		jedis.zadd("highList", 1,  "2015-01-01||0.369");
		jedis.zadd("highList", 1,  "2015-01-02||0.323");
		jedis.zadd("highList", 1,  "2015-01-03||0.455");
		jedis.zadd("highList", 1,  "2015-01-04||0.234");
		jedis.zadd("highList", 1,  "2015-01-05||0.743");
		jedis.zadd("highList", 1,  "2015-01-03||0.455");
	}

	@Test
	public void sdsd(){
		jedis = new Jedis("192.168.250.200", 6379);
		Boolean exists = jedis.sismember("highList", "2015-01-02||0.323");
		System.out.println(exists);

	}




	//操作list   结束


	@Test
	public void test() {
		jedis = new Jedis("192.168.1.43", 6379);
		// 添加
		jedis.sadd("userss", "liuling");
		jedis.sadd("userss", "xinxin");
		jedis.sadd("userss", "ling");
		jedis.sadd("userss", "zhangxinxin");
		jedis.sadd("userss", "who");
		// 移除noname
		jedis.srem("userss", "who");
		System.out.println(jedis.smembers("userss"));// 获取所有加入的value
		System.out.println(jedis.sismember("userss", "who"));// 判断 who
															// 是否是user集合的元素
		System.out.println(jedis.srandmember("userss"));
		System.out.println(jedis.scard("userss"));// 返回集合的元素个数
	}

	/**
	 * incr 方法的使用
	 */
	@Test
	public void tes22(){
		jedis = new Jedis("192.168.1.43", 6379);
		Long xujunmeng = jedis.incr("cache:quant:quant:service:factor_zan:zan_M001001");
		System.out.println(xujunmeng);
	}


	@Test
	public void test2() {
		jedis = new Jedis("192.168.1.43", 6379);
		Long persist = jedis.persist("8053");
		System.out.println(persist);
		Long sadd = jedis.sadd("cache:quant:service:quant_zan", "aaa15");
		System.out.println(sadd);
		jedis.sadd("cache:quant:service:quant_zan", "aaa16");
		jedis.sadd("cache:quant:service:quant_zan", "aaa17");
		jedis.sadd("cache:quant:service:quant_zan", "aaa18");
		jedis.sadd("cache:quant:service:quant_zan", "aaa19");
		jedis.sadd("cache:quant:service:quant_zan", "aaa20");
		jedis.sadd("cache:quant:service:quant_zan", "aaa21");
		System.out.println(jedis.smembers("cache:quant:service:quant_zan"));
		Boolean sismember = jedis.sismember("set2", "aaa3");
		System.out.println(sismember);
	}

	@Test
	public void testsdf(){
		jedis = new Jedis("192.168.1.43", 6379);
		Set<String> keys = jedis.keys("cloud*");
		for (String string : keys) {
			jedis.del(string);
		}
	}

	//yyyy-MM-dd HH:mm:ss
	@Test
	public void test170(){



		jedis = new Jedis("122.144.134.170", 6379);
		Set<String> keys = jedis.keys("TEST:hqfsj:*");
		for (String key : keys) {
			//小时+分钟--ListData
			Map<String, List<TimeShare>> map = Maps.newLinkedHashMap();

			Long llen = jedis.llen(key);
			List<String> lrange = jedis.lrange(key, 0, llen);
			List<TimeShare> list = Lists.newArrayList();
			for (String s : lrange) {
				TimeShare timeShare = new TimeShare(s);
				list.add(timeShare);
			}
			for (TimeShare share : list) {
				String dt = share.getDt();
				String HH = dt.split(":")[0];
				String mm = dt.split(":")[1];
				String ss = dt.split(":")[2];
				String hhmm = HH + mm;
				if(!map.containsKey(hhmm)){
					map.put(hhmm, new ArrayList<>());
				}
				map.get(hhmm).add(share);
			}




		}

	}

	
}
