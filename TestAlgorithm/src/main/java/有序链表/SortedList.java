package 有序链表;

/**
 * Created by jgsoft on 2019/2/25.
 */
public class SortedList {

    private Link first;

    public SortedList() {
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insert(long key) {
        Link newLink = new Link(key);
        Link previous = null;
        Link current = first;

        while (current != null && key > current.getdData()) {
            previous = current;
            current = current.getNext();
        }

        if (previous == null) {
            first = newLink;
        } else {
            previous.setNext(newLink);
        }
        newLink.setNext(current);
    }

    public Link remove() {
        Link temp = first;
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
