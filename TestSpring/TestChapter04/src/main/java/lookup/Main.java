package lookup;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by james on 2018/4/11.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext bf = new ClassPathXmlApplicationContext("lookup/beans.xml");
        GetBeanTest test = (GetBeanTest)bf.getBean("getBeanTest");
        test.showMe();
    }

}
