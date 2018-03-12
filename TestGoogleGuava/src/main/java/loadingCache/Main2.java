package loadingCache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author junmeng.xu
 * @date 2016年4月22日下午2:02:38
 */
public class Main2 {

	private static Cache<String, Temp> cache = CacheBuilder
			.newBuilder()
			.expireAfterWrite(10, TimeUnit.MINUTES)
			.build();

	public static void main(String[] args){

		while(true){
//			Temp t1 = new Main2().test23("aaa");
//			Temp t2 = new Main2().test23("aaa");
//			Temp t3 = new Main2().test23("ccc");
//			Temp t4 = new Main2().test23("ddd");
//			Temp t5 = new Main2().test23("ccc");

			Temp t1 = new Main2().test24("aaa");
			System.out.println(t1);
			Temp t2 = new Main2().test24("aaa");
			System.out.println(t2);
			Temp t3 = new Main2().test24("ccc");
			System.out.println(t3);
			Temp t4 = new Main2().test24("ddd");
			System.out.println(t4);
			Temp t5 = new Main2().test24("ccc");
			System.out.println(t5);

		}






	}

	public Temp test23(String key){
		Temp temp = cache.getIfPresent(key);
		if(temp == null){
			temp = daoTest(key);
			cache.put(key, temp);
		}
		return temp;
	}

	public Temp test24(String key){
		Temp temp = null;
		try {
			temp = cache.get(key, () -> daoTest(key));
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public Temp daoTest(String key){
		Map<String, Temp> map = new HashMap<>();
		map.put("aaa", new Temp("name1", "男"));
		map.put("bbb", new Temp("name2", "男"));
		map.put("ccc", new Temp("name3", "男"));
		map.put("ddd", new Temp("name4", "男"));
		map.put("eee", new Temp("name5", "男"));
		return map.get(key);
	}


}
class Temp{
	private String name;
	private String sex;

	public Temp(String name, String sex) {
		this.name = name;
		this.sex = sex;
	}

	public Temp() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Temp{" +
				"name='" + name + '\'' +
				", sex='" + sex + '\'' +
				'}';
	}
}