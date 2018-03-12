package 实例八切点函数详解;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by junmeng.xu on 2016/12/12.
 */
@Aspect
public class TestAspect {

    @AfterReturning("@annotation(实例八切点函数详解.NeedTest)")  //后置增强切面
    public void needTestFun(){
        System.out.println("needTestFun() executed!");
    }

}
