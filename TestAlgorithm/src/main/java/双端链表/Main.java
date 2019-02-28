package 双端链表;

/**
 * Created by jgsoft on 2019/2/25.
 */
public class Main {

    public static void main(String[] args) {
        FirstLastList theList = new FirstLastList();
        theList.insertFirst(22);
        theList.insertFirst(44);
        theList.insertFirst(66);

        theList.insertLast(11);
        theList.insertLast(33);
        theList.insertLast(55);
        theList.insertLast(77);

        theList.displayList();
    }

}
