package springjms.service;

import java.io.Serializable;

import javax.jms.Destination;

/**
@author junmeng.xu
@date  2016年2月3日下午5:49:07
 */
public interface ProducerService {
	
	//发送普通的纯文本消息
	public void sendMessage(Destination destination , String message);
	
	//发送一个ObjectMessage
	public void sendMessage(Destination destination , Serializable obj);
	

}
