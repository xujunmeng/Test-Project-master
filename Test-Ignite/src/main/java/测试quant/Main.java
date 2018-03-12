package 测试quant;

import java.util.HashMap;
import java.util.Map;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.junit.Test;

/**
@author junmeng.xu
@date  2016年9月29日下午5:41:07
 */
public class Main {

	/**
	 * 存入数据
	 */
	@Test
	public void test23(){
		Map<Long, HqPrice> data = new HashMap<>();
		data.put(1l, new HqPrice("111", "000001", 2.2, 2.2, 2.2, 2.2, 2.2, 2.2));
		data.put(2l, new HqPrice("222", "000002", 2.3, 2.3, 2.3, 2.3, 2.3, 2.3));
		IgniteCache<Long,HqPrice> cache = CsfIgnite.getIgnite().getOrCreateCache("junmengTest1");
		data.forEach(cache::put);
	}
	
	/**
	 * 获取最新的时间
	 */
	@Test
	public void test2323(){
		
		CacheConfiguration<String, String> config = new CacheConfiguration<>("junmengTest1");
		System.out.println("config : " + config);
		CacheConfiguration<String,String> mode = config.setCacheMode(CacheMode.REPLICATED);
		System.out.println("mode : " + mode);
		IgniteCache<String,String> igniteCache = CsfIgnite.getIgnite().getOrCreateCache(config);
		String lastDate = igniteCache.get("junmengTest1");
		System.out.println("lastDate : " + lastDate);
		
	}
	
}
