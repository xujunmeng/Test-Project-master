package maps;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author junmeng.xu
 * @date 2016年4月20日下午6:08:38
 */
public class Main {

	public static final Integer haha = 1;
	/**
	 * 转换Map中的value值
	 */
	@Test
	public void testkj() {

		Map<String, Integer> personMap = Maps.newHashMap();

		personMap.put("A", 1);
		personMap.put("B", 1);
		personMap.put("C", 0);
		Map<String, Boolean> map = Maps.transformValues(personMap,
				haha::equals);
		System.out.println("=======================================");
		System.out.println("转换Map中的value值" + map);
	}

	/**
	 * 将map转为list
	 */
	@Test
	public void test22132(){
		Map<String, Integer> map = Maps.newHashMap();
		map.put("A", 1);
		map.put("B", 1);
		map.put("C", 0);

	}


	/**
	 * 转换Map中的value值
	 */
	@Test
	public void testksdfj() {
		Person p1 = new Person("001", "zhang_san");
		Person p2 = new Person("002", "li_si");
		Map<String, Person> personMap = Maps.newHashMap();

		personMap.put("A", p1);
		personMap.put("B", p2);
		Map<String, String> transformValues = Maps.transformValues(personMap,
				input -> input.getId());
		System.out.println("转换Map中的value值" + transformValues);
	}


	/**
	 * 	将主键当作Map的key
	 */
	@Test
	public void test222(){
		System.out.println("=============================================");
		Person p1 = new Person("001", "zhang_san");
		Person p2 = new Person("002", "li_si");
		List<Person> personList = Lists.newArrayList();
		personList.add(p1);
		personList.add(p2);
		// 将主键当作Map的key
		Map<String, Person> personMap2 = Maps.uniqueIndex(personList,
				input -> {
					return input.getName();
				});
		System.out.println("将name当作Map的key ： " + personMap2);
		System.out.println("=============================================");
	}

	/**
	 * 	将主键当作Map的key
	 */
	@Test
	public void test() {
		Person p1 = new Person("001", "zhang_san");
		Person p2 = new Person("002", "li_si");
		List<Person> personList = Lists.newArrayList();
		personList.add(p1);
		personList.add(p2);
		ImmutableMap<String, Person> immutableMap = Maps.uniqueIndex(
				personList, a -> {
					return a.getId();
				});
		System.out.println(immutableMap);
	}

	@Test
	public void test2() {
		Map<String, Map<String, Set<String>>> annMap = new ConcurrentHashMap<>();
		Map<String, Set<String>> map = new ConcurrentHashMap<String, Set<String>>();
		Set<String> set = new HashSet<String>();
		set.add("aaa");
		set.add("bbb");
		set.add("ccc");
	}

}
