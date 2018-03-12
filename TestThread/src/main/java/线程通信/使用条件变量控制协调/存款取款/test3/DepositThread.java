package 线程通信.使用条件变量控制协调.存款取款.test3;
/**
@author junmeng.xu
@date  2016年5月19日下午1:47:45
 */
public class DepositThread extends Thread{

	private Account account;
	private double depositAmount;
	public DepositThread(String name, Account account, double depositAmount) {
		super(name);
		this.account = account;
		this.depositAmount = depositAmount;
	}
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			account.depositAmount(depositAmount);
		}
	}
	
	
}
