package 线程创建;

/**
 * @author james
 * @date 2018/8/31
 */
public class SecondThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "=========" + i);
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "=========");
        SecondThread st = new SecondThread();
        Thread thread1 = new Thread(st, "线程1");
        Thread thread2 = new Thread(st, "线程2");

        boolean alive = thread1.isAlive();
        System.out.println("线程1, start before, alive : " + alive);

        boolean alive2 = thread2.isAlive();
        System.out.println("线程2, start before, alive : " + alive2);

        thread1.start();
        thread2.start();
    }
}
