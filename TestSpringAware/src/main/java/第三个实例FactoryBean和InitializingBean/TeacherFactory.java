package 第三个实例FactoryBean和InitializingBean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author james
 * @date 2018/7/18
 */
public class TeacherFactory implements FactoryBean, InitializingBean {

    @Override
    public Object getObject() throws Exception {
        return new Teacher();
    }

    @Override
    public Class<?> getObjectType() {
        return Teacher.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("teacher class initialized.");
    }
}
