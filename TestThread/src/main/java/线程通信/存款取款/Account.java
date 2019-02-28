package 线程通信.存款取款;
/**
@author junmeng.xu
@date  2016年5月18日下午2:44:31
 */
public class Account {

	/**
	 * 账号编号
	 */
	private String accountNo;

	/**
	 * 账户余额
	 */
	private double balance;

	/**
	 * 标识账户中是否已有存款的旗标
	 */
	private boolean flag = false;

	public Account(){}
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
	
	//一个类中两个同步方法，在执行其中一个的时候，另一个是执行不了的，因为对应的this是同一个
	
	public synchronized void draw(double drawAmount){
		try {
			//如果flag为假，表明账户中还没有人存钱进去，则取钱方法阻塞
			if(!flag){
				wait();
			}else{
				//执行取钱
				System.out.println(Thread.currentThread().getName() + " 取钱 " + drawAmount);
				balance -= drawAmount;
				System.out.println("账户余额为 : " + balance);

				//将标识账户是否已有存款的旗标设为false
				flag = false;

				//唤醒其他线程  ：：唤醒了deposit方法中wait()的线程
				notifyAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized void deposit(double depositAmount){
		try {
			//如果flag为真，表明账户中已有人存钱进去，则存钱方法阻塞
			if(flag){
				wait();
			}else{
				//执行存款
				System.out.println(Thread.currentThread().getName() + " 存款 " + depositAmount);
				balance += depositAmount;
				System.out.println("账户余额为 ： " + balance);

				//将表示账户是否已有存款的旗标设为true
				flag = true;

				//唤醒其他线程
				notifyAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
