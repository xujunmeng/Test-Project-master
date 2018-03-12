package yield;
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
		for(int i = 0 ; i < 20 ; i++){
			System.out.println(getName() + "  " + i);
			//当i等于20时，使用yield方法让当前线程让步
			if(i == 10){
				Thread.yield();
			}
		}
	}
	
	public static void main(String[] args) {
		
		//启动两条并发线程
		TestYield2 ty1 = new TestYield2("高级");
		//将ty1线程设置成最高优先级
//		ty1.setPriority(Thread.MAX_PRIORITY);
		ty1.start();
		TestYield2 ty2 = new TestYield2("低级");
		//将ty2线程设置成最低优先级
//		ty2.setPriority(Thread.MIN_PRIORITY);
		ty2.start();
		
		
	}
	
}
