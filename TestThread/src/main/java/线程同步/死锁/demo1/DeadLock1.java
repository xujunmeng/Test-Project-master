package 线程同步.死锁.demo1;
/**
@author junmeng.xu
@date  2016年5月19日上午9:34:57
 */
class A1{
	public synchronized void aaa(B1 b){
		System.out.println(Thread.currentThread().getName() + "=====A1.aaa");
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		b.last();
	}
	public synchronized void last(){
		System.out.println("=====A1.last");
	}
}
class B1{
	public synchronized void bbb(A1 a){
		System.out.println(Thread.currentThread().getName() + "=====B1.bbb");
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		a.last();
		
	}
	public synchronized void last(){
		System.out.println("=====B1.last");
	}
}
public class DeadLock1 implements Runnable {

	A1 a1 = new A1();
	B1 b1 = new B1();
	public void run() {
		a1.aaa(b1);
	}
	public void init(){
		b1.bbb(a1);
	}
	public static void main(String[] args) {
		
		DeadLock1 d = new DeadLock1();
		Thread t = new Thread(d);
		t.start();
		d.init();
		
	}
	
}
