package volatile变量;
/**
@author junmeng.xu
@date  2016年8月12日下午5:10:20
 */
public class ThreadStart {

	public static void main(String[] args) {
		
		new ReaderThread().start();
		new ReaderThread1().start();
		
	}
	
}

