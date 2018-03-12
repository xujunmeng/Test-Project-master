package 线程同步.同步方法.银行取款3.test1;
/**
@author junmeng.xu
@date  2016年5月19日上午10:19:59
 */
public class DrawThread extends Thread{

	private Account account;
	private double drawAmount;
	
	public DrawThread(String name, Account account, double drawAmount) {
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}

	public void run() {
		account.draw2(drawAmount);
	}
	
}
