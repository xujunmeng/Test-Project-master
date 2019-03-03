package 线程通信.使用阻塞队列控制线程通信.实例二;

import java.util.concurrent.BlockingQueue;

/**
 *
 * 利用BlockingQueue实现线程通信
 *
 * @author james
 * @date 2018/9/20
 */
public class Producer extends Thread {

    private BlockingQueue<String> blockingQueue;

    public Producer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        String[] strArr = new String[]{
          "java","struts","spring"
        };

        for (int i = 0; i < 9999999; i++) {
            System.out.println(getName() + " Producer生产者准备生产集合元素! ");
            try {

                Thread.sleep(200);

                //尝试放入元素，如果队列已满，则线程被阻塞
                blockingQueue.put(strArr[i % 3]);

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " Producer生产完成 : " + blockingQueue);
        }

    }
}
