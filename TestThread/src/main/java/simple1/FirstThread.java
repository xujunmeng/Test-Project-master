package simple1;
/**
@author junmeng.xu
@date  2016年5月18日下午4:11:00
 */
public class FirstThread extends Thread {

	//重写run方法，run方法的方法体就是线程执行体
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(getName() + "   " + i);
		}
	}
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + "  " + i);
			if(i == 20){
				new FirstThread().start();
				new FirstThread().start();
			}
		}
		
	}
	
}
class SecondThread extends Thread{
	//重写run方法，run方法的方法体就是线程执行体
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(getName() + "   " + i);
		}
	}
}
