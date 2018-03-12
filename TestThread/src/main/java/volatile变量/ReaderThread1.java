package volatile变量;

/**
 * Created by james on 2017/5/27.
 */
public class ReaderThread1 extends Thread{

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        System.out.println(" = " + SafeThread.getNumber());
        long end = System.currentTimeMillis();
        System.out.println("所用时间 : " + end);
    }
}
