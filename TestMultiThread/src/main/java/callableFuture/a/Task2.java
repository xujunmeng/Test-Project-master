package callableFuture.a;


/**
@author junmeng.xu
@date  2016年3月16日下午5:20:37
 */
public class Task2 extends Task {

	public Integer call() throws Exception {
		String name = Thread.currentThread().getName();
		System.out.println("子线程task2在进行计算=="+ name +"===");
//		Thread.sleep(3000);
		int sum = 0;
		for (int i = 0; i < 80; i++) {
			sum += i;
		}
		return sum;
	}

}
