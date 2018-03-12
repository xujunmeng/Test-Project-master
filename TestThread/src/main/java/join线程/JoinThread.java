package join线程;
/**
@author junmeng.xu
@date  2016年5月18日下午3:27:33
 */
public class JoinThread extends Thread {

	//提供一个有参数的构造器，用于设置该线程的名字
	public JoinThread(String name){
		super(name);
	}
	//重写run方法，定义线程执行体
	@Override
	public void run() {
		for (int i = 0; i < 30; i++) {
			System.out.println(getName() + "   " + i);
		}
	}
	public static void main(String[] args) throws InterruptedException {
		
		//启动子线程
		new JoinThread("新线程").start();
		for (int i = 0; i < 30; i++) {
			if(i == 10){
				JoinThread jt = new JoinThread("被Join的线程");
				jt.start();
				//main线程调用了jt线程的join方法，main线程必须等jt执行结束才会向下执行
				jt.join();
			}
			
			//这个是主线程（main线程）打印的日志
			System.out.println(Thread.currentThread().getName() + "   " + i);
		}
		
	}
	
}
