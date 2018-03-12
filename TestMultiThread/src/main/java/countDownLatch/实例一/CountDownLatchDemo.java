package countDownLatch.实例一;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
@author junmeng.xu
@date  2016年2月19日下午3:31:07
 */
public class CountDownLatchDemo {

	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void main(String[] args) throws InterruptedException {
		
		CountDownLatch latch = new CountDownLatch(3);
		Worker worker1 = new Worker("zhang san", 5000, latch);
		Worker worker2 = new Worker("li si", 8000, latch);
		Worker worker3 = new Worker("wang wu", 8000, latch);
		worker1.start();
		worker2.start();
		worker3.start();
		latch.await();
		System.out.println(" all work done at : " + sdf.format(new Date()));
		
	}
	
	
	
}
