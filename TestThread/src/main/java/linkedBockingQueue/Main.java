package linkedBockingQueue;

import org.junit.Test;


/**
@author junmeng.xu
@date  2016年8月19日上午10:07:04
 */
public class Main {

	public static void main(String[] args) {
		
		//建立一个装苹果的篮子
		Basket basket = new Basket();
		
		Producer producer = new Producer("生产者001", basket);
		Producer producer2 = new Producer("生产者002", basket);
		Consumer consumer = new Consumer("消费者001", basket);
		Consumer consumer2 = new Consumer("消费者002", basket);
		
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(producer2);
		Thread t3 = new Thread(consumer);
		Thread t4 = new Thread(consumer2);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
//		service.submit(consumer2);
		
	}
}
