package 实例八切点函数详解;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by junmeng.xu on 2016/12/12.
 */
public class PointcutFunTest {

    public static void main(String[] args) {

        String configPath = "实例八切点函数详解/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter naiveWaiter = (Waiter) ctx.getBean("naiveWaiter");
        Waiter naughtyWaiter = (Waiter) ctx.getBean("naughtyWaiter");
		naiveWaiter.greetTo("John");
		naiveWaiter.serveTo("John");
		naughtyWaiter.greetTo("Tom");
		naughtyWaiter.serveTo("Tom");

    }

}
