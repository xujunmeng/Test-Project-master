package ThreadLocal类.实例四;

import ThreadLocal类.实例三.Account;
import ThreadLocal类.实例三.MyTest;

/**
@author junmeng.xu
@date  2016年5月19日下午3:59:02
 */


public class ThreadLocalTest {

	public static void main(String[] args) {
		
		//启动两条线程，两条线程共享同一个Account
		Account account = new Account("初始名");
		/**
		 * 虽然两个线程共享同一个账户，即只有一个账户名，
		 * 但由于账户名是 ThreadLocal类型的，所以每个线程都完全拥有各自的账户名副本，
		 * 因此在i==6之后，
		 * 将看到两个线程访问同一个账户时出现不同的账户名
		 */
		new MyTest(account, "线程甲").start();
		new MyTest(account, "线程乙").start();

	}
	
}
