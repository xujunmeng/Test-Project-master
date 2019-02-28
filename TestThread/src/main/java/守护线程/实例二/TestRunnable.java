package 守护线程.实例二;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author james
 * @date 2018/9/3
 */
public class TestRunnable implements Runnable {

    @Override
    public void run() {
        try {
            //守护线程阻塞1秒后运行
            Thread.sleep(1000);
            File f = new File("D://test.txt");
            FileOutputStream os = new FileOutputStream(f, true);
            os.write("daemon".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
