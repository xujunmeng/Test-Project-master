package loadingCache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.Test;

import java.util.concurrent.TimeUnit;


/**
@author junmeng.xu
@date  2016年3月29日下午1:23:10f
 */
public class Main {

	// 消息最大发送频率：3分钟一次
	private Cache<String, Integer> cache_max_frequency = 
			CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.MINUTES).build();
	// 消息最大发送次数：3次。然后冻结半小时
	private static Cache<String, Integer> cache_max_times = 
			CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).build();
	
	public Boolean Method(String str){
		
		// 3分钟内，已经发送了1次同类消息，则block
		Integer max_frequency = cache_max_frequency.getIfPresent(str);
		if(max_frequency == null){
			cache_max_frequency.put(str, 1);
		} else {
			return true;
		}
		
		// 30分钟内，已经发送了3次同类消息，则block
		Integer max_times = cache_max_times.getIfPresent(str);
		if(max_times == null){
			cache_max_times.put(str, 1);
		} else {
			if(max_times < 3){
				cache_max_times.put(str, max_times + 1);
			} else {
				return true;
			}
		}
		
		return false;
		
		
		
	}
	
	
	@Test
	public void test22(){
		Boolean method = Method("junmeng");
		System.out.println(method);
	}
		
		
		
}
