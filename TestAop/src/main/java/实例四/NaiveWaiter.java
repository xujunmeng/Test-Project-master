package 实例四;

/**
 * Created by junmeng.xu on 2016/12/12.
 */
public class NaiveWaiter implements Waiter {

    @Override
    public void greetTo(String name) {
        System.out.println("greet to " + name + " ...");
    }

    @Override
    public void serveTo(String name) {
        System.out.println("serving " + name + " ...");
    }
}
