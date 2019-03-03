package 线程同步.银行取款1;
/**
@author junmeng.xu
@date  2016年5月18日下午4:53:58
 */
public class Main {

	public static void main(String[] args) {
		
		//创建一个账户，里面有1000的存款
		Account account = new Account("1234567", 1000);

		//模拟两个线程对同一个账户取钱，分别取出800
		new DrawThread("甲", account, 800).start();
		new DrawThread("乙", account, 800).start();
		
	}
	
}
