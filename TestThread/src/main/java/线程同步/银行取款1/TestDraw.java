package 线程同步.银行取款1;
/**
@author junmeng.xu
@date  2016年5月18日下午4:53:58
 */
public class TestDraw {

	public static void main(String[] args) {
		
		//创建一个账户
		Account acct = new Account("1234567", 1000);
		//模拟两个线程对同一个账户取钱
		new DrawThread("甲", acct, 800).start();
		new DrawThread("乙", acct, 800).start();
		
	}
	
}
