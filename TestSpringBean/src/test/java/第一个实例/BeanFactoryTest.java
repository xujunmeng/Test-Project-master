package 第一个实例;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import 自定义FactoryBean实现类.Car;

/**
 * Created by james on 2017/8/28.
 */
public class BeanFactoryTest {

    @Test
    public void testSimpleLoad() {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("ApplicationContext.xml"));
        Car bean = (Car)beanFactory.getBean("car");
        System.out.println(bean);
    }

}
