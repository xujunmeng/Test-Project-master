package InsertSort;

import org.junit.Test;

/**
@author junmeng.xu
@date  2016年7月7日上午11:23:57
 */
public class Main {

	@Test
	public void test(){
		int[] arr = new int[]{77,99,44,55,22,88,11,00,66,33};
		insertSort(arr);
	}

	public static void insertSort(int[] arr) {
		int count = arr.length;
		int in, out;
		for(out = 1; out < count; out++){
			int temp = arr[out];
			in = out;
			while(in > 0 && arr[in - 1] >= temp){
				arr[in] = arr[in - 1];
				--in;
			}
			arr[in] = temp;
		}
		for (Integer obj : arr) {
			System.out.println(obj);
		}
	}

}
