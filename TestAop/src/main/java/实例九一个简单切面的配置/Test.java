package 实例九一个简单切面的配置;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by junmeng.xu on 2016/12/12.
 */
public class Test {

    public static void main(String[] args) {

//        String configPath = "实例九一个简单切面的配置/beans.xml";  //前置增强
//        String configPath = "实例九一个简单切面的配置/beans2.xml";  //后置增强
//        String configPath = "实例九一个简单切面的配置/beans3.xml";  //后置增强
        String configPath = "实例九一个简单切面的配置/beans4.xml";  //环绕增强
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter naiveWaiter = (Waiter)ctx.getBean("naiveWaiter");
        Waiter naughtyWaiter = (Waiter)ctx.getBean("naughtyWaiter");
        naiveWaiter.greetTo("John");
        naughtyWaiter.greetTo("Tom");




    }

}
