package cyclicBarrier用法;

import java.util.concurrent.CyclicBarrier;

/**
 *
 * 从结果可以看出，当四个线程都到达barrier状态后，会从四个线程中选择一个线程去执行Runnable。
 *
@author junmeng.xu
@date  2016年8月15日上午9:33:11
 */
public class Test2 {

	public static void main(String[] args) {
		
		int N = 4;
		CyclicBarrier barrier = new CyclicBarrier(N, () ->
				System.out.println(" 最后的这个线程 : " + Thread.currentThread().getName()));
		
		for(int i = 0 ; i < N ; i++){
			new Worker2(barrier).start();
		}
		
	}
	
}

class Worker2 extends Thread{

	private CyclicBarrier cyclicBarrier;

	public Worker2(CyclicBarrier cyclicBarrier){
		this.cyclicBarrier = cyclicBarrier;
	}
	
	@Override
	public void run() {
		System.out.println(" 线程 : " + Thread.currentThread().getName() + " 正在写入数据... ");
		try {
			Thread.sleep(1000);
			System.out.println(" 线程 ： " + Thread.currentThread().getName() + " 写入数据完毕，等待其他线程写入完毕 ");
			cyclicBarrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" 线程 : " + Thread.currentThread().getName() + " 所有线程写入完毕，继续处理其他任务... ");
	}
	
	
}
