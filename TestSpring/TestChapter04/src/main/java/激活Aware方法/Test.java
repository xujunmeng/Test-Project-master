package 激活Aware方法;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by james on 2018/5/10.
 */
public class Test implements BeanFactoryAware {

    private BeanFactory beanFactory;

    //声明bean的时候spring会自动注入BeanFactory
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void testAware() {
        //通过hello这个bean id从beanFactory获取实例
        Hello hello = (Hello)beanFactory.getBean("hello");
        hello.say();
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        Test test = (Test)ctx.getBean("test");
        test.testAware();
    }
}
