package 实例八切点函数详解;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by junmeng.xu on 2016/12/12.
 */
@Aspect
public class TestAspect {

    @AfterReturning("@annotation(实例八切点函数详解.NeedTest)")  //后置增强切面
    public void needTestFun(){
        System.out.println("needTestFun() executed!");
    }

    @AfterReturning("@within(实例八切点函数详解.NeedClassTest)")  //后置增强切面
    public void needClassTestFun() {
        System.out.println("needClassTestFun() executed!");
    }

    /**
     * 针对类下的所有方法
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "@within(实例八切点函数详解.NeedClassTest)", throwing = "e")
    public void needClassThrowFun(JoinPoint joinPoint, Throwable e) {
        System.out.println(e);
    }

}
