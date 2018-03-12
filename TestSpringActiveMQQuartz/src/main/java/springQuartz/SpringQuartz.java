package springQuartz;

import javax.jms.Destination;

import org.quartz.JobExecutionException;

import springjms.service.ProducerService;

/**
@author junmeng.xu
@date  2016年2月4日下午2:28:50
 */
public class SpringQuartz {
	private ProducerService producerService;
	
	private Destination destination;
	
	public void execute()
			throws JobExecutionException {
		System.out.println("spring jms quartz 整合   开始");

		for (int i = 0; i < 5; i++) {
			producerService.sendMessage(destination, "fackfackxjmxjmx喜剧明星加盟新局面下徐军猛西交民巷====" + (i+1));
		}
		System.out.println();
		
	}

	public void setProducerService(ProducerService producerService) {
		this.producerService = producerService;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	
	
}
