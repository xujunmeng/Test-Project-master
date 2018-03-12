package 线程通信.使用条件变量控制协调.存款取款.test2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
@author junmeng.xu
@date  2016年5月19日上午11:11:07
 */
public class Account {

	//显示定义Lock对象
	private ReentrantLock lock = new ReentrantLock();
	//获得指定Lock对象 对应的  条件变量
	private Condition condition = lock.newCondition();
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
	public void draw(double drawAmount) throws InterruptedException{
		lock.lock();
		try {
			if(!flag){
				condition.await();
			}else{
				if(balance >= drawAmount){
					System.out.println(Thread.currentThread().getName() + " 取款 : " + drawAmount);
					balance = balance - drawAmount;
					System.out.println("\t余额 : " + balance);
				}else{
					System.out.println(Thread.currentThread().getName() + " 余额不足!!!!");
				}
				flag = false;
				condition.signalAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public void deposit(double depositAmount) throws InterruptedException{
		lock.lock();
		try {
			if(flag){
				condition.await();
			}else{
				System.out.println(Thread.currentThread().getName() + " 存款 : " + depositAmount);
				balance = balance + depositAmount;
				System.out.println("\t余额 : " + balance);
				flag = true;
				condition.signalAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	}
	
	
}
