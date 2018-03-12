package 第二个实例基于注解及基于Java类配置中引用属性;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by james on 2017/5/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:第二个实例基于注解及基于Java类配置中引用属性.xml"})
public class Test1 {


    @Autowired
    private MyDataSource myDataSource;

    @Test
    public void test() {
        System.out.println(myDataSource);
    }
}
