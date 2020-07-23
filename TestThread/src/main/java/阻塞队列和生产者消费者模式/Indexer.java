package 阻塞队列和生产者消费者模式;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * @author james
 * @date 2019/3/22
 */
public class Indexer implements Runnable {

    private final BlockingQueue<File> blockingQueue;

    public Indexer(BlockingQueue<File> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                File file = blockingQueue.take();
                if (!file.exists()) {
                    break;
                } else {
                    indexFile(file);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void indexFile(File file) {
        System.out.println(Thread.currentThread().getName() + "=========="+ file.getName());
    }
}
