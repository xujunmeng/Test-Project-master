package 线程通信.使用条件变量控制协调.存款取款.test3;
/**
@author junmeng.xu
@date  2016年5月19日下午1:50:17
 */
public class Test {

	public static void main(String[] args) {
		Account account = new Account("adsfsdf", 0);
		new DepositThread("存款aaa", account, 1000).start();
		new DepositThread("存款bbb", account, 1000).start();
		new DepositThread("存款ccc", account, 1000).start();
		new DrawThread("取款aaa", account, 5000).start();
		new DrawThread("取款bbb", account, 5000).start();
		new DrawThread("取款ccc", account, 5000).start();
		
		
		
	}
	
}
