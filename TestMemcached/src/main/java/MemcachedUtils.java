import java.util.Date;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
@author junmeng.xu
@date  2016年5月17日下午3:24:55
 */
public class MemcachedUtils {

	protected static MemCachedClient memCachedClient = new MemCachedClient();
	protected static MemcachedUtils memcachedUtils = new MemcachedUtils();
	static{
		String[] servers = {"192.168.1.43:11211"};
		Integer[] weights = {3};
		
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(servers);
		pool.setWeights(weights);
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000*60*6);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);
		
		pool.initialize();
		
		memCachedClient.setCompressEnable(true);
		memCachedClient.setCompressThreshold(64*1024);
	}
	protected MemcachedUtils(){
		
	}
	public static MemcachedUtils getInstance(){
		return memcachedUtils;
	}
	
	public boolean add(String key, Object value){
		return memCachedClient.add(key, value);
	}
	public boolean add(String key, String value, Date expiry){
		return memCachedClient.add(key, value, expiry);
	}
	public Object get(String key){
		return memCachedClient.get(key);
	}
	
	
	
	
	
	
}
