package 线程通信.使用条件变量控制协调.存款取款.test3;
/**
@author junmeng.xu
@date  2016年5月19日下午1:49:07
 */
public class DrawThread extends Thread {

	private Account account;
	private double drawAmount;
	public DrawThread(String name, Account account, double drawAmount) {
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			account.draw(drawAmount);
		}
	}
	
}
