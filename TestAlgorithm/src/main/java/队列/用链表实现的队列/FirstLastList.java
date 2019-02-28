package 队列.用链表实现的队列;

/**
 * Created by jgsoft on 2019/2/25.
 */
public class FirstLastList {

    private Link first;

    private Link last;

    public FirstLastList() {
        this.first = null;
        this.last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertLast(long dData) {

        Link newLink = new Link(dData);

        if (isEmpty()) {
            first = newLink;
        } else {
            last.setNext(newLink);
        }
        last = newLink;
    }

    public long deleteFirst() {
        long temp = first.getdData();

        if (first.getNext() == null) {
            last = null;
        }
        first = first.getNext();
        return temp;
    }

    public void displayList() {
        Link current = first;
        while (current != null) {
            current.displayLink();
            current = current.getNext();
        }
    }

}
