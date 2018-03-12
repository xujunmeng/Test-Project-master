import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
@author junmeng.xu
@date  2016年2月4日上午11:02:48
 */


public class TestSpringQz {
	
	public static void main(String[] args) {
//		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext2.xml");
	}
	
}
