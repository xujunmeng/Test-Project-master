package 优先级队列;

/**
 * Created by jgsoft on 2019/2/24.
 */
public class PriorityQ {

    //array in sorted order, from max at 0 to min at size-1
    private int maxSize;

    private long[] queArray;

    private int nItems;

    public PriorityQ(int maxSize) {
        this.maxSize = maxSize;
        queArray = new long[maxSize];
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
        return nItems != maxSize;
    }

    public void insert(long item) {

        if (isFull()) {
            throw new RuntimeException("优先级队列已满");
        }

        int j;

        if (isEmpty()) {
            queArray[nItems] = item;
            nItems = nItems + 1;
        } else {
            for (j = nItems - 1; j > 0 ; j--) {
                if (item > queArray[j]) {
                    queArray[j+1] = queArray[j];
                } else {
                    break;
                }
            }
            queArray[j+1] = item;
            nItems = nItems + 1;
        }
    }

    public long remove() {
        if (isEmpty()) {
            throw new RuntimeException("优先级队列为空");
        }
        nItems = nItems - 1;
        return queArray[nItems];
    }

    public long peekMin() {
        return queArray[nItems - 1];
    }


}
