package 第一个实例ApplicationContextAware;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by james on 2017/7/7.
 */
public class Test {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("第一个实例ApplicationContextAware/bean.xml");

        HelloBean hello = (HelloBean)context.getBean("helloBean");

        String helloWord = hello.getHelloWord();

        System.out.println(helloWord);

    }

}
