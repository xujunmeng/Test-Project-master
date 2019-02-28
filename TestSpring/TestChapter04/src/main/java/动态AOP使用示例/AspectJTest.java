package 动态AOP使用示例;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by james on 2018/5/10.
 */
@Aspect
@Component
public class AspectJTest {

//    @Before("execution(* *.test())")
//    public void beforeTest() {
//        System.out.println("beforeTest");
//    }
//
//    @After("execution(* *.test())")
//    public void afterTest() {
//        System.out.println("afterTest");
//    }
//
//    @Around("execution(* *.test())")
//    public Object aroundTest(ProceedingJoinPoint p) {
//        System.out.println("around before");
//        Object o = null;
//        try {
//            o = p.proceed();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        System.out.println("around after");
//        return o;
//    }

    @AfterThrowing(pointcut = "execution(* 动态AOP使用示例.impl.TestBean.test(..))", throwing = "e")
    public void doAfterThrowing(Throwable e) {
        if (e instanceof Exception) {
            System.out.println("asdf");
        }
    }

}
