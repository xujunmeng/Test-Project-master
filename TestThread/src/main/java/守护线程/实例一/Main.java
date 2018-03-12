package 守护线程.实例一;
/**
@author junmeng.xu
@date  2016年8月22日上午11:29:08
 */
public class Main {

	public static void main(String[] args) {
		
		Thread daemonThread = new Thread();
		
		//设置daemonThread为守护线程,default false (非守护线程)
		daemonThread.setDaemon(true);
		
		//验证当前线程是否为守护线程，返回true则为守护线程
		boolean daemon = daemonThread.isDaemon();
		
		System.out.println(daemon);
		
	}
	
}
