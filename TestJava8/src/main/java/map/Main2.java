package map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
@author junmeng.xu
@date  2016年5月3日下午6:14:55
 */
public class Main2 {
    private List<String> stringList = new ArrayList<>();

    public Main2() {
        init();
    }

    private void init() {
        initStringList();
    }

    /**
     * 初始化字符串列表
     */
    private void initStringList() {
        stringList.add("zzz1");
        stringList.add("aaa2");
        stringList.add("bbb2");
        stringList.add("fff1");
        stringList.add("fff2");
        stringList.add("aaa1");
        stringList.add("bbb1");
        stringList.add("zzz2");
    }
    
    @Test
    public void useStreamFilter(){
    	stringList.stream().filter((a) -> a.startsWith("a")).forEach((b) -> System.out.println(b));
//    	stringList.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);
    }
    
    @Test
    public void useStreamSort(){
    	Object[] array = stringList.stream().sorted().toArray();
    	for (Object object : array) {
			System.out.println(object);
		}
    	
    	stringList.stream().sorted().filter((a) -> a.startsWith("a")).forEach((b) -> System.out.println(b));
    	
    }
    
    @Test
    public void useStreamMap(){
    	//加toArray()會有排序功能
    	Object[] array = stringList.stream().map((a) -> a.toUpperCase()).sorted().toArray();
    	for (Object object : array) {
			System.out.println(object);
		}
    	System.out.println("==============");
    	stringList.stream().map((a) -> a.toUpperCase()).sorted().forEach((c) -> System.out.println(c));
    	System.out.println("==============");
    	
    	List<String> list = new ArrayList<String>();
    	Object[] array2 = stringList.stream().map((a) -> list.add(a)).toArray();
    	System.out.println(list);
    	System.out.println("============");
    	List<Junmeng> persons = new ArrayList<Junmeng>();
    	stringList.stream().map((a) -> persons.add(new Junmeng(a))).toArray();
    	System.out.println(persons);
    	System.out.println("============");
    	List<Junmeng> person2 = new ArrayList<Junmeng>();
    	stringList.stream().map((a) -> person2.add(new Junmeng(a))).collect(Collectors.toList());
    	System.out.println(persons);
    	
    	
    	
    }
    
    
    @Test
    public void useStreamMatch(){
    	boolean anyMatch = stringList.stream().anyMatch((a) -> a.startsWith("a"));
    	System.out.println(anyMatch);
    	
    	
        // boolean anyMatch(Predicate<? super T> predicate);参数为Predicate函数式接口
        // 解释:集合中是否有任一元素匹配以'a'开头
        boolean anyStartsWithA = stringList.stream().anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyStartsWithA);

        // boolean allMatch(Predicate<? super T> predicate);
        // 解释:集合中是否所有元素匹配以'a'开头
        boolean allStartsWithA = stringList.stream().allMatch((s) -> s.startsWith("a"));
        System.out.println(allStartsWithA);

        // boolean noneMatch(Predicate<? super T> predicate);
        // 解释:集合中是否没有元素匹配以'd'开头
        boolean nonStartsWithD = stringList.stream().noneMatch((s) -> s.startsWith("d"));
        System.out.println(nonStartsWithD);
    }
    
    @Test
    public void useStreamReduce(){
    	Optional<String> reduce = stringList.stream().sorted().reduce((a1, a2) -> a1 + "#" + a2);
    	reduce.ifPresent((a) -> System.out.println(a));
    	System.out.println(reduce.get());
    	
    	List<Integer> list = new ArrayList<Integer>();
    	list.add(2);
    	list.add(4);
    	list.add(3);
    	list.add(5);
    	Optional<Integer> reduce2 = list.stream().sorted().reduce((a1, a2) -> a1 > a2 ? a1 : a2);
    	reduce2.ifPresent((a) -> System.out.println(a));
    	
    }
    
    class Junmeng{
    	private String name;
    	public Junmeng(String name) {
    		this.name = name;
    	}
    	public String getName() {
    		return name;
    	}
    	public void setName(String name) {
    		this.name = name;
    	}
    	@Override
    	public String toString() {
    		return "Junmeng [name=" + name + "]";
    	}
    }
}

