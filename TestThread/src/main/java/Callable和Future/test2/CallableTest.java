package Callable和Future.test2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
@author junmeng.xu
@date  2016年9月22日下午6:05:33
 */
public class CallableTest {

	static ExecutorService executor = Executors.newFixedThreadPool(20);
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 20; i++) {
			System.out.println(Thread.currentThread().getName() + " 的循环变量i的值 :  " + i);
			if(i == 10){
				method1();
			}
			if(i == 15){
				method2();
			}
			
		}
		
		
		
		
		
		

		
		
	}
	
	private static void method1() {
		
		FutureTask<Integer> task = new FutureTask<>(() -> {
			
			int i = 0;
			for( ; i < 10 ; i++){
				System.out.println(Thread.currentThread().getName() + " 的循环变量i的值 :  " + i);
			}
			return i;
			
		});
		executor.execute(task);
		
		Integer integer = null;
		try {
			integer = task.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(integer);
		
	}
	
	private static void method2() {
		
		FutureTask<Integer> task = new FutureTask<>(() -> {
			
			int i = 0;
			for( ; i < 10 ; i++){
				System.out.println(Thread.currentThread().getName() + " 的循环变量i的值 :  " + i);
			}
			return i;
			
		});
		executor.execute(task);
		
		Integer integer = null;
		try {
			integer = task.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(integer);
		
	}
	
}

