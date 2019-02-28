package spring异步调用;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.concurrent.Future;

/**
 * Created by james on 2018/5/24.
 */
@EnableAsync
@Component
public class TestAsyncBean {

    public void sayHello4() throws InterruptedException {
        Thread.sleep(2 * 1000);//网络连接中 。。。消息发送中。。。
        System.out.println(MessageFormat.format("线程:{0}==我爱你啊!", Thread.currentThread().getName()));
    }

    @Async
    public String sayHello2() throws InterruptedException {
        Thread.sleep(2 * 1000);//网络连接中 。。。消息发送中。。。
        String result = MessageFormat.format("线程:{0}==我爱你啊!", Thread.currentThread().getName());
        return result;// 调用方调用后会立即返回,所以返回null
    }

    @Async
    public Future<String> sayHello3() throws InterruptedException {
        Thread.sleep(6 * 1000);//网络连接中 。。。消息发送中。。。
        String result = MessageFormat.format("线程:{0}==我爱你啊!", Thread.currentThread().getName());
        return new AsyncResult<String>(result);
    }

    /**
     * 异步方法
     *
     * @throws InterruptedException
     */
    @Async
    public void asyncMethod() throws InterruptedException {
        for (int i = 1; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "********" + i);
        }
    }


}
