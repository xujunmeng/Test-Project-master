package 第三个实例FactoryBean和InitializingBean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author james
 * @date 2018/7/13
 */
public class StuFactory implements FactoryBean, InitializingBean {

    @Override
    public Object getObject() throws Exception {
        return new Student();
    }

    @Override
    public Class<?> getObjectType() {
        return Student.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("student class initialized.");
    }
}
