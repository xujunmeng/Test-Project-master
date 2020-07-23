package executorsubmit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
@author junmeng.xu
@date  2016年2月22日下午5:02:21
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService pool = Executors.newFixedThreadPool(3);
		//execute(Runnable x)没有返回值。可以执行任务，但无法判断任务是否成功完成
		//pool.execute(new RunnableTest("Task1"));
		List<Callable<String>> calls = new ArrayList<Callable<String>>();
		for(int i = 0; i < 10; i++) {
			final int index = i;
			calls.add(() -> String.valueOf(index));
		}
		List<Future<String>> futures = pool.invokeAll(calls);
		for(Future<String> future : futures) {
			System.out.println(future.get());
		}
//		Future<?> future = pool.submit(new RunnableTest("Task2"));
//		if(future.get() == null){
//			System.out.println("任务完成");
//		}
		pool.shutdown();
		
	}
}
