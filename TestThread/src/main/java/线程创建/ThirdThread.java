package 线程创建;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 实现Callable接口和实现Runable接口并没有太大的差别
 * 只是Callable的call()方法允许声明抛出异常，允许带返回值
 * @author james
 * @date 2018/8/31
 */
public class ThirdThread {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            int i = 1;
            for (; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "====" + i);
            }
            return i;
        });

        new Thread(task, "有返回值的线程").start();

        System.out.println(Thread.currentThread().getName() + "========" + task.get());
    }

}
