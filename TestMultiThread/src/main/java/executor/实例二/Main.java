package executor.实例二;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author junmeng.xu
 * @date 2016年10月11日下午1:17:02
 */
public class Main {

	public static ExecutorService executor = Executors.newFixedThreadPool(100);

	private List<FutureTask<String>> map() {

		FutureTask<String> task1 = null;
		task1 = new FutureTask<String>(new Callable<String>() {
			public String call() throws Exception {
				System.out.println("线程的名字 : "
						+ Thread.currentThread().getName());
				return "2";
			}
		});
		executor.execute(task1);
		
		FutureTask<String> task2 = null;
		task2 = new FutureTask<String>(new Callable<String>() {
			public String call() throws Exception {
				System.out.println("线程的名字 : "
						+ Thread.currentThread().getName());
				return "4";
			}
		});
		executor.execute(task2);
		
		FutureTask<String> task3 = null;
		task3 = new FutureTask<String>(new Callable<String>() {
			public String call() throws Exception {
				System.out.println("线程的名字 : "
						+ Thread.currentThread().getName());
				return "6";
			}
		});
		executor.execute(task3);
		
		List<FutureTask<String>> futureList = new ArrayList<FutureTask<String>>();
		futureList.add(task1);
		futureList.add(task2);
		futureList.add(task3);
		
		
		return futureList;

	}

	
	public static void main(String[] args) throws Exception {
		
		List<FutureTask<String>> list = new Main().map();
		
		for (FutureTask<String> futureTask : list) {
			String data = futureTask.get();
			System.out.println("最后的结果 ： " + data);
		}
		
	}
	
	
}
