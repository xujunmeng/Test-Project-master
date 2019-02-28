package 队列.用链表实现的队列;

/**
 * Created by jgsoft on 2019/2/25.
 */
public class LinkQueue {

    private FirstLastList theList;

    public LinkQueue() {
        theList = new FirstLastList();
    }

    public boolean isEmpty() {
        return theList.isEmpty();
    }

    public void insert(long dData) {
        theList.insertLast(dData);
    }

    public long remove() {
        return theList.deleteFirst();
    }

    public void displayQueue() {
        theList.displayList();
    }

}
