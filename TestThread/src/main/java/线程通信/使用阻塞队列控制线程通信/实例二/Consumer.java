package 线程通信.使用阻塞队列控制线程通信.实例二;

import java.util.concurrent.BlockingQueue;

/**
 * @author james
 * @date 2018/9/25
 */
public class Consumer extends Thread {

    private BlockingQueue<String> blockingQueue;

    public Consumer (BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(getName() + " Consumer消费者准备消费集合元素! ");
            try {

                Thread.sleep(200);

                //尝试取出元素，如果队列已空，则线程被阻塞
                String result = blockingQueue.take();
                System.out.println(getName() + " Consumer消费者取出的元素为 : " + result);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
