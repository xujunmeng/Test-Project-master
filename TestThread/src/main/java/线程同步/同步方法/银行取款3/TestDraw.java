package 线程同步.同步方法.银行取款3;


/**
@author junmeng.xu
@date  2016年5月18日下午6:09:42
 */
public class TestDraw {

	public static void main(String[] args) {
		Account acc = new Account("333333", 1000);
		new DrawThread("甲", acc, 800).start();
		new DrawThread("乙", acc, 800).start();
		new DrawThread("丙", acc, 800).start();
		new DrawThread("丁", acc, 800).start();
		
	}
	
}
