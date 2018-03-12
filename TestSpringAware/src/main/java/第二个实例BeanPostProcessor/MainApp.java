package 第二个实例BeanPostProcessor;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by james on 2017/7/7.
 */
public class MainApp {

    public static void main(String[] args) {

        AbstractApplicationContext context = new ClassPathXmlApplicationContext("第二个实例BeanPostProcessor/bean.xml");

        HelloWorld obj = (HelloWorld)context.getBean("helloWorld");

        obj.getMessage();

        context.registerShutdownHook();

    }

}
