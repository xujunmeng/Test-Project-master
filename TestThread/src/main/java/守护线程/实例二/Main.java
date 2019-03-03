package 守护线程.实例二;

/**
@author junmeng.xu
@date  2016年8月22日上午11:40:48
 */
//运行结果：文件daemon.txt中没有"daemon"字符串


//完成文件输出的守护线程任务
public class Main{

	public static void main(String[] args) {
		Runnable tr = new TestRunnable();
		Thread thread = new Thread(tr);
		//设置守护线程
		thread.setDaemon(true);
		//开始执行分进程
		thread.start();
	}
	

}