package ConcurrentLinkedQueue.测试用例一;

import org.junit.Test;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 定义
 一个基于链接节点的无界线程安全队列。此队列按照 FIFO（先进先出）原则对元素进行排序。队列的头部 是队列中时间最长的元素。队列的尾部 是队列中时间最短的元素。
 新的元素插入到队列的尾部，队列获取操作从队列头部获得元素。当多个线程共享访问一个公共 collection 时，ConcurrentLinkedQueue 是一个恰当的选择。此队列不允许使用 null 元素。
 offer和poll
 offer(E e)
 将指定元素插入此队列的尾部。

 poll()
 获取并移除此队列的头，如果此队列为空，则返回 null。
 *
 * offer是往队列添加元素，poll是从队列取出元素并且删除该元素
 * ConcurrentLinkedQueue中的add() 和 offer() 完全一样，都是往队列尾部添加元素
 *
 * peek()
 获取但不移除此队列的头；如果此队列为空，则返回 null
 *
 *size or isEmpty
 size()
 返回此队列中的元素数量

 注意：

 如果此队列包含的元素数大于 Integer.MAX_VALUE，则返回 Integer.MAX_VALUE。
 需要小心的是，与大多数 collection 不同，此方法不是 一个固定时间操作。由于这些队列的异步特性，确定当前的元素数需要进行一次花费 O(n) 时间的遍历。
 所以在需要判断队列是否为空时，尽量不要用 queue.size()>0，而是用 !queue.isEmpty()
 比较size()和isEmpty() 效率的示例：

 场景：10000个人去饭店吃饭，10张桌子供饭，分别比较size() 和 isEmpty() 的耗时
 *
 *
 * contains
 contains(Object o)
 如果此队列包含指定元素，则返回 true
 *
 *toArray()
 返回以恰当顺序包含此队列所有元素的数组

 toArray(T[] a)
 返回以恰当顺序包含此队列所有元素的数组；返回数组的运行时类型是指定数组的运行时类型
 *
 *
 * iterator()
 返回在此队列元素上以恰当顺序进行迭代的迭代器
 *
 *
 * @author james
 * @date 2018/8/20
 */
public class Main {

    @Test
    public void test1() {
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        queue.offer("哈哈哈");

        System.out.println("offer后，队列是否空? : " + queue.isEmpty());
        System.out.println("从队列中poll : " + queue.poll());
        System.out.println("pool后，队列是否空? : " + queue.isEmpty());
    }

    @Test
    public void test2() {
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        queue.offer("哈哈哈");
        System.out.println("offer后，队列是否空？" + queue.isEmpty());
        System.out.println("从队列中peek：" + queue.peek());
        System.out.println("从队列中peek：" + queue.peek());
        System.out.println("从队列中peek：" + queue.peek());
        System.out.println("pool后，队列是否空？" + queue.isEmpty());
    }

    @Test
    public void test3() {
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        queue.offer("哈哈哈");
        System.out.println("offer后，队列是否空？" + queue.isEmpty());
        System.out.println("从队列中remove已存在元素 ：" + queue.remove("哈哈哈"));
        System.out.println("从队列中remove不存在元素：" + queue.remove("123"));
        System.out.println("remove后，队列是否空？" + queue.isEmpty());
    }

    @Test
    public void test4() {
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        queue.offer("123");
        System.out.println(queue.contains("123"));
        System.out.println(queue.contains("456"));
    }

    @Test
    public void test5() {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
        queue.offer("123");
        queue.offer("234");
        Object[] objects = queue.toArray();
        System.out.println(objects[0] + ", " + objects[1]);

        //将数据存储到指定数组
        String[] strs = new String[2];
        queue.toArray(strs);
        System.out.println(strs[0] + ", " + strs[1]);
    }

    @Test
    public void test6() {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
        queue.offer("123");
        queue.offer("234");
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
