package BubbleSort;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;


/**
 * @author junmeng.xu
 * @date 2016年5月13日上午10:27:48
 */
public class Main {

	
	public static void bubbleSort(long[] arr){
		int count = arr.length;
		for(int outer = count - 1 ;outer > 1 ;outer --){
			for(int inner = 0 ; inner < count - 1 ;inner++){
				if(arr[inner] > arr[inner + 1]){
				      long temp = arr[inner];
				      arr[inner] = arr[inner+1];
				      arr[inner+1] = temp;
				}
			}
		}
	}
	public static void bubbleSort2(Object[] arr){
		long begin = System.currentTimeMillis();
		int count = arr.length;
		for(int outer = count - 1 ; outer > 1 ; outer --){
			for(int inner = 0 ; inner < count - 1 ; inner++){
				if((long)arr[inner] > (long)arr[inner + 1]){
					long temp = (long)arr[inner];
					arr[inner] = arr[inner+1];
					arr[inner+1] = temp;
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end-begin);
		for (Object object : arr) {
			System.out.print(object);
		}
	}
	public static void listSort3(List<Long> list){
		long begin = System.currentTimeMillis();
		list.sort((a, b) -> a.compareTo(b));
		long end = System.currentTimeMillis();
		System.out.println(end-begin);
		for (Long long1 : list) {
			System.out.print(long1);
		}
	}
	
	public static void main(String[] args) {
		long[] arr = {77,99,44,55,22,88,11,00,66,33};
		bubbleSort(arr);
		for (long l : arr) {
			System.out.println(l);
		}
	}
	
	@Test
	public void test1(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(77); 
		list.add(99);
		list.add(44);
		list.add(55);
		list.add(22);
		list.add(88);
		list.add(11);
		list.add(00);
		list.add(66);
		list.add(33);
		list.sort((a, b) -> a.compareTo(b));
		System.out.println(list);
	}
	//10000时，耗时494
	@Test
	public void test2(){
		List<Long> list = Lists.newArrayList();
		java.util.Random r=new java.util.Random();
		for(int i=0;i<10000;i++){
		    list.add((long)r.nextInt());
		}
		bubbleSort2(list.toArray());
	}
	//10000时，耗时65
	@Test
	public void test3(){
		List<Long> list = Lists.newArrayList();
		java.util.Random r=new java.util.Random();
		for(int i=0;i<10000;i++){
			list.add((long)r.nextInt());
		}
		listSort3(list);
	}
	//100时，耗时1或0
	@Test
	public void test4(){
		List<Long> list = Lists.newArrayList();
		java.util.Random r=new java.util.Random();
		for(int i=0;i<100;i++){
			list.add((long)r.nextInt());
		}
		bubbleSort2(list.toArray());
	}
	//100时，耗时57
	@Test
	public void test5(){
		List<Long> list = Lists.newArrayList();
		java.util.Random r=new java.util.Random();
		for(int i=0;i<100;i++){
			list.add((long)r.nextInt());
		}
		listSort3(list);
	}
	//3000时，耗时54
	@Test
	public void test6(){
		List<Long> list = Lists.newArrayList();
		java.util.Random r=new java.util.Random();
		for(int i=0;i<3000;i++){
			list.add((long)r.nextInt());
		}
		bubbleSort2(list.toArray());
	}
	//3000时，耗时56（不稳定50-70）
	@Test
	public void test7(){
		List<Long> list = Lists.newArrayList();
		java.util.Random r=new java.util.Random();
		for(int i=0;i<3000;i++){
			list.add((long)r.nextInt());
		}
		listSort3(list);
	}

}
