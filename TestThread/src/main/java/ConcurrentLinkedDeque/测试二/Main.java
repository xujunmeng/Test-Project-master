package ConcurrentLinkedDeque.测试二;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 *
 * 让我们看看它如何工作:

 首先,你执行100个 AddTask任务将元素添加到任务列表。每一个任务使用 add()方法插入10000个元素到列表，新增加的元素都会放到列表最后。当所有这些任务已经完成了,你会在控制台打印出了列表的元素数量。这时,这个列表有1000000个元素。
 然后,你执行100个 RemoveTask任务将元素从列表中删除。每一个任务删除这个列表 用pollFirst()和pollLast()方法。pollFirst()方法返回和删除列表的第一个元素和pollLast()方法返回和删除最后一个元素的列表。如果列表为空,这些方法返回一个null值。当所有这些任务已经完成了,在控制台的打印出列表中元素的数量，这时,有零元素列表。
 打印列表的元素的数量时,你使用了 size()方法，你必须考虑,这种方法并不是真实的,特别是如果你使用它在线程进行列表中添加同时又删除数据。计数的方法遍历整个列表的元素和内容列表可以改变这个操作。一旦在你使用它们时没有任何线程修改列表,你可以保证返回的结果是正确的。
 请注意, ConcurrentLinkedDeque类提供了更多的方法来获取元素列表形式:

 getFirst()和 getLast():这些方法返回分别从列表中第一个和最后一个元素。他们不会从列表中删除返回的元素。如果列表是空的,这些方法抛出一个 NoSuchElementExcpetion例外。
 peek(), peekFirst(), peekLast():这些方法返回列表的第一个和最后一个元素。他们不会从列表中删除返回的元素。如果列表为空,这些方法返回一个null值。
 remove(), removeFirst(), removeLast():这些方法返回列表的第一个和最后一个元素。他们从列表中移除返回的元素。如果列表是空的,这些方法抛出一个 NoSuchElementException例外。
 一个 ConcurrentLinkedDeque是一个合适的选择,许多线程共享访问公共集合。
 像大多数其他并发集合实现,这个类不允许null元素的使用。
 迭代器是弱一致的,返回元素反映在一些点或双端队列的状态,因为迭代器的创建。他们不把ConcurrentModificationException与其他操作,可以同时进行。
 *
 * @author james
 * @date 2018/8/21
 */
public class Main {

    public static void main(String[] args)
    {
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
        Thread threads[] = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            AddTask task = new AddTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        System.out.printf("Main: %d AddTask threads have been launched\n", threads.length);

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Main: Size of the List: %d\n", list.size());

        for (int i = 0; i < threads.length; i++) {
            RemoveTask task = new RemoveTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        System.out.printf("Main: %d RemoveTask threads have been launched\n", threads.length);

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Main: Size of the List: %d\n", list.size());
    }

}
