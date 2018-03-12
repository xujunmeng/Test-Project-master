package 线程通信.使用条件变量控制协调.存款取款.test3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
@author junmeng.xu
@date  2016年5月19日下午1:30:36
 */
public class Account {

	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean flag = false;
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
	public void draw(double drawAmount){
		lock.lock();
		try {
			if(!flag){
				condition.await();
			} else {
				if(balance >= drawAmount){
					System.out.println(Thread.currentThread().getName() + " 取款 ： " + drawAmount);
					balance = balance - drawAmount;
					System.out.println("\t余额 : " + balance);
				} else {
					System.out.println(Thread.currentThread().getName() + "余额不足！！！！");
				}
				flag = false;
				condition.signalAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	public void depositAmount(double depositAmount){
		lock.lock();
		try {
			if(flag){
				condition.await();
			} else {
				System.out.println(Thread.currentThread().getName() + " 存款 : " + depositAmount);
				balance = balance + depositAmount;
				System.out.println("\t余额 : " + balance);
				flag = true;
				condition.signalAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
