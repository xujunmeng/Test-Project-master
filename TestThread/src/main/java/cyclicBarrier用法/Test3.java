package cyclicBarrier用法;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier是可以重用的，看下面这个例子
 * 从执行结果可以看出，在初次的4个线程越过barrier状态后，又可以用来进行新一轮的使用。而CountDownLatch无法进行重复使用。
 *
 *
@author junmeng.xu
@date  2016年8月15日上午9:53:52
 */
public class Test3 {

	public static void main(String[] args) {
		
		int N = 4;
		CyclicBarrier barrier = new CyclicBarrier(N);
		
		for(int i = 0 ; i < N ; i++){
			new Worker3(barrier).start();
		}
		
		try {
			Thread.sleep(25000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(" 线程 ：  " + Thread.currentThread().getName() + " CyclicBarrier重用... ");
		
		for(int i = 0 ; i < N ; i++){
			new Worker3(barrier).start();
		}
		
		System.out.println(" 最后的一次 : " + Thread.currentThread().getName());
		
	}
	
}

class Worker3 extends Thread{

	private CyclicBarrier cyclicBarrier;

	public Worker3(CyclicBarrier cyclicBarrier){
		this.cyclicBarrier = cyclicBarrier;

	}

	@Override
	public void run() {
		System.out.println(" 线程 : " + Thread.currentThread().getName() + " 正在写入数据 ... ");
		try {
			Thread.sleep(5000);
			System.out.println(" 线程 : " + Thread.currentThread().getName() + " 写入数据完毕，等待其他线程写入完毕... ");
			cyclicBarrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" 线程 : " + Thread.currentThread().getName() + " 所有线程写入完毕，继续处理其他任务... ");
	}
}