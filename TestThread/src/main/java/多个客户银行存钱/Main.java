package 多个客户银行存钱;
/**
@author junmeng.xu
@date  2016年5月18日上午10:51:41
 */
public class Main {

	public static void main(String[] args) {
		
		Consumer consumer = new Consumer();
		Thread t1 = new Thread(consumer);
		Thread t2 = new Thread(consumer);
		Thread t3 = new Thread(consumer);
		Thread t4 = new Thread(consumer);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
	
	
}
class Bank{
	private Integer sum = 0;
	Object obj = new Object();
	public void add(int num){
		synchronized (obj) {
			sum = sum + num;
			System.out.println("sum : " + sum);
		}
	}
	
}
class Consumer implements Runnable{
	Bank bank = new Bank();
	public void run() {
		for(int i = 1; i <= 3 ; i++){
			bank.add(100);
		}
		
	}
}