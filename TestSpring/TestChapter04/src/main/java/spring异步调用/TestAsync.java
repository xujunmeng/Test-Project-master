package spring异步调用;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;

/**
 * Created by james on 2018/5/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/application-context.xml"})
public class TestAsync {

    @Autowired
    private TestAsyncBean testAsyncBean;

    @Test
    public void test_sayHello4() throws InterruptedException, ExecutionException {
        System.out.println("你不爱我了么?");
        testAsyncBean.sayHello4();
        System.out.println("回的这么慢, 你肯定不爱我了, 我们还是分手吧。。。");
        Thread.sleep(3 * 1000);// 不让主进程过早结束
    }

    @Test
    public void test_sayHello2() throws InterruptedException, ExecutionException {
        System.out.println(MessageFormat.format("线程:{0}==你不爱我了么?", Thread.currentThread().getName()));
        System.out.println(testAsyncBean.sayHello2());
        System.out.println(MessageFormat.format("线程:{0}==你说的啥? 我们还是分手吧。。。", Thread.currentThread().getName()));
        Thread.sleep(3 * 1000);// 不让主进程过早结束
    }

    @Test
    public void test_sayHello3() throws InterruptedException, ExecutionException {
        System.out.println(MessageFormat.format("线程:{0}==你不爱我了么?", Thread.currentThread().getName()));
        System.out.println(testAsyncBean.sayHello3().get());
        System.out.println(MessageFormat.format("线程:{0}==你说的啥? 我们还是分手吧。。。", Thread.currentThread().getName()));
    }

    @Test
    public void sdfdsf() {
        try {
            //获取对象bean，进而调用异步方法
            testAsyncBean.asyncMethod();

            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "********" + i);
                if (2 == i) {
                    Thread.sleep(1000L);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
