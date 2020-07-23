package completionService;

import java.util.Random;
import java.util.concurrent.*;

/**
@author junmeng.xu
@date  2016年4月3日下午3:57:53
 */
public class Main {

	public static void main(String[] args) {
		
		ExecutorService exec = Executors.newFixedThreadPool(10);
		//容量为10的阻塞队列
		BlockingQueue<Future<Integer>> queue = new LinkedBlockingDeque<>(100);
		//实例化CompletionService
		CompletionService<Integer> completionService = new ExecutorCompletionService<>(exec, queue);

		/**
		 * 模拟瞬间产生100个任务，且每个任务执行时间不一致
		 */
		for(int i = 0 ; i < 100 ; i++){
			completionService.submit(() -> {
				try {
					int ran = new Random().nextInt(100);
					Thread.sleep(ran);
					System.out.println(Thread.currentThread().getName() + " 休息了 " + ran);
					int aaa = 0/0;
					return ran;
				} catch (Exception e) {
					throw new RuntimeException("模拟运行时异常");
				}
            });
		}

		/**
		 * 立即数据结果
		 */
		for(int i = 0 ; i < 100 ; i++){
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
