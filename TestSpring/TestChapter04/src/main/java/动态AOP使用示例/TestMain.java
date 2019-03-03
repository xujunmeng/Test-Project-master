package 动态AOP使用示例;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by james on 2018/5/10.
 */
public class TestMain {

    public static void main(String[] args) {
        ApplicationContext bf = new ClassPathXmlApplicationContext("application-context.xml");
        ITestBean bean = (ITestBean)bf.getBean("testBean");
        bean.test();
    }

}
