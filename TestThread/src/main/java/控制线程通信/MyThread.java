package 控制线程通信;

/**
 * @author james
 * @date 2018/9/25
 */
public class MyThread extends Thread {

    /**
     * 提供指定线程名的构造器
     * @param name
     */
    public MyThread(String name) {
        super(name);
    }

    /**
     * 提供指定线程名，线程组的构造器
     */
    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {

            try {
                int m = 1/0;
            } catch (Exception e) {
                System.out.println("抛出了异常,hahahaha");
                e.printStackTrace();
            }
            System.out.println(getName() + " 线程的i变量 " + i);
        }
    }
}
