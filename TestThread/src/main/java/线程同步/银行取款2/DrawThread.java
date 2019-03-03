package 线程同步.银行取款2;
/**
 *
 * 阻止两个线程对同一个共享资源进行并发访问，因此通常推荐使用可能被并发访问的共享资源充当同步监视器。
 * 对于取钱模拟程序，应该考虑使用账户(account)作为同步监视器。
 *
 * @author junmeng.xu
 * @date  2016年5月18日下午5:09:19
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
	//当多条线程修改同一个共享数据时，将涉及数据安全问题
	@Override
	public void run() {
		/**
		 * 使用account作为同步监视器，任何线程进入下面同步代码块之前
		 * 必须先获得对account账户的锁-----其他线程无法获得锁，也就无法修改它
		 * 这种做法符合：加锁--》修改完成--》释放锁    逻辑
		 * 也称为临界区
		 */
		synchronized (account) {
			//判断余额是否够取款的数额
			if(account.getBalance() >= drawAmount){
				//可以取
				System.out.println(getName() + "取钱成功 ! 吐出钞票为 : " + drawAmount);
				account.setBalance(account.getBalance() - drawAmount);
				System.out.println("\t余额为 : " + account.getBalance());
			}else{
				System.out.println(getName() + "取钱失败! 余额不足");
			}
		}
		//同步代码块结束，该线程释放同步锁
 	}
	
}
