package 多生产多消费.test2;
/**
@author junmeng.xu
@date  2016年8月17日下午4:34:38
 */
public class Resource {
	private String name;
	private int count = 1;
	private boolean flag = false;
	public synchronized void set(String name){
		while(!flag){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.name = name + count;
			count++;
			System.out.println(Thread.currentThread().getName() + "...生产者..." + this.name);
			flag = true;
		}
		this.notify();
	}
	public synchronized void get(){
		while(flag){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "...消费者..." + this.name);
			flag = false;
		}
		this.notify();
	}
}
