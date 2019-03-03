package 线程创建;

/**
 * 通过继承Thread类来创建线程类
 *
 *
 * @author james
 * @date 2018/8/31
 */
public class FirstThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + "=========" + i);
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "=========");
        new FirstThread().start();
        new FirstThread().start();
    }
}
