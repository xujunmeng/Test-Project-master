package 线程通信.存款取款;
/**
@author junmeng.xu
@date  2016年5月18日下午3:06:34
 */
public class DrawThread extends Thread {

	/**
	 * 用户账户
	 */
	private Account account;

	/**
	 * 当前取钱线程所希望取的钱数
	 */
	private double drawAmount;
	
	public DrawThread(String name, Account account, double drawAmount){
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}

	//重复100次执行取钱操作
	@Override
	public void run(){
		for (int i = 0; i < 100; i++) {
			account.draw(drawAmount);
		}
	}
	
}
