package ScheduleExecutorService使用实例;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
@author junmeng.xu
@date  2016年8月22日下午1:54:25
 */
public class ScheduleExecutorServiceTest {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws Exception {
		
		//初始化一个ScheduledExecutorService对象，这个对象的线程池大小为2
		ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
		
		//用内函数的方式定义了一个Runnable任务
		Runnable task1 = new Runnable() {
			
			public void run() {
				System.out.println("Taskrepeating.");
			}
		};
		
		//调用所定义的ScheduleExecutorService对象来执行任务，任务每秒执行一次。
		//能重复执行的任务一定是Runnable类型。
		//注意我们可以用TimeUnit来制定时间单位,这也是Java5.0里新的特性，5.0以前的计时单位是微秒，现在可精确到奈秒
		final ScheduledFuture future1 = service.scheduleAtFixedRate(task1, 0, 1, TimeUnit.SECONDS);
		
		//调用ScheduleExecutorService对象来执行第二个任务，第二个任务所作的就是在10秒钟后取消第一个任务
		ScheduledFuture future2 = service.schedule(new Callable() {

			public Object call() throws Exception {
				future1.cancel(true);
				return "taskcancelled!";
			}
		}, 10, TimeUnit.SECONDS);
		
		System.out.println(future2.get());
		
		//关闭服务
		service.shutdown();
		
	}
	
}
