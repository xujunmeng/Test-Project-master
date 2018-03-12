package 线程同步.银行取款2;
/**
@author junmeng.xu
@date  2016年5月18日下午5:09:19
 */
public class DrawThread extends Thread {

	private Account account;
	private double drawAmount;
	public DrawThread(String name, Account account, double drawAmount){
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}
	//当多条线程修改同一个共享数据时，将涉及数据安全问题
	@Override
	public void run() {
		/**
		 * 使用account作为同步监视器，任何线程进入下面同步代码块之前
		 * 必须先获得对account账户的锁-----其他线程无法获得锁，也就无法修改它
		 * 这种做法符合：加锁--》修改完成--》释放锁    逻辑
		 */
		synchronized (account) {
			//判断余额是否够取款的数额
			if(account.getBalance() >= drawAmount){
				//可以取
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(getName() + "取钱成功 ! 吐出钞票为 : " + drawAmount);
				account.setBalance(account.getBalance() - drawAmount);
				System.out.println("\t余额为 : " + account.getBalance());
			}else{
				System.out.println(getName() + "取钱失败! 余额不足");
			}
		}
 	}
	
}
