package map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
@author junmeng.xu
@date  2016年4月22日下午1:16:00
 */
public class Main {

	public static void main(String[] args) {
		
		Map<Integer, String> map = new HashMap<>();
		for(int i = 0 ; i < 19 ; i++){
			map.putIfAbsent(i, "val" + i);
		}
		Collection<String> values = map.values();
		Stream<String> stream = values.stream();
		stream.filter(new Predicate<String>() {
			@Override
			public boolean test(String t) {
				System.out.println(t);
				return true;
			}
		});
	}
	@Test
	public void test(){
		Person p = new Person();
		p.setName("zs");
		p.setAge(1);
		Person p2 = new Person();
		p2.setName("zs2");
		p2.setAge(2);
		Person p3 = new Person();
		p3.setName("zs3");
		p3.setAge(3);
		Person p4 = new Person();
		p4.setName("zs4");
		p4.setAge(4);
		List<Person> list = new ArrayList<Person>();
		list.add(p);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		Stream<Integer> map = list.stream().map(new Function<Person, Integer>() {
			@Override
			public Integer apply(Person t) {
				return t.getAge();
			}
		});
		List<Object> collect = map.collect(Collectors.toList());
		System.out.println(collect);
	}
	
	@Test
	public void test2(){
		Person p = new Person();
		p.setName("zs");
		p.setAge(1);
		Person p2 = new Person();
		p2.setName("zs2");
		p2.setAge(2);
		Person p3 = new Person();
		p3.setName("zs3");
		p3.setAge(3);
		Person p4 = new Person();
		p4.setName("zs4");
		p4.setAge(4);
		List<Person> list = new ArrayList<Person>();
		list.add(p);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		Stream<Integer> map = list.stream().map(new Function<Person, Integer>() {
			@Override
			public Integer apply(Person t) {
				return t.getAge();
			}
		});
		List<Object> collect = map.collect(Collectors.toList());
		System.out.println(collect);
	}
	
