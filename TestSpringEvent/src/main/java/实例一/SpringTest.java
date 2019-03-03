package 实例一;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by james on 2017/7/7.
 */
public class SpringTest {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        //创建一个ApplicationEvent对象
        EmailEvent event = new EmailEvent("xxxx@163.com", "This is a test content");

        System.out.println("SpringTest 当前线程名称 : " + Thread.currentThread().getName());

        //主动触发该事件
        context.publishEvent(event);
    }

}
