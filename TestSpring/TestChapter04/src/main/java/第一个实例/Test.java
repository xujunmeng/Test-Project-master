package 第一个实例;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by james on 2017/5/26.
 */
public class Test {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("第一个实例/beans.xml");

    }

}
