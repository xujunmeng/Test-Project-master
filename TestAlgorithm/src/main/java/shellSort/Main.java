package shellSort;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * @author junmeng.xu
 * @date 2016年7月14日下午1:22:34
 */
public class Main {

	public void shellSort(Object[] arr) {
		long begin = System.currentTimeMillis();
		int count = arr.length;
		int inner, outer;
		int temp;

		int h = 1; // find initial value of h
		while (h <= count / 3)
			h = h * 3 + 1; // (1, 4, 13, 40, 121, ...)

		while (h > 0) // decreasing h, until h=1
		{
			// h-sort the file
			for (outer = h; outer < count; outer++) {
				temp = (Integer) arr[outer];
				inner = outer;
				// one subpass (eg 0, 4, 8)
				while (inner > h - 1 && (Integer) arr[inner - h] >= temp) {
					arr[inner] = arr[inner - h];
					inner = inner - h;
				}
				arr[inner] = temp;
			} // end for
			h = (h - 1) / 3; // decrease h
		} // end while(h>0)
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}
	
	@Test
	public void test3(){
		List<Integer> list = Lists.newArrayList(34,56,12,4,6,7,4,3,56,7,4,23);
//		java.util.Random r=new java.util.Random();
//		for(int i=0;i<30;i++){ 
//			list.add((Integer)r.nextInt());
//		}
		shellSort(list.toArray());
	}
	
	
	
	
	
	
	
	
	
}
