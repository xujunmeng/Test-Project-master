package yield.实例二;


/**
@author junmeng.xu
@date  2016年5月18日下午4:27:38
 */
public class TestYield2 extends Thread {

	public TestYield2(String name){
		super(name);
	}
	//定义run方法作为线程执行体
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println(getName() + "   " + i);
			if(i == 10){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Thread.yield();
			}
		}
	}
	public static void main(String[] args) {
		
		//启动两条并发线程
		TestYield2 ty1 = new TestYield2("高级");
		//将ty1线程设置成最高优先级
		ty1.setPriority(Thread.MAX_PRIORITY);
		ty1.start();
		TestYield2 ty2 = new TestYield2("低级");
		//将ty2线程设置成最低优先级
		ty2.setPriority(Thread.MIN_PRIORITY);
		ty2.start();
		
	}
}
