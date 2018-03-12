package Lambda表达式;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/**
@author junmeng.xu
@date  2016年4月22日上午10:06:26
 */
public class Main {

	public static void main(String[] args) {
		
		//老版本
		List<String> names = Arrays.asList("pter", "anna", "mike", "xenia");
		System.out.println(names);
		Collections.sort(names, new Comparator<String>() {

			@Override
			public int compare(String a, String b) {
				return b.compareTo(a);
			}
		});
		System.out.println(names);
	}
	@Test
	public void test1(){
		List<String> names = Arrays.asList("pter", "anna", "mike", "xenia");
		Collections.sort(names, (String a, String b) -> {
			return b.compareTo(a);
		});
		System.out.println(names);
	}
	@Test
	public void test2(){
		List<String> names = Arrays.asList("pter", "anna", "mike", "xenia");
		Collections.sort(names, (String a, String b) -> b.compareTo(a));
		System.out.println(names);
	}
	@Test
	public void test3(){
		List<String> names = Arrays.asList("pter", "anna", "mike", "xenia");
		Collections.sort(names, (a, b) -> b.compareTo(a));
		System.out.println(names);
	}
	
}
