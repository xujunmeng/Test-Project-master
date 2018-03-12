package 线程通信.使用条件变量控制协调.存款取款.test2;
/**
@author junmeng.xu
@date  2016年5月19日上午11:23:23
 */
public class DepositThread extends Thread {

	private Account account;
	private double depositAmount;
	public DepositThread(String name, Account account, double depositAmount) {
		super(name);
		this.account = account;
		this.depositAmount = depositAmount;
	}
	@Override
	public void run() {
		try {
			for (int i = 0; i < 100; i++) {
				account.deposit(depositAmount);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
