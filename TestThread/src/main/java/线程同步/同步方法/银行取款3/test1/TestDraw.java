package 线程同步.同步方法.银行取款3.test1;
/**
@author junmeng.xu
@date  2016年5月19日上午10:21:30
 */
public class TestDraw {

	public static void main(String[] args) {
		Account acc = new Account("123有钱", 1000);
		new DrawThread("aaa", acc, 800).start();
		new DrawThread("bbb", acc, 800).start();
		new DrawThread("ccc", acc, 800).start();
		new DrawThread("ddd", acc, 800).start();
		
	}
	
}
