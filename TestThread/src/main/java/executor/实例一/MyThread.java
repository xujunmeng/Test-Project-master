package executor.实例一;
/**
@author junmeng.xu
@date  2016年2月19日下午4:07:20
 */
public class MyThread extends Thread {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " 正在执行... ");
		
	}
	
}
