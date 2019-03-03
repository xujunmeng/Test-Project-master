package 容器基本用法;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by james on 2018/4/3.
 */
public class BeanFactoryTest {

    @Test
    public void testSimpleLoad() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("容器基本用法/beans.xml"));
        MyTestBean bean = (MyTestBean)bf.getBean("myTestBean");
        String testStr = bean.getTestStr();
        Assert.assertEquals(testStr, "testStr");

    }

}
