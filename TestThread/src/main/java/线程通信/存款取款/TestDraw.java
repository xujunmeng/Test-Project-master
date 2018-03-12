package 线程通信.存款取款;
/**
@author junmeng.xu
@date  2016年5月18日下午3:11:14
 */
public class TestDraw {

	public static void main(String[] args) {
		
		//创建一个账户
		Account acct = new Account("1234567", 0);
		new DrawThread("取钱者", acct, 800).start();
		new DepositThread("存款者甲", acct, 800).start();
		new DepositThread("存款者乙", acct, 800).start();
		new DepositThread("存款者丙", acct, 800).start();
	}
	
}
