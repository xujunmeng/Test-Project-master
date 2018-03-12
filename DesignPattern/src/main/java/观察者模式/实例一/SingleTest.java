package 观察者模式.实例一;

/**
 * Created by james on 2017/7/7.
 * 测试类SingleTest，在这里将观察者加入到被观察者的观察列表中。
 */
public class SingleTest {

    public static void main(String[] args) {

        NumObservable number = new NumObservable();

        number.addObserver(new NumObserver());

        number.setData(1);

        number.setData(2);

        number.setData(3);

    }
}
