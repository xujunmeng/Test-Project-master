package 控制线程通信;

/**
 * @author james
 * @date 2018/9/25
 */
public class Main {

    public static void main(String[] args) {

        //获取主线程所在的线程组，这是所有线程默认的线程组
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println("主线程组的名字 : " + threadGroup.getName());
        System.out.println("主线程组是否是后台线程组 ： " + threadGroup.isDaemon());

        new MyThread(threadGroup, "猪猪线程").start();




    }

}
