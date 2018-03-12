package 守护线程.实例三;
/**
@author junmeng.xu
@date  2016年8月22日上午11:53:52
 */
public class Main {

	public static void main(String[] args) {
		
		Thread t1 = new MyCommon();
		Thread t2 = new Thread(new MyDaemon());
		//设置为守护线程
		t2.setDaemon(true);
		t2.start();
		t1.start();
		
	}
	
}

class MyCommon extends Thread{
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("前台线程是第 : " + i + "次执行!!");
			try {
				Thread.sleep(7);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class MyDaemon implements Runnable{
	public void run() {
		for(long i = 0 ; i < 999999L ; i++){
			System.out.println("守护线程第 : " + i+ "次执行!!");
			try {
				Thread.sleep(7);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}