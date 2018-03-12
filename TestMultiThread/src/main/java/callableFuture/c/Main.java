package callableFuture.c;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
@author junmeng.xu
@date  2016年4月5日下午1:23:32
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String begin = simpleDateFormat.format(new Date());// new Date()为获取当前系统时间
		
		ExecutorService executor = Executors.newFixedThreadPool(20);
		
		List<String> list = addList();
		int count = list.size();
		int times = count / 20 + (count % 20 == 0 ? 0 : 1);
		
		List<Callable<Integer>> calls = new ArrayList<Callable<Integer>>();
		int head = 0;
		for (int i = 0; i < times; i++) {
			int end = head + 20;
			if(end > count) end = count;
			final List<String> subSids = list.subList(head, end);
			
			Callable<Integer> callable = new Callable<Integer>() {
				
				public Integer call() throws Exception {
					System.out.println("subSids : " + subSids);
					List<String> a = subTest(subSids);
					System.out.println(" a : " + a);
					System.out.println(Thread.currentThread().getName() + " : ");
					return null;
				}

			};
			head += 20;
			calls.add(callable);
			
		}
		executor.invokeAll(calls);
		
		String end = simpleDateFormat.format(new Date());// new Date()为获取当前系统时间
		System.out.println(begin.toString());
		System.out.println(end.toString());
		
		executor.shutdown();
		
	}
	private static List<String> subTest(List<String> subSids) {
		int i = 0;
		List<String> a = new ArrayList<String>();
			for (String string : subSids) {
				a.add(string + " : " + i++); 
			}
		return a;
		
	}
	
	public static List<String> addList(){
		List<String> list = new ArrayList<String>();
		for(int i= 0; i<200000 ;i++){
			list.add("" + i);
		}
		return list;
	}
}
