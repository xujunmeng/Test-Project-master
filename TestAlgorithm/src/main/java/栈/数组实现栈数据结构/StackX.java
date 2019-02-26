package 栈.数组实现栈数据结构;

/**
 * Created by jgsoft on 2019/2/23.
 */
public class StackX {

    private int maxSize;

    private Object[] stackArray;

    private int top;

    public StackX(int maxSize) {
        this.maxSize = maxSize;
        this.stackArray = new Object[maxSize];
        this.top = -1;
    }

    /**
     * 入栈动作
     */
    public void push(Object val) {
        top = top + 1;
        stackArray[top] = val;
    }

    /**
     * 出栈动作
     */
    public Object pop() {
        Object val = stackArray[top];
        top = top - 1;
        return val;
    }

    /**
     * 查看动作,只能查看最上面的值
     */
    public Object peek() {
        return stackArray[top];
    }

    /**
     * 判断栈是否为空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 判断栈是否不为空
     */
    public boolean isNotEmpty() {
        return top != -1;
    }

    /**
     * 判断栈是否满了
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈是否没有满
     */
    public boolean isNotFull() {
        return top != maxSize - 1;
    }
}
