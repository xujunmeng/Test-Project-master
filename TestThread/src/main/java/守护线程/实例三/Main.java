package 守护线程.实例三;
/**
 *
 * setDaemon(true)必须在start()方法之前调用。。。。
 *
 *
 * @author junmeng.xu
 * @date  2016年8月22日上午11:53:52
 */
public class Main {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new MyCommon());
		Thread t2 = new Thread(new MyDaemon());
		//设置为守护线程
		t2.setDaemon(true);
		t2.start();
		t1.start();
		
	}
	
}