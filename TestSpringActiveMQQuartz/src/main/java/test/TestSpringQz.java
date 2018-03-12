package test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
@author junmeng.xu
@date  2016年2月4日下午2:38:57
 */
public class TestSpringQz {
	
	public static void main(String[] args) {
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
}
