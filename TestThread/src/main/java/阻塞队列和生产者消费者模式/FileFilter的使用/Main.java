package 阻塞队列和生产者消费者模式.FileFilter的使用;

import java.io.File;

/**
 * @author james
 * @date 2019/3/22
 */
public class Main {

    public static void main(String[] args) {
        File file = new File("D:\\爱回收资料");
        File[] files = file.listFiles(new MyFileFilter());
        for (File fileS : files) {
            System.out.println(fileS.getName());
        }

    }

}
