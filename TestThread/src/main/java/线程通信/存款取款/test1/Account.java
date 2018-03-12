package 线程通信.存款取款.test1;
/**
@author junmeng.xu
@date  2016年5月19日上午11:11:07
 */
public class Account {

	private boolean flag = false;//有存款的标识
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
	public synchronized void draw(double drawAmount) throws InterruptedException{
		if(!flag){
			wait();
		}else{
			if(balance >= drawAmount){
				System.out.println(Thread.currentThread().getName() + " 取款 : " + drawAmount);
				balance = balance - drawAmount;
				System.out.println("\t余额 : " + balance);
			}else{
				System.out.println(Thread.currentThread().getName() + " 余额不足!!!!");
			}
			flag = false;
			notifyAll();
		}
	}
	public synchronized void deposit(double depositAmount) throws InterruptedException{
		if(flag){
			wait();
		}else{
			System.out.println(Thread.currentThread().getName() + " 存款 : " + depositAmount);
			balance = balance + depositAmount;
			System.out.println("\t余额 : " + balance);
			flag = true;
			notifyAll();
		}
		
	}
	
	
}
