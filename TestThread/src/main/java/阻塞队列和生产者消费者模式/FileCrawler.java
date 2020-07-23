package 阻塞队列和生产者消费者模式;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * @author james
 * @date 2019/3/22
 */
public class FileCrawler implements Runnable {

    private final BlockingQueue<File> fileBlockingQueue;

    private final FileFilter fileFilter;

    private final File root;

    public FileCrawler(BlockingQueue<File> fileBlockingQueue, File root) {
        this.fileBlockingQueue = fileBlockingQueue;
        this.root = root;
        this.fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return !pathname.isDirectory();
            }
        };
    }

    private boolean alreadyIndexed(File file) {
        if (fileBlockingQueue.contains(file)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void crawl(File root) throws InterruptedException {
        File[] files = root.listFiles(fileFilter);
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    crawl(file);
                } else if (!alreadyIndexed(file)) {
                    fileBlockingQueue.put(file);
                }
            }
        }
    }
}
