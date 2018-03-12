package 线程通信.存款取款.test1;
/**
@author junmeng.xu
@date  2016年5月19日上午11:20:07
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
		try {
			for (int i = 0; i < 100; i++) {
				account.draw(drawAmount);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
