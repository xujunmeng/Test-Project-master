package 队列.用链表实现的队列;

/**
 * Created by jgsoft on 2019/2/25.
 */
public class Main {

    public static void main(String[] args) {
        LinkQueue theQueue = new LinkQueue();
        theQueue.insert(20);
        theQueue.insert(40);
        theQueue.insert(60);
        theQueue.insert(70);
        theQueue.displayQueue();
    }

}
