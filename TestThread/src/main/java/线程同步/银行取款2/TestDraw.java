package 线程同步.银行取款2;
/**
@author junmeng.xu
@date  2016年5月18日下午5:19:39
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
