package springQuartz;

import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author junmeng.xu
 * @date 2016年2月4日上午10:59:21
 */
public class SpringQuartz implements Job {

	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		Map properties = context.getMergedJobDataMap();
		

		System.out.println("Hello World!");
		System.out.println("Previous Fire Time : " + context.getPreviousFireTime());
		System.out.println("Current Fire Time: " + context.getFireTime());
		System.out.println("Next Fire Time: " + context.getNextFireTime());
		System.out.println(properties.get("triggerMessage"));
		System.out.println();

	}

}
