package ConcurrentLinkedDeque.测试二;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 *
 * 在Java中,最常用的数据结构可能是列表。有数目不详的元素列表,你可以添加、阅读、或删除任何位置的元素。此外,并发列表允许不同的线程列表中添加或删除元素时不产生任何数据不一致。非阻塞列表提供如下操作,如果操作不能立即完成,列出抛出异常或者返回一个null值。Java 7中引入了ConcurrentLinkedDeque类,它实现了一个非阻塞并发列表，在本教程中,我们将学习使用这个类。

 在这个例子中,我们将实现一个示例使用以下两个不同的任务:

 一个将大量数据添加到一个列表中
 一个大量地从同样的列表中删除数据
 让我们为每个任务创建的线程：
 *
 *
 * @author james
 * @date 2018/8/21
 */
public class AddTask implements Runnable {

    private ConcurrentLinkedDeque<String> list;

    public AddTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10000; i++) {
            list.add(name + " : Element : " + i);
        }
    }
}
