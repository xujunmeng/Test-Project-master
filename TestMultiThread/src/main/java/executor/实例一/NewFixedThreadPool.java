package executor.实例一;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
@author junmeng.xu
@date  2016年2月19日下午4:06:02
 */
public class NewFixedThreadPool {

	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newCachedThreadPool();
		//创建线程
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		Thread t6 = new MyThread();
		Thread t7 = new MyThread();
		Thread t8 = new MyThread();
		//将线程放入池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		pool.execute(t6);
		pool.execute(t7);
		pool.execute(t8);
		pool.shutdown();
	}
	
}
