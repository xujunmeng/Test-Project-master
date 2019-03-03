package 实例八切点函数详解;

/**
 * @author james
 * @date 2018/8/4
 */
@NeedClassTest
public class ClassLevelWaiter implements Waiter {

    @Override
    public void greetTo(String clientName) {
        System.out.println("ClassLevelWaiter:greet to "+clientName+"...");
    }

    @Override
    public void serveTo(String clientName) {
        int i = 1/0;
        System.out.println("ClassLevelWaiter:serve to "+clientName+"...");
    }
}
