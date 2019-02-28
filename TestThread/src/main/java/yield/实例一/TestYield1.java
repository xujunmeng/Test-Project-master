package yield.实例一;

/**
@author junmeng.xu
@date  2016年8月17日上午10:39:13
 */
public class TestYield1 extends Thread {

	private String name;
	
	public TestYield1(String name){
		this.name = name;
	}
	
	@Override
	public void run() {
		for(int i = 0 ; i < 5 ; i++){
			System.out.println(name + "  " + i);
			//当i等于5时，使用yield方法让当前线程让步
			if(i == 2){
				Thread.yield();
			}
		}
	}
}
