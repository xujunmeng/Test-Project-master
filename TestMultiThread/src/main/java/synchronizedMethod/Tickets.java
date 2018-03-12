package synchronizedMethod;

import java.util.concurrent.Callable;

/**
@author junmeng.xu
@date  2016年3月23日上午11:18:19
 */
public class Tickets implements Callable<Integer>  {
	public static Object lock = new Object();
	private static byte[] lock2 = new byte[0];
	public static int total = 100;
	public Integer call() throws Exception {
		synchronized (lock2) {
			while(true){
				if(total <= 1){
					break;
				}
				System.out.println(Thread.currentThread().getName() + "==卖票之前"+total);  
				total--;  
				System.out.println(Thread.currentThread().getName() + "==卖票之后"+total); 
			}
		}
		return 0;
	}
	
}
