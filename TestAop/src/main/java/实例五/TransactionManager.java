package 实例五;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * Created by junmeng.xu on 2016/12/12.
 */
public class TransactionManager implements ThrowsAdvice {

    //定义增强逻辑
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable{

        System.out.println("--------------");
        System.out.println("method : " + method.getName());
        System.out.println("抛出异常 : " + ex.getMessage());
        System.out.println("成功回滚事务");

    }
}
