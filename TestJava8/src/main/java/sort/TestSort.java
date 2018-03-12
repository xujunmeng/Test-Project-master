package sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
@author junmeng.xu
@date  2016年3月16日下午5:57:38
 */
public class TestSort {

	public static void main(String[] args) {
		
		List<String> names = Arrays.asList("aaa" , "bbb" , "ccc");
		
		Collections.sort(names, new Comparator<String>() {

			public int compare(String a, String b) {
				return b.compareTo(a);
			}
		});
		
	}
	
}
