package InsertSort;

import org.junit.Test;

/**
@author junmeng.xu
@date  2016年7月7日上午11:23:57
 */
public class Main {

	@Test
	public void test(){
		Integer[] arr = new Integer[]{23,5,54,2,5,234};
		int count = arr.length;
		int inner;
		int outer;
		for(outer = 1; outer < count; outer++){
			int temp = arr[outer];
			inner = outer;
			while(inner > 0 && arr[inner-1] >= temp){
				arr[inner] = arr[inner-1];
				--inner;
			}
			arr[inner] = temp;
		}
		for (Integer obj : arr) {
			System.out.println(obj);
		}
	}
	
}
