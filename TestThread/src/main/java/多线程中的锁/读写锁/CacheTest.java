package 多线程中的锁.读写锁;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 设计一个缓存系统
 * 读写锁的应用。
 * JDK1.5自带的读写锁特性，读与读不互斥，读与写互斥，写与写互斥。
 * 为什么要使用读写锁？一句话概括那就是提高系统性能，如何提高呢？
 * 试想，对于所有对读的操作是不需要线程互斥的，而如果方法内
 * 使用了synchronized关键字同步以达到线程安全，对于所有的线程不管是读还是写的操作都要同步。
 * 这时如果有大量的读操作时就会又性能瓶颈。
 *
 * 所以，当一个方法内有多个线程访问，并且方法内有读和写读操作时，
 * 提升性能最好的线程安全办法时采用读写锁的机制对读写互斥、写写互斥。这样对于读读就没有性能问题了
 *
 * @author james
 * @date 2019/9/19
 */
public class CacheTest {

    //缓存的map
    private Map<String, Object> map = new HashMap<>();

    //读写锁对象
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 从缓存中获取数据的方法
     */
    public Object getData(String key) {
        //读锁，只对写的线程互斥
        readWriteLock.readLock().lock();

        Object value = null;
        try {
            //尝试从缓存中获取数据
            value = map.get(key);
            if (value == null) {

                //发现目标值为null,释放掉读锁
                readWriteLock.readLock().unlock();

                //发现目标值为null,需要写值操作，上写锁
                readWriteLock.writeLock().lock();

                try {
                    //很严谨这一步，再次取目标值
                    value = map.get(key);

                    //很严谨这一步,再次判断目标值，防止读锁释放后，后面获得写锁的线程再次进行写值操作
                    if (value == null) {
                        //模拟DB操作
                        value = new Random().nextInt(10000) + "test";
                        map.put(key, value);
                        System.out.println("db completed !");
                    }

                    //再次对读进行锁定，防止写的操作，造成数据错乱
                    readWriteLock.readLock().lock();
                } finally {
                    readWriteLock.writeLock().unlock();
                }
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
        return value;
    }

    public static void main(String[] args) {
        CacheTest cacheTest = new CacheTest();
        for (int i = 0; i < 10000; i++) {
            new Thread() {
                @Override
                public void run() {
                    System.out.println(cacheTest.getData("user"));
                }
            }.start();
        }
    }

}
