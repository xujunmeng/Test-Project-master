package 线程同步.死锁.demo;

/**
 * @author james
 * @date 2018/9/13
 */
public class B{

    public synchronized void bar( A a ) {
        System.out.println("当前线程名: "
                + Thread.currentThread().getName() + " 进入了B实例的bar方法" );
        try
        {
            Thread.sleep(10);
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }
        System.out.println("当前线程名: " + Thread.currentThread().getName() + " 企图调用A实例的last方法");
        a.last();
    }

    public synchronized void last()
    {
        System.out.println("进入了B类的last方法内部");
    }
}
