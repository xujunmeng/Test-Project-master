package SelectSort;

import org.junit.Test;

/**
@author junmeng.xu
@date  2016年5月16日上午10:22:49
 */
public class Main {

	@Test
	public void test1111() {
		long[] arr = {77,99,44,55,22,88,11,00,66,33};
		selectSort2222(arr);
		for (long a : arr) {
			System.out.println(a);
		}
	}

	public static void selectSort1111(long[] arr) {
		int in, out, min;
		for (out = 0; out < arr.length - 1; out++) {
			min = out;
			for (in = 0; in < arr.length; in++) {
				if (arr[in] < arr[min]) {
					min = in;
				}
			}
			long temp = arr[out];
			arr[out] = arr[min];
			arr[min] = temp;
		}


	}

	public static void selectSort2222(long[] arr) {
		int out, in ,min;
		for (out = 0; out < arr.length - 1; out++) {
			min = out;
			for (in = 0; in < arr.length; in++) {
				if (arr[in] < arr[min]) {
					min = in;
				}
			}
			long temp = arr[out];
			arr[out] = arr[min];
			arr[min] = temp;
		}
	}




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
