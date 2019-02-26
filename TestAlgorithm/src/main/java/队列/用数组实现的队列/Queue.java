package 队列.用数组实现的队列;

/**
 * Created by jgsoft on 2019/2/24.
 */
public class Queue {

    private int maxSize;

    private Object[] queArray;

    private int front;

    private int rear;

    private int nItems;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        queArray = new Object[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    public boolean isNotEmpty() {
        return nItems != 0;
    }

    public boolean isFull() {
        return nItems == maxSize;
    }

    public boolean isNotFull() {
        return  nItems != maxSize;
    }

    public int size() {
        return nItems;
    }

    public void insert(Object j) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }

        if (rear == maxSize - 1) {
            rear = -1;
        }

        rear = rear + 1;

        queArray[rear] = j;

        nItems = nItems + 1;
    }

    public Object remove() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }

        Object obj = queArray[front];
        queArray[front] = null;
        front = front + 1;

        if (front == maxSize) {
            front = 0;
        }

        nItems = nItems - 1;

        return obj;
    }

    public Object peekFront() {
        return queArray[front];
    }



}
