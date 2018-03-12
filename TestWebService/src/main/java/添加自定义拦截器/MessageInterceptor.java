package 添加自定义拦截器;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;

/**
@author junmeng.xu
@date  2016年5月31日下午2:44:05
 */
public class MessageInterceptor extends AbstractPhaseInterceptor<Message> {

	public MessageInterceptor(String phase) {
		super(phase);
	}

	public void handleMessage(Message message) throws Fault {
		
		System.out.println("##################handleMessage##########");
		System.out.println(message);
		if(message.getDestination() != null){
			System.out.println(message.getId() + "#" + message.getDestination().getMessageObserver());
		}
		if(message.getExchange() != null){
			System.out.println(message.getExchange().getInMessage() + "#" + message.getExchange().getInFaultMessage());
			System.out.println(message.getExchange().getOutMessage() + "#" + message.getExchange().getOutFaultMessage());
		}
	}
	
	
	

}
