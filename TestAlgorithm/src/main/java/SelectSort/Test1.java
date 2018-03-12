package SelectSort;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

/**
@author junmeng.xu
@date  2016年7月7日上午9:53:39
 */
public class Test1 {

	@Test
	public void test1(){
		int arr[] = {75, 6, 23};
		int count = arr.length;
		int outer;
		int inner;
		int min = 0;
		for(outer = 0 ; outer < count - 1 ; outer++){
			for(inner = outer + 1 ; inner < count; inner++){
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

	@Test
	public void test9() {
		int[] arr = {75, 6, 23};
		int count = arr.length;
		int outer;
		int inner;
		int min;
		for (outer = 0; outer < count - 1; outer++) {
			min = outer;
			for (inner = outer + 1; inner < count; inner++) {
				if (arr[inner] < arr[min]) {
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

	@Test
	public void test10() {
		int[] arr = {93, 23, 7 ,234, 34, 34, 5, 34, 763, 2};
		int count = arr.length;
		int outer;
		int inner;
		int min;
		for (outer = 0; outer < count - 1; outer++) {
			min = outer;
			for (inner = outer + 1; inner < count; inner++) {
				if (arr[inner] < arr[min]) {
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

	@Test
	public void test11() {
		int[] arr = {93, 23, 7 ,234, 34, 34, 5, 34, 763, 2};
		int count = arr.length;
		int outer;
		int inner;
		int min;
		for (outer = 0; outer < count - 1; outer++) {
			min = outer;
			for (inner = outer + 1; inner < count; inner++) {
				if (arr[inner] < arr[min]) {
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











	








	@Test
	public void test2(){
		Integer arr[] = new Integer[]{23,4,6723,5,324,76,8,9,54,23,324,56,68,9,5,3,2,54,67,12345};
		int count = arr.length;
		int out;
		int in;
		int min;
		for (out = 0; out < count; out++) {
			min = out;
			for (in = out + 1; in < count; in++) {
				if(arr[in] < arr[min]){
					min = in;
				}
			}
			int temp = arr[out];
			arr[out] = arr[min];
			arr[min] = temp;
		}
		for (Integer obj : arr) {
			System.out.println(obj);
		}
	}
	
	public void selectSort(Object[] arr){
		long begin = System.currentTimeMillis();
		int count = arr.length;
		int out;
		int in;
		int min;
		for (out = 0; out < count; out++) {
			min = out;
			for (in = out + 1; in < count; in++) {
				if((Integer)arr[in] < (Integer)arr[min]){
					min = in;
				}
			}
			int temp = (Integer)arr[out];
			arr[out] = arr[min];
			arr[min] = temp;
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}
	public static void listSort(List<Long> list){
		long begin = System.currentTimeMillis();
		list.sort((a, b) -> a.compareTo(b));
		long end = System.currentTimeMillis();
		System.out.println(end-begin);
	}
	//数量100时，耗时0或1
	@Test
	public void test3(){
		List<Integer> list = Lists.newArrayList();
		java.util.Random r=new java.util.Random();
		for(int i=0;i<100;i++){
			list.add((Integer)r.nextInt());
		}
		selectSort(list.toArray());
	}
	//数量100时，耗时361
	@Test
	public void test4(){
		List<Long> list = Lists.newArrayList();
		java.util.Random r=new java.util.Random();
		for(int i=0;i<100;i++){
			list.add((long)r.nextInt());
		}
		listSort(list);
	}
	//数量3000时，耗时20
	@Test
	public void test5(){
		List<Integer> list = Lists.newArrayList();
		java.util.Random r=new java.util.Random();
		for(int i=0;i<3000;i++){
			list.add((Integer)r.nextInt());
		}
		selectSort(list.toArray());
	}
	//数量3000时，耗时80
	@Test
	public void test6(){
		List<Long> list = Lists.newArrayList();
		java.util.Random r=new java.util.Random();
		for(int i=0;i<3000;i++){
			list.add((long)r.nextInt());
		}
		listSort(list);
	}
	//数量10000时，耗时117
	@Test
	public void test7(){
		List<Integer> list = Lists.newArrayList();
		java.util.Random r=new java.util.Random();
		for(int i=0;i<10000;i++){
			list.add((Integer)r.nextInt());
		}
		selectSort(list.toArray());
	}
	//数量10000时，耗时59
	@Test
	public void test8(){
		List<Long> list = Lists.newArrayList();
		java.util.Random r=new java.util.Random();
		for(int i=0;i<10000;i++){
			list.add((long)r.nextInt());
		}
		listSort(list);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
