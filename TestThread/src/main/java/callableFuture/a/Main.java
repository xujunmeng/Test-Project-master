package callableFuture.a;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
@author junmeng.xu
@date  2016年2月22日下午5:32:37
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService executor = Executors.newFixedThreadPool(20);
		Task1 task1 = new Task1();
		Task2 task2 = new Task2();
		Task3 task3 = new Task3();
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(task1);
		tasks.add(task2);
		tasks.add(task3);
		List<Future<Integer>> invokeAll = executor.invokeAll(tasks);
		for (Future<Integer> futures : invokeAll) {
			Integer integer = futures.get();
			System.out.println("运行结果" + integer);
		}
		executor.shutdown();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("主线程在执行任务");
		System.out.println("所有任务执行完毕");
		
	}
	
}
