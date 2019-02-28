package ConcurrentLinkedQueue.测试用例二;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.currentTimeMillis;

/**
 * @author james
 * @date 2018/8/21
 */
public class Test01ConcurrentLinkedQueue {

    public static void main(String[] args) throws InterruptedException {

        //吃饭人数
        int peopleNum = 10000;

        //饭桌数量
        int tableNum = 10;

        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

        //计数器
        CountDownLatch count = new CountDownLatch(tableNum);

        //将吃饭人数放入队列(吃饭的人进行排队)
        for (int i = 1; i <= peopleNum; i++) {
            //offer是往队列添加元素，poll是从队列取出元素并且删除该元素
            queue.offer("消费者_" + i);
        }

        //执行10个线程从队列取出元素(10个桌子开始供饭)
        System.out.println("========================开饭了===============================");
        long start = currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(tableNum);

        for (int i = 0; i < tableNum; i++) {
            executorService.submit(new Dinner("00" + (i+1), queue, count));
        }

        //计数器等待，直到队列为空(所有人吃完)
        count.await();

        long time = currentTimeMillis() - start;
        System.out.println("========================所有人已经吃完===============================");
        System.out.println("共耗时 : " + time);

        //停止线程池
        executorService.shutdown();
    }

}
