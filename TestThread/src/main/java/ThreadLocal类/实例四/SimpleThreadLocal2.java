package ThreadLocal类.实例四;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by james on 2017/11/28.
 */
public class SimpleThreadLocal2 {

    private Map<Thread, Integer> valueMap = Collections.synchronizedMap(new HashMap<>());

    public void set(Integer newValue) {
        //键为线程对象，值为本线程的变量副本
        valueMap.put(Thread.currentThread(), newValue);
    }

    public Integer get(Thread thread) {
        //返回本线程对应的变量
        Integer o = valueMap.get(thread);
        //如果在Map中不存在，放到Map中保存起来
        if (o == null && !valueMap.containsKey(thread)) {
            o = initialValue();
            valueMap.put(thread, o);
        }
        return o;
    }

    //获取下一个序列之值
    public int getNextNum(Thread thread) {
        get(thread);
        set(valueMap.get(thread)+1);
        return get(thread);
    }

    public void remove() {
        valueMap.remove(Thread.currentThread());
    }

    public Integer initialValue() {
        return 0;
    }


    public static void main(String[] args) {
        SimpleThreadLocal2 sn = new SimpleThreadLocal2();
        //三个线程共享sn，各自产生序列号
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();

    }

    private static class TestClient extends Thread{
        private SimpleThreadLocal2 simpleThreadLocal2;

        public TestClient(SimpleThreadLocal2 simpleThreadLocal2) {
            this.simpleThreadLocal2 = simpleThreadLocal2;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("Thread.currentThread().getName() : " + Thread.currentThread().getName() + ", sn.getNextNum() : " + simpleThreadLocal2.getNextNum(Thread.currentThread()));
            }
        }
    }

}
