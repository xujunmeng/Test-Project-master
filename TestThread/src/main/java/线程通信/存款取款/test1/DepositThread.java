package 线程通信.存款取款.test1;
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
