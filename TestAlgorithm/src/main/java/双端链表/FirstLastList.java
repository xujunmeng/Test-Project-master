package 双端链表;

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

    public void insertFirst(long dData) {

        Link newLink = new Link(dData);

        if (isEmpty()) {
            last = newLink;
        }

        newLink.setNext(first);

        first = newLink;

    }

    public void insertLast(long dData) {

        Link newLink = new Link(dData);

        if (isEmpty()) {
            first = newLink;
        } else {
            //first last 对应同一个next，所以做了修改之后，都会改变
            last.setNext(newLink);
        }

        last = newLink;
    }

    public long deletFirst() {
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
