package 线程通信.使用条件变量控制协调.存款取款.test2;
/**
@author junmeng.xu
@date  2016年5月19日上午11:24:32
 */
public class Test {

	public static void main(String[] args) {
		
		Account account = new Account("456哈哈哈", 0);
		new DrawThread("取款111", account, 5000).start();
		new DrawThread("取款222", account, 5000).start();
		new DrawThread("取款333", account, 5000).start();
		new DepositThread("存款111", account, 1000).start();
		new DepositThread("存款222", account, 1000).start();
		
		
	}
	
}
