package ConcurrentLinkedDeque;
/**
@author junmeng.xu
@date  2016年8月19日上午10:21:13
 */

/**
 * 定义苹果生产者
 */
public class Producer implements Runnable {

	private String instance;
	private Basket basket;
	public Producer(String instance, Basket basket) {
		super();
		this.instance = instance;
		this.basket = basket;
	}
	
	public void run() {
		try {
			while(true){
				//生产苹果
				basket.produce();
				
				System.out.println("生产 : " + instance);
				//休眠300
//				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.out.println("Producer Interrupted");
		}
		
	}
	
}
