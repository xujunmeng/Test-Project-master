package 实例三;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by junmeng.xu on 2016/12/12.
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        String clientName = (String) args[0];
        System.out.println("How are you ! Mr." + clientName + ".");
    }
}
