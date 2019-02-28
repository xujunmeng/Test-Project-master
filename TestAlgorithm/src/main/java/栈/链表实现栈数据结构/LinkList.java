package 栈.链表实现栈数据结构;

/**
 * Created by jgsoft on 2019/2/25.
 */
public class LinkList {

    private Link first;

    public LinkList() {
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(long dData) {
        Link newLink = new Link(dData, first);
        first = newLink;
    }

    public long deleteFirst() {
        Link temp = first;

        first = first.getNext();

        return temp.getdData();

    }

    public void displayList() {
        Link current = first;

        while (current != null) {
            current.displayLink();
            current = current.getNext();
        }
    }

}
