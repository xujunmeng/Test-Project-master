package pair;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

/**
@author junmeng.xu
@date  2016年5月4日下午1:59:15
 */
public class Main {

	@Test
	public void test1(){
		
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		int size = list.size();
		
		Pair<Integer, List<String>> pair = new Pair<>(size, list);
		Integer value0 = pair.getValue0();
		List<String> value1 = pair.getValue1();
		System.out.println(value0);
		System.out.println(value1);
		
	}
	
	
}
