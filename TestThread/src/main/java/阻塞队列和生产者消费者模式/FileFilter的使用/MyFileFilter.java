package 阻塞队列和生产者消费者模式.FileFilter的使用;

import java.io.File;
import java.io.FileFilter;

/**
 * @author james
 * @date 2019/3/22
 */
public class MyFileFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        String filename = pathname.getName().toLowerCase();
        if (filename.contains(".sql")) {
            return true;
        } else {
            return false;
        }
    }

}
