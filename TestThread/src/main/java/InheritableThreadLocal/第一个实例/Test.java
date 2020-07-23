package InheritableThreadLocal.第一个实例;

/**
 * @author james
 * @date 2019/9/24
 */
public class Test {

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        threadLocal.set(new Integer(32));

        Thread thread = new MyThread();
        thread.start();

        System.out.println("main = " + threadLocal.get());


    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("MyThread = " + threadLocal.get());
        }
    }

}
