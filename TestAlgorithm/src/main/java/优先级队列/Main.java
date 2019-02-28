package 优先级队列;

/**
 * Created by jgsoft on 2019/2/24.
 */
public class Main {

    public static void main(String[] args) {
        PriorityQ priorityQ = new PriorityQ(5);
        priorityQ.insert(50);
        priorityQ.insert(30);
        priorityQ.insert(10);
        priorityQ.insert(40);
        priorityQ.insert(20);

        while (priorityQ.isNotEmpty()) {
            long item = priorityQ.remove();
            System.out.println(item);
        }

    }

}
