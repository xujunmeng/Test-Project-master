package completionService;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;

/**
@author junmeng.xu
@date  2016年4月3日下午3:57:53
 */
public class Main {

	public static void main(String[] args) {
		
		ExecutorService exec = Executors.newFixedThreadPool(20);
		//容量为10的阻塞队列
		BlockingQueue<Future<Integer>> queue = new LinkedBlockingDeque<Future<Integer>>(10);
		//实例化CompletionService
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(exec, queue);
		
		meth(exec, completionService);
		
		System.out.println("asdfasd");
		
	}

	private static void meth(ExecutorService exec,
			CompletionService<Integer> completionService) {
		/**
		 * 模拟瞬间产生10个任务，且每个任务执行时间不一致
		 */
		for(int i = 0 ; i < 10 ; i++){
			completionService.submit(new Callable<Integer>() {
				
				public Integer call() throws Exception {
					int ran = new Random().nextInt(100);
					Thread.sleep(ran);
					System.out.println(Thread.currentThread().getName() + " 休息了 " + ran);
					return ran;
				}
			});
		}
		
		/**
		 * 立即数据结果
		 */
		for(int i = 0 ; i < 10 ; i++){
			try {
				Future<Integer> f = completionService.take();
				System.out.println(f.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		exec.shutdown();
	}
	
	
}
