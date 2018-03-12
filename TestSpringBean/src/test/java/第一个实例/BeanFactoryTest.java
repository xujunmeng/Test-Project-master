package 第一个实例;

import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by james on 2017/8/28.
 */
public class BeanFactoryTest {

    @Test
    public void testSimpleLoad() {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("ApplicationContext.xml"));
        MyTestBean bean = (MyTestBean)beanFactory.getBean("myTestBean");
        Assert.assertEquals("testStr", bean.getTestStr());
    }

}
