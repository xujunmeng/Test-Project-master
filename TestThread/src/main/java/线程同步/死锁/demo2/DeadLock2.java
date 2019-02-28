package 线程同步.死锁.demo2;
/**
@author junmeng.xu
@date  2016年5月19日上午9:52:32
 */
class A2{
	public synchronized void aaa(B2 b){
		System.out.println(Thread.currentThread().getName() + "----A2.aaa");
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		b.last();
	}
	public synchronized void last(){
		System.out.println("A2.last");
	}
	
}
class B2{
	public synchronized void bbb(A2 a){
		System.out.println(Thread.currentThread().getName() + "----B2.aaa");
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		a.last();
	}
	public synchronized void last(){
		System.out.println("B2.last");
	}
}
public class DeadLock2 implements Runnable {

	A2 a = new A2();
	B2 b = new B2();
	public void run() {
		a.aaa(b);
	}
	public void init(){
		b.bbb(a);
	}
	public static void main(String[] args) {
		
		DeadLock2 d = new DeadLock2();
		Thread t = new Thread(d);
		t.start();
		d.init();
	}
	
	
}
