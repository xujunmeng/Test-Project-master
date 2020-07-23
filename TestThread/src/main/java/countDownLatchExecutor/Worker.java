package countDownLatchExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
@author junmeng.xu
@date  2016年2月19日下午3:33:26
 */
public class Worker extends Thread {
	final static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	String workerName;
	int workTime;
	CountDownLatch latch;
	public Worker(String workerName, int workTime, CountDownLatch latch) {
		super();
		this.workerName = workerName;
		this.workTime = workTime;
		this.latch = latch;
	}
	
	public void run(){
		System.out.println("Worker : " + workerName + " do work begin at " + sdf.format(new Date()));
		doWork();
		System.out.println("Worker : " + workerName + " do work complete at " + sdf.format(new Date()));
		latch.countDown();
	}
	
	private void doWork(){
		try {
			Thread.sleep(workTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
