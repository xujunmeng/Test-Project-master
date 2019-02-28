package 线程组和未处理的异常;

/**
 * @author james
 * @date 2018/9/25
 */
public class Main {

    public static void main(String[] args) {

        //设置主线程的异常处理器
        Thread.currentThread().setUncaughtExceptionHandler(new MyExHandler());

        int a = 5 / 0;

        System.out.println("程序正常结束");

    }

}
