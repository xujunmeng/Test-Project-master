package countDownLatch.实例二;

import java.util.concurrent.CountDownLatch;

/**
@author junmeng.xu
@date  2016年8月12日下午5:53:01
 */
public class Test {

	public static void main(String[] args) {
		
		final CountDownLatch latch = new CountDownLatch(2);
		
		new Thread(){
			public void run() {
				try {
					System.out.println("子线程1 : " + Thread.currentThread().getName() + " 正在执行 ");
					Thread.sleep(5000);
					System.out.println("子线程1 : " + Thread.currentThread().getName() + " 执行完毕 ");
					latch.countDown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
		
		new Thread(){
			public void run() {
				try {
					System.out.println("子线程2 : " + Thread.currentThread().getName() + " 正在执行 ");
					Thread.sleep(5000);
					System.out.println("子线程2 : " + Thread.currentThread().getName() + " 执行完毕 ");
					latch.countDown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
	
		try {
			System.out.println("等待两个子线程执行完毕Z...");
			latch.await();
			System.out.println("两个子线程已经执行完毕");
			System.out.println("继续执行主线程");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
