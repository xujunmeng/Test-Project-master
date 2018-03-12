package 线程同步.同步方法.银行取款3;
/**
@author junmeng.xu
@date  2016年5月18日下午5:49:29
 */
public class Account {

	private String accountNo;
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		return true;
	}
	
	//因为账户余额不允许随便修改，所以取消balance属性的setter方法
	
	//提供一个线程安全draw方法来完成取钱操作
	public synchronized void draw(double drawAmount){
		if(getBalance() >= drawAmount){
			//可以取
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "取钱成功 ! 吐出钞票为 : " + drawAmount);
			balance = balance - drawAmount;
			System.out.println("\t余额为 : " + balance);
		}else{
			System.out.println(Thread.currentThread().getName() + "取钱失败! 余额不足");
		}
	}
	
	
}
