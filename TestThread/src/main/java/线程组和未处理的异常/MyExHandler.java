package 线程组和未处理的异常;

/**
 *
 * 定义自己的异常处理器
 *
 * @author james
 * @date 2018/9/25
 */
public class MyExHandler implements Thread.UncaughtExceptionHandler {

    /**
     * 实现uncaughtException（）方法，该方法将处理线程的未处理异常
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("t : " + t + " 线程出现了异常 : " + e);
    }
}
