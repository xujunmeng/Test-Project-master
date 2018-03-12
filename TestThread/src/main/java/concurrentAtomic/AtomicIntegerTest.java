package concurrentAtomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by james on 2017/5/27.
 */
public class AtomicIntegerTest {

    static AtomicInteger ai = new AtomicInteger(2);

    public static void main(String[] args) {
        // 实例化了一个AtomicInteger类的对象atomic并定义初始值为1
        System.out.println(ai.getAndIncrement());
        // 进行atomic的原子化操作：增加1并且获取这个增加后的新值
        System.out.println(ai.get());


    }

}
