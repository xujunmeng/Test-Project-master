package BubbleSort;

import org.junit.Test;

/**
@author junmeng.xu
@date  2016年5月16日上午9:29:04
 */
public class Test1 {

	@Test
	public void test(){
		Integer arr[] = {1,23,6};
		int count = arr.length;
		for(int outer = count - 1 ;outer > 1 ;outer --){
			for(int inner = 0 ;inner < count - 1 ;inner++){
				if(arr[inner] > arr[inner+1]){
					int temp = arr[inner];
					arr[inner] = arr[inner + 1];
					arr[inner + 1] = temp;
				}
			}
		}
		for (Integer integer : arr) {
			System.out.println(integer);
		}
	}
	
	@Test
	public void test2(){
		Integer arr[] = {23,23,6,76,23,43,6,7,23,5,456,8,23,3,4576,67,45,234,8,254,6,458,678,3456,3456};
		int count = arr.length;
		for(int outer = count - 1 ;outer > 1 ;outer --){
			for(int inner = 0 ;inner < count - 1 ;inner++){
				if(arr[inner] > arr[inner+1]){
					int temp = arr[inner];
					arr[inner] = arr[inner + 1];
					arr[inner + 1] = temp;
				}
			}
		}
		for (Integer integer : arr) {
			System.out.println(integer);
		}
	}
	
	@Test
	public void test3(){
		Integer[] arr = new Integer[]{75, 6, 23};
		int count = arr.length;
		for (int outer = count - 1; outer > 1; outer--) {
			for (int inner = 0; inner < count-1; inner++) {
				if(arr[inner] > arr[inner+1]){
					int temp = arr[inner];
					arr[inner] = arr[inner+1];
					arr[inner+1] = temp;
				}
			}
		}
		for (Integer obj : arr) {
			System.out.println(obj);
		}
	}
	
	
	
}
