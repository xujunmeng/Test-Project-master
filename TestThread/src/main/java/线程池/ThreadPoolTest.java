package 线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
@author junmeng.xu
@date  2016年5月19日下午3:44:36
 */
class TestThread implements Runnable{
	
	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " 的i值为 :  " + i);
		}
		
	}
}
public class ThreadPoolTest {

	public static void main(String[] args) {
		
		//创建一个具有固定线程数 的线程池
		ExecutorService pool = Executors.newFixedThreadPool(6);
		//向线程池中提交两个线程
		pool.submit(new TestThread());
		pool.submit(new TestThread());
		//关闭线程池
		pool.shutdown();
		
	}
	
}
