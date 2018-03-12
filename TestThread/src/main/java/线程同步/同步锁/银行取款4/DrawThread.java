package 线程同步.同步锁.银行取款4;


/**
@author junmeng.xu
@date  2016年5月18日下午6:18:15
 */
public class DrawThread extends Thread {
	private Account account;
	private double drawAmount;
	public DrawThread(String name, Account account, double drawAmount){
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}
	
	@Override
	public void run() {
		//直接调用account对象的draw方法来执行取钱
		account.draw(drawAmount);
	}
	
}
