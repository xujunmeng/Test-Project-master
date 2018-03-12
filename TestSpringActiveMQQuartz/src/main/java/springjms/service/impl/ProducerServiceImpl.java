package springjms.service.impl;

import java.io.Serializable;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import springjms.service.ProducerService;

/**
@author junmeng.xu
@date  2016年2月3日下午5:46:22
 */
public class ProducerServiceImpl implements ProducerService {

	private JmsTemplate jmsTemplate;
	
	public void sendMessage(Destination destination, final String message) {
		
		System.out.println("生产者发送消息");
		System.out.println("生产者发送了一个消息 ： " + message);
		jmsTemplate.send(destination, new MessageCreator() {
			
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
		
	}

	public void sendMessage(Destination destination, Serializable obj) {
		
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

}
