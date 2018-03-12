package 数据网格的例子;

import com.google.common.collect.HashBasedTable;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.junit.Test;
import 测试quant.CsfIgnite;

import javax.cache.Cache;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author junmeng.xu
 * @date 2016年6月3日下午5:54:46
 */
public class Main {

	public static void main(String[] args) {
		Ignite ignite = Ignition
				.start("D:/javaWorkLianxi/Test-Ignite/src/main/java/example-ignite.xml");
			IgniteCache<Integer, String> cache = ignite.cache("junmeng");
			// Store keys in cache (values will end up on different cache
			// nodes).
			for (int i = 0; i < 10; i++)
				cache.put(i, Integer.toString(i) + "mengmeng");
			for (int i = 0; i < 10; i++)
				System.out.println("Got [key=" + i + ", val=" + cache.get(i)
						+ ']');
			IgniteConfiguration configuration = ignite.configuration();
			
			CacheConfiguration[] cacheConfiguration = configuration.getCacheConfiguration();
			for (CacheConfiguration cacheConfiguration2 : cacheConfiguration) {
				System.out.println(cacheConfiguration2);
			}
			

	}

	@Test
	public void test(){
		Map<String, Object[][]> data = saveData();

		Ignite ignite = CsfIgnite.getIgnite();
		IgniteCache<String, Object[][]> obj = ignite.cache("junmeng");

		obj.removeAll();

		Set<Map.Entry<String, Object[][]>> entries = data.entrySet();
		for (Map.Entry<String, Object[][]> entry : entries) {
			String key = entry.getKey();
			Object[][] value = entry.getValue();
			obj.put(key, value);
		}

	}


	public Map<String, Object[][]> saveData(){
		HashBasedTable<String, String, Object[]> dataTickVal = HashBasedTable.create();
		dataTickVal.put("2001-01-01", "000001", new Object[]{"000001", 3.4D, 5.2D});
		dataTickVal.put("2001-01-02", "000002", new Object[]{"000002", 3.3D, 6.2D});
		dataTickVal.put("2001-01-03", "000003", new Object[]{"000003", 3.2D, 7.2D});
		dataTickVal.put("2001-01-04", "000004", new Object[]{"000004", 3.1D, 8.2D});
		dataTickVal.put("2001-01-05", "000005", new Object[]{"000005", 3.0D, 9.2D});
		dataTickVal.put("2001-01-06", "000006", new Object[]{"000006", 2.9D, 10.2D});
		Map<String, Map<String, Object[]>> map = dataTickVal.rowMap();
		Map<String, Object[][]> result = new HashMap<>();
		Set<Map.Entry<String, Map<String, Object[]>>> set = map.entrySet();
		for (Map.Entry<String, Map<String, Object[]>> entry : set) {
			String dt = entry.getKey();
			Map<String, Object[]> tv = entry.getValue();
			int len = tv.size();
			Object[][] arr = new Object[len][];
			int i = 0;
			Collection<Object[]> values = tv.values();
			for (Object[] o : values){
				arr[i++] = o;
			}
			result.put(dt, arr);
		}
		return result;
	}

	@Test
	public void getData(){

		Ignite ignite = CsfIgnite.getIgnite();
		IgniteCache<Object, Object[]> obj = ignite.cache("junmeng");

		for (Cache.Entry<Object, Object[]> entry : obj) {
			Object key = entry.getKey();
			Object[] value = entry.getValue();
			System.out.println(key + "  " + value);
		}
	}

	@Test
	public void getData2(){

		Ignite ignite = CsfIgnite.getIgnite();
		IgniteCache<Object, Object> obj = ignite.cache("junmeng");

		for (Cache.Entry<Object, Object> entry : obj) {
			Object key = entry.getKey();
			Object value = entry.getValue();
			System.out.println(key + "  " + value);
		}
	}


}
