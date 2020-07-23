package ConcurrentLinkedQueue.测试用例二;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * 场景：10000个人去饭店吃饭，10张桌子供饭，分别比较size() 和 isEmpty() 的耗时
 * @author james
 * @date 2018/8/20
 */
public class Dinner implements Runnable {

    private String name;

    private ConcurrentLinkedQueue<String> queue;

    private CountDownLatch count;

    public Dinner(String name, ConcurrentLinkedQueue<String> queue, CountDownLatch count) {
        this.name = name;
        this.queue = queue;
        this.count = count;
    }

    @Override
    public void run() {
        //while (queue.size() > 0) {
        //当数据量越大，这种耗时差距越明显。所以这种判断用isEmpty 更加合理
        while (!queue.isEmpty()) {
            //从队列取出一个元素 排队的人少一个
            System.out.println("[ " + queue.poll() + " ]" + "---已吃完，饭桌编号 : " + name + ", 当前线程 : " +Thread.currentThread().getName());
        }

        //计数器-1
        count.countDown();


    }
}
