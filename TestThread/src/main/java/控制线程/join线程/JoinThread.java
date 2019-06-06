package 控制线程.join线程;
/**
 *
 * Thread提供了让一个线程等待另一个线程完成的方法，join()方法。
 * 当在某个程序执行流中调用其他线程的join()方法时，
 * 调用线程将被阻塞，直到被join()方法加入的join线程执行完为止。
 *
 * join()方法通常由使用线程的程序调用，以将大问题划分成许多小问题，每个小问题分配一个线程。
 * 当所有的小问题都得到处理后，再调用主线程来进一步操作。
 *
 *
 * @author junmeng.xu
 * @date  2016年5月18日下午3:27:33
 */
public class JoinThread extends Thread {

	/**
	 * 提供一个有参数的构造器，用于设置该线程的名字
	 */
	public JoinThread(String name){
		super(name);
	}

	/**
	 * 重写run方法，定义线程执行体
	 */
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(getName() + "   " + i);
		}
	}
	
}
