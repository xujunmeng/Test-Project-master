package 实例十Advisor配置;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by junmeng.xu on 2016/12/13.
 */
public class Test {

    public static void main(String[] args) {

        String configPath = "实例十Advisor配置/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter naiveWaiter = (Waiter)ctx.getBean("naiveWaiter");
        Waiter naughtyWaiter = (Waiter)ctx.getBean("naughtyWaiter");
        naiveWaiter.greetTo("John");
        naughtyWaiter.greetTo("Tom");

    }

}
