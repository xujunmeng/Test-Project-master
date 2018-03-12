package 多生产多消费.test1;


/**
 * @author junmeng.xu
 * @date 2016年8月17日下午4:37:14
 */
public class Test {
	public static void main(String[] args) {
		Resource resource = new Resource();
		Producter producter1 = new Producter(resource);
		Producter producter2 = new Producter(resource);
		Consumer consumer1 = new Consumer(resource);
		Consumer consumer2 = new Consumer(resource);
		Thread t1 = new Thread(producter1);
		Thread t2 = new Thread(producter2);
		Thread t3 = new Thread(consumer1);
		Thread t4 = new Thread(consumer2);
		t1.start();
		t2.start();
		t3.start();
		t4.start();

	}

}
