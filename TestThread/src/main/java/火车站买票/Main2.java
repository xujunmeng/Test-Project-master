package 火车站买票;
/**
@author junmeng.xu
@date  2016年5月18日上午10:35:31
 */
/**
 * 用同步代码块解决多线程问题 
 *
 */
public class Main2 {

	public static void main(String[] args) {
		
		Ticket3 ticket3 = new Ticket3();
		Thread t1 = new Thread(ticket3);
		Thread t2 = new Thread(ticket3);
		Thread t3 = new Thread(ticket3);
		Thread t4 = new Thread(ticket3);
		Thread t5 = new Thread(ticket3);
		Thread t6 = new Thread(ticket3);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		
	}
	
}
class Ticket3 implements Runnable{
	private Integer ticket = 100;
	public void run() {
		while(true){
			method1();
		}
		
	}
	private synchronized void method1() {
		if(ticket > 0){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "......" + ticket--);
		}
	}
}
class Ticket2 implements Runnable{
	
	private Integer ticket = 100;
	Object obj = new Object();
	
	public void run() {
		while(true){
			synchronized (obj) {
				if(ticket > 0){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "......" + ticket--);
				}
			}
		}
		
	}
	
}
