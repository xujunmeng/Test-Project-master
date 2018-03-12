package SelectSort;

import org.junit.Test;

/**
@author junmeng.xu
@date  2016年5月16日上午10:22:49
 */
public class Main {

	@Test
	public void test(){
		int arr[] = {75, 6, 23};
		int count = arr.length;
		int outer;
		int inner;
		int min;
		for(outer = 0 ; outer < count -1 ; outer++){
			min = outer;
			for(inner = outer + 1 ; inner < count ; inner++){
				if(arr[inner] < arr[min]){
					min = inner;
				}
			}
			int temp = arr[outer];
			arr[outer] = arr[min];
			arr[min] = temp;
		}
		for (int i : arr) {
			System.out.println(i);
		}
	}
	
}
