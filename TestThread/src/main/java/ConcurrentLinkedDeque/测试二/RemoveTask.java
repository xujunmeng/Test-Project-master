package ConcurrentLinkedDeque.测试二;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author james
 * @date 2018/8/21
 */
public class RemoveTask implements Runnable {

    private ConcurrentLinkedDeque<String> list;

    public RemoveTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            list.pollFirst();
            list.pollLast();
        }
    }
}
