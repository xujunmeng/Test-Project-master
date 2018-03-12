package 实例七;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 * Created by junmeng.xu on 2016/12/12.
 */
public class AspectJProxyTest {

    public static void main(String[] args) {
        Waiter target = new NaiveWaiter();
        AspectJProxyFactory factory = new AspectJProxyFactory();
        //设置目标对象
        factory.setTarget(target);
        //添加切面类
        factory.addAspect(PreGreetingAspect.class);
        //生成织入切面的代理对象
        Waiter proxy = factory.getProxy();
        proxy.greetTo("John");
        proxy.serveTo("John");
    }



}
