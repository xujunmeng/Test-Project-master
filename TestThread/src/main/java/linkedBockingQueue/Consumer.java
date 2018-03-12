package linkedBockingQueue;
/**
@author junmeng.xu
@date  2016年8月19日上午10:14:50
 */
/**
 * 定义苹果消费者
 */
public class Consumer implements Runnable {
	
	private String instance;
	private Basket basket;
	
	public Consumer(String instance, Basket basket) {
		this.instance = instance;
		this.basket = basket;
	}
	
	public void run() {
		try {
			while(true){
				//消费苹果
				basket.consume();
				System.out.println("消费 : " + instance);
				//休眠1000ms
//				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.out.println("Consumer Interrupted");
		}
		
	}
	
	

}
