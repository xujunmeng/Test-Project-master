package cyclicBarrier用法;

import java.util.concurrent.CyclicBarrier;

/**
@author junmeng.xu
@date  2016年8月12日下午6:32:02
 */
public class Test {

	public static void main(String[] args) {
		
		int N = 4;
		
		CyclicBarrier barrier = new CyclicBarrier(N);
		
		for (int i = 0; i < N; i++) {
			new Worker(barrier).start();
		}
		
	}
	
}
class Worker extends Thread{
	
	private CyclicBarrier cyclicBarrier;
	
	public Worker(CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
	}
	
	@Override
	public void run() {
		System.out.println(" 线程 : " + Thread.currentThread().getName() + " 正在写入数据.... ");
		try {
			//Thread.sleep(5000);  //以睡眠来模拟写入数据操作
			System.out.println(" 线程 : " + Thread.currentThread().getName() + " 写入数据完毕 , 等待其他线程写入完毕 ");
			cyclicBarrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" 线程 :  " + Thread.currentThread().getName() + " 所有线程写入完毕 , 继续处理其他任务... ");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
