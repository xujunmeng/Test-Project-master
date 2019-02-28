package 栈.链表实现栈数据结构;

/**
 * Created by jgsoft on 2019/2/25.
 */
public class Main {

    public static void main(String[] args) {
        LinkStack theStack = new LinkStack();
        theStack.push(20);
        theStack.push(40);
        theStack.push(10);
        theStack.push(30);
        theStack.displayStack();
    }

}
