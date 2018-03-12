package googleguava;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
@author junmeng.xu
@date  2016年2月19日下午2:04:14
 */
public class BookCache {

	public static Cache<String,List<Book>> cache = CacheBuilder.newBuilder()
			.expireAfterAccess(8, TimeUnit.SECONDS).maximumSize(10)
			.build();
	
}
