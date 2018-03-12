package 火车站买票;
/**
@author junmeng.xu
@date  2016年5月18日上午10:26:52
 */
/**
 * 多线程出现的问题
 * 1.多个线程有共享数据
 * 2.多个线程对共享数据有操作
 */
public class Main {

	public static void main(String[] args) {
		
		Ticket ticket = new Ticket();
		Thread t1 = new Thread(ticket);
		Thread t2 = new Thread(ticket);
		Thread t3 = new Thread(ticket);
		Thread t4 = new Thread(ticket);
		Thread t5 = new Thread(ticket);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
	
	
}
class Ticket implements Runnable{
	private int tickets = 100;
	public void run() {
		while(true){
			if(tickets > 0){
				System.out.println(Thread.currentThread().getName() + "......" + tickets--);
			}
		}
	}
}