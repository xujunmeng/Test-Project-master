package 线程通信.condition;

/**
 *
 * 程序最后被阻塞无法继续向下执行，这是因为3个存款者线程共有300次存款操作，但1个取钱者线程只有100次取钱操作，
 * 所以程序最后被阻塞
 *
 *
 * @author junmeng.xu
 * @date  2016年5月18日下午3:11:14
 */
public class TestDraw {

	public static void main(String[] args) {
		
		//创建一个账户
		Account acct = new Account("1234567", 0, false);
		new DrawThread("取钱者A", acct, 800).start();
		new DepositThread("存款者甲", acct, 800).start();
		new DepositThread("存款者乙", acct, 800).start();
		new DepositThread("存款者丙", acct, 800).start();
	}
	
}
