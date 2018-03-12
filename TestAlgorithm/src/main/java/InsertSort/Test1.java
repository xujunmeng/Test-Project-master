package InsertSort;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

/**
@author junmeng.xu
@date  2016年7月7日下午1:23:58
 */
public class Test1 {

	public void insertSort(Object[] arr){
		long begin = System.currentTimeMillis();
		int count = arr.length;
		int in;
		int out;
		for(out = 1; out < count; out++){
			int temp = (Integer)arr[out];
			in = out;
			while(in > 0 && (Integer)arr[in-1] >= temp){
				arr[in] = arr[in-1];
				--in;
			}
			arr[in] = temp;
		}
		long end = System.currentTimeMillis();
		System.out.println(end-begin);
	}
	public static void listSort(List<Long> list){
		long begin = System.currentTimeMillis();
		list.sort((a, b) -> a.compareTo(b));
		long end = System.currentTimeMillis();
		System.out.println(end-begin);
	}
	//数量500时，耗时3
	@Test
	public void test1(){
		List<Integer> list = Lists.newArrayList();
		java.util.Random r=new java.util.Random();
		for(int i=0;i<500;i++){
			list.add((Integer)r.nextInt());
		}
		insertSort(list.toArray());
	}
	//数量500时，耗时219
	@Test
	public void test2(){
		List<Long> list = Lists.newArrayList();
		java.util.Random r=new java.util.Random();
		for(int i=0;i<500;i++){
			list.add((long)r.nextInt());
		}
		listSort(list);
	}
	//数量3000时，耗时16
	@Test
	public void test3(){
		List<Integer> list = Lists.newArrayList();
		java.util.Random r=new java.util.Random();
		for(int i=0;i<3000;i++){
			list.add((Integer)r.nextInt());
		}
		insertSort(list.toArray());
	}
	//数量3000时，耗时80
	@Test
	public void test4(){
		List<Long> list = Lists.newArrayList();
		java.util.Random r=new java.util.Random();
		for(int i=0;i<3000;i++){
			list.add((long)r.nextInt());
		}
		listSort(list);
	}
	//数量10000时，耗时121
	@Test
	public void test5(){
		List<Integer> list = Lists.newArrayList();
		java.util.Random r=new java.util.Random();
		for(int i=0;i<10000;i++){
			list.add((Integer)r.nextInt());
		}
		insertSort(list.toArray());
	}
	//数量10000时，耗时89
	@Test
	public void test6(){
		List<Long> list = Lists.newArrayList();
		java.util.Random r=new java.util.Random();
		for(int i=0;i<10000;i++){
			list.add((long)r.nextInt());
		}
		listSort(list);
	}
}
