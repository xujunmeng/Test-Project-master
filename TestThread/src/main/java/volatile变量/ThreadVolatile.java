package volatile变量;

/**
 * @author james
 * @date 2019/6/5
 */
public class ThreadVolatile {

    public static void main(String[] args) throws InterruptedException {
        ThreadVolatileDemo threadVolatileDemo = new ThreadVolatileDemo();
        threadVolatileDemo.start();
        Thread.sleep(3000);
        threadVolatileDemo.setRuning(false);
        System.out.println(threadVolatileDemo.flag);
    }

}
