package callableFuture.a;
/**
@author junmeng.xu
@date  2016年3月16日下午5:24:06
 */
public class Task1 extends Task {

	public Integer call() throws Exception {
		String name = Thread.currentThread().getName();
		System.out.println("子线程task1在进行计算=="+ name +"===");
//		Thread.sleep(3000);
		int sum = 0;
		for (int i = 0; i < 90; i++) {
			sum += i;
		}
		return sum;
	}

}
