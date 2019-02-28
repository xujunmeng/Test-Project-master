package 线程同步.死锁.demo;
/**
@author junmeng.xu
@date  2016年5月18日下午6:24:27
 */


public class DeadLock implements Runnable {

	A a = new A();

	B b = new B();

	public void init(){
		Thread.currentThread().setName("主线程");
		//调用a对象的foo方法
		a.foo(b);
		System.out.println("进入了主线程之后");
	}

	@Override
	public void run() {
		Thread.currentThread().setName("副线程");
		//调用b对象的bar方法
		b.bar(a);
		System.out.println("进入了副线程之后");
		
	}

	public static void main(String[] args)
	{
		DeadLock dl = new DeadLock();
		//以dl为target启动新线程
		new Thread(dl).start();
		//执行init方法作为新线程
		dl.init();
	}
	
}
