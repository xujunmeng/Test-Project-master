package 责任链模式.实例二;

/**
 * Created by james on 2017/9/18.
 */
public class ConcreteHandler extends Handler {

    @Override
    public void doHandler() {

        if (getNextHandler() != null) {

            System.out.println("还有责任链");

            getNextHandler().doHandler();

        } else {
            System.out.println("我自己处理" + toString());
        }

    }
}
