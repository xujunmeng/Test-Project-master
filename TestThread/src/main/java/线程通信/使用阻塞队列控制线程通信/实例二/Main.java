package 线程通信.使用阻塞队列控制线程通信.实例二;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author james
 * @date 2018/9/25
 */
public class Main {

    public static void main(String[] args) {

        //创建一个容量为1的BlockingQueue
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1);

        //启动3个生产者线程
        new Producer(blockingQueue).start();
        new Producer(blockingQueue).start();
        new Producer(blockingQueue).start();

        //启动一个消费者线程
        new Consumer(blockingQueue).start();

    }

}
