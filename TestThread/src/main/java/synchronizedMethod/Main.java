package synchronizedMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
@author junmeng.xu
@date  2016年3月23日上午11:22:14
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService executor = Executors.newFixedThreadPool(20);
		Tickets t1 = new Tickets();
		Tickets t2 = new Tickets();
		Tickets t3 = new Tickets();
		Tickets t4 = new Tickets();
		Tickets t5 = new Tickets();
		Tickets t6 = new Tickets();
		List<Tickets> tickets = new ArrayList<Tickets>();
		tickets.add(t1);
		tickets.add(t2);
		tickets.add(t3);
		tickets.add(t4);
		tickets.add(t5);
		tickets.add(t6);
		List<Future<Integer>> invokeAll = executor.invokeAll(tickets);
		for (Future<Integer> futures : invokeAll) {
			Integer integer = futures.get();
			System.out.println("运行结果" + integer);
		}
		executor.shutdown();
	}
	
}
