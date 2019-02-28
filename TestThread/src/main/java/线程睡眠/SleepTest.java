package 线程睡眠;

import org.junit.Test;

import java.util.Date;

/**
 *
 * 让当前正在执行的线程暂停一段时间，并进入阻塞状态
 *
 *
 * @author james
 * @date 2018/9/3
 */
public class SleepTest {

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println("当前时间 : " + new Date());
            Thread.sleep(5000);
        }
    }

}
