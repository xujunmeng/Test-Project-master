package 第二个实例BeanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by james on 2017/7/7.
 */
public class InitHelloWorld implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("BeforeInitialization : " + beanName);

        return bean;//you can return any other object as well.
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("AfterInitialization : " + beanName);

        return bean;//you can return any other object as well.
    }
}
