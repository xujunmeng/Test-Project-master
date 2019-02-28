package 循环依赖.构造器循环依赖;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by james on 2018/4/16.
 */
public class TestMain {

    @Test
    public void test() {
        new ClassPathXmlApplicationContext("循环依赖/构造器循环依赖/beans.xml");
    }

}
