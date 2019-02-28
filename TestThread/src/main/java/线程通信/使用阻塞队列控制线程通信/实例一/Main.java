package 线程通信.使用阻塞队列控制线程通信.实例一;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author james
 * @date 2018/9/20
 */
public class Main {

    @Test
    public void test() throws InterruptedException {
        //定义一个长度为2的阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(2);

        blockingQueue.put("java");
        blockingQueue.put("java");
        blockingQueue.put("java");
    }

}
