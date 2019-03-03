package 线程同步.同步方法.银行取款3;
/**
 *
 * 对于synchronized修饰的实例方法(非static方法)而言，无须显式指定同步监视器，
 * 同步方法的同步监视器是this，也就是调用该方法的对象
 *
 * 不可变类总是线程安全的，因为他的对象状态不可改变，单可变对象需要额外的方法来保证其线程安全。
 *
 *
 *
 * @author junmeng.xu
 * @date  2016年5月18日下午5:49:29
 */
public class Account {

	/**
	 * 账户编号
	 */
	private String accountNo;

	/**
	 * 账户余额
	 */
	private double balance;

	public Account(String accountNo, double balance) {
		this.accountNo = accountNo;
		this.balance = balance;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public double getBalance() {
		return balance;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountNo == null) ? 0 : accountNo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Account other = (Account) obj;
		if (accountNo == null) {
			if (other.accountNo != null) {
				return false;
			}
		} else if (!accountNo.equals(other.accountNo)) {
			return false;
		}
		return true;
	}

	public synchronized void draw(double drawAmount){
		if(getBalance() >= drawAmount){
			//可以取
			System.out.println(Thread.currentThread().getName() + "取钱成功 ! 吐出钞票为 : " + drawAmount);
			balance = balance - drawAmount;
			System.out.println("\t余额为 : " + balance);
		}else{
			System.out.println(Thread.currentThread().getName() + "取钱失败! 余额不足");
		}
	}
	
	
}