	@Test
	public void testmap(){
		String str = "aa";
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "aa");
		map.put("b", "bb");
		map.put("c", "cc");
		Map<String, String> filterValues = Maps.filterValues(map, v -> v.compareTo(str) == 0);
		filterValues.forEach((k, v) -> System.out.println(k + ":" + v));
		
	}
	//Maps.filterValues
	@Test
	public void testMap2(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "aa");
		map.put("b", "bb");
		map.put("c", "cc");
		Map<String, String> filterValues = Maps.filterValues(map, new com.google.common.base.Predicate<String>() {
			@Override
			public boolean apply(String input) {
				if(input == "bb")
					return false;
				return true;
			}
		});
		filterValues.forEach(new BiConsumer<String, String>() {
			@Override
			public void accept(String t, String u) {
				System.out.println(t + " : " + u);
			}
		});
	}
	@Test
	public void testToMap(){
		List<String> list = new ArrayList<String>();
		list.add("111");
		list.add("222");
		
		ImmutableMap<String,String> map = Maps.toMap(Sets.newHashSet(list), new com.google.common.base.Function<String, String>() {
			@Override
			public String apply(String input) {
				System.out.println("=====" + input);
				return "xjm";
			}
		});
		map.forEach(new BiConsumer<String, String>() {
			@Override
			public void accept(String t, String u) {
				System.out.println(t + " : " + u);
			}
		});
	}
	@Test
	public void testaa(){
		List<String> list = new ArrayList<String>();
		list.add("111");
		list.add("222");
		ImmutableMap<String,Double> map = Maps.toMap(Sets.newHashSet(list), v -> 0d);
		map.forEach((k, v) -> System.out.println(k + " : " + v));
	}
	
	@Test
	public void test3232(){
		Map<String, Set<String>> parentMetircs = new HashMap<String, Set<String>>();
		Set<String> set = Sets.newHashSet("M011004", "M011003", "M011006", "M011005", "M011008", "M011007", "M011002");
		parentMetircs.put("M011", set);
		
		Map<String, Integer> metricsRank = new HashMap<String, Integer>();
		metricsRank.put("M009003", 0);
		
		parentMetircs.forEach((p, factors) -> {
			ImmutableMap<String,Boolean> map = Maps.toMap(factors, v -> metricsRank.getOrDefault(v, 1) == 1);
			map.forEach((k, v) -> System.out.println(k + " : " + v));
		});
		
	}
	
	
	
	
	
	
	//Map.getOrDefault(Object, V)
	@Test
	public void testoo(){
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("aaa", 2d);
		map.put("bbb", 3d);
		// JDK8之前的实现方法
		Double d = map.get("aaaa");
		if(d == null){
			d = 23d;
			System.out.println(d);
		}
		// JDK8的实现方法
		Double orDefault = map.getOrDefault("aaaa", 232323d);
		System.out.println(orDefault);
	}
	
	@Test
	public void testtrans(){
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("aaa", 2d);
		map.put("bbb", 3d);
		Map<String, Double> transformEntries = Maps.transformEntries(map, new Maps.EntryTransformer<String, Double, Double>() {

			@Override
			public Double transformEntry(String key, Double value) {
				System.out.println("=========1" + key + " : " + value);
				return 2323232323d;
			}
		});
		for(Map.Entry<String, Double> entry : transformEntries.entrySet()){
			System.out.println("=========2" + entry.getKey() + " : " + entry.getValue());
		}
	}
	
	@Test
	public void testtrans4(){
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("aaa", 2d);
		map.put("bbb", 3d);
		Map<String, String> transformEntries = Maps.transformEntries(map,new Maps.EntryTransformer<String, Double, String>() {
			@Override
			public String transformEntry(String key, Double value) {
				System.out.println("=========1" + key + " : " + value);
				return "dddd";
			}
		});
		for(Entry<String, String> entry : transformEntries.entrySet()){
			System.out.println("=========2" + entry.getKey() + " : " + entry.getValue());
		}
	}
	
	@Test
	public void testtrans2(){
		Map<String, Double> map = new HashMap<String, Double>();
		Map<String, Double> map2 = new HashMap<String, Double>();
		map.put("aaa", 2d);
		map.put("bbb", 3d);
		Map<String, Double> transformEntries = Maps.transformEntries(map,(k, v) -> map2.getOrDefault(k, 3344d) * v);
		for(Map.Entry<String, Double> entry : transformEntries.entrySet()){
			System.out.println("=========2" + entry.getKey() + " : " + entry.getValue());
		}
		
	}

	
	@Test
	public void flatMap(){
		List<Student> students = new ArrayList<Student>();
		Student s1 = new Student();
		s1.setName("aa");
		List<String> s1List = new ArrayList<String>();
		s1List.add("11");
		s1List.add("22");
		s1List.add("33");
		s1List.add("44");
		s1.setTeacheId(s1List);
		students.add(s1);
		Stream<List<String>> map = students.stream().map(Student::getTeacheId);
		Stream<String> flatMap = map.flatMap(Collection::stream);
		Set<String> collect = flatMap.collect(Collectors.toSet());
		Collection<String> a = (Collection<String>) collect;
		System.out.println(a);
		
		Set<String> collect2 = students.stream().map(Student::getTeacheId).flatMap(Collection::stream).collect(Collectors.toSet());
		System.out.println(collect2);
		
	}
	@Test
	public void flatMap2(){
		List<Student> students = new ArrayList<Student>();
		Student s1 = new Student();
		s1.setName("aa");
		List<String> s1List = new ArrayList<String>();
		s1List.add("11");
		s1List.add("22");
		s1List.add("33");
		s1List.add("44");
		s1.setTeacheId(s1List);
		students.add(s1);
		Stream<List<String>> map = students.stream().map(Student::getTeacheId);
		Stream<String> flatMap = map.flatMap(Collection::stream);
		Set<String> collect = flatMap.collect(Collectors.toSet());
		Collection<String> a = (Collection<String>) collect;
		System.out.println(a);
		
		Set<String> collect2 = students.stream().map(Student::getTeacheId).flatMap(Collection::stream).collect(Collectors.toSet());
		System.out.println(collect2);
		
	}
	
	
	@Test
	public void testgetKeysAndValues(){
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("aaa", 2d);
		map.put("bbb", 3d);
		Collection<Double> values = map.values();
		System.out.println(values);
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
class Student{
	private String name;
	private List<String> teacheId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getTeacheId() {
		return teacheId;
	}
	public void setTeacheId(List<String> teacheId) {
		this.teacheId = teacheId;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", teacheId=" + teacheId + "]";
	}
	
}
class Person{
	private String name;
	private Integer age;
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
    /**
     * 准确精度的加法计算规则：null consider to BigDecimal.ZERO
     *
     * @param array values to be added
     * @return sum(array elements)
     */
    public BigDecimal sum(Collection<? extends Number> array) {
        if (array == null || array.isEmpty())
            return BigDecimal.ZERO;
        BigDecimal sum = BigDecimal.ZERO;
        for (Number bd : array) {
            if (bd != null) {
                if (bd instanceof Double
                        && (((Double) bd).isNaN() || ((Double) bd).isInfinite())) {
                    continue;
                }
                sum = sum.add(new BigDecimal(bd.doubleValue()));
            }
        }
        return sum.setScale(8, RoundingMode.HALF_UP);
    }
	
}