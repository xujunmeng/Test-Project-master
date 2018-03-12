package 实例一;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by junmeng.xu on 2016/12/12.
 */
public class TestBeforeAdvice {

    public static void main(String[] args) {

        Waiter target = new NaiveWaiter();

        BeforeAdvice advice = new GreetingBeforeAdvice();

        //spring 提供的代理工厂
        ProxyFactory pf = new ProxyFactory();

        //设置代理目标
        pf.setTarget(target);

        //为代理目标添加增强
        pf.addAdvice(advice);

        //生成代理实例
        Waiter proxy = (Waiter)pf.getProxy();
        proxy.greetTo("John");
        proxy.serveTo("Tom");

    }

}
