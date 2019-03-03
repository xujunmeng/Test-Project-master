package 线程通信.condition;

/**
@author junmeng.xu
@date  2016年5月18日下午2:59:48
 */
public class DepositThread extends Thread {

	/**
	 * 用户账户
	 */
	private Account account;

	/**
	 * 当前存钱线程所希望存款的钱数
	 */
	private double depositAmount;

	public DepositThread(String name, Account account, double depositAmount){
		super(name);
		this.account = account;
		this.depositAmount = depositAmount;
	}
	
	//重复100次执行存款操作
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			account.deposit(depositAmount);
		}
	}
}
