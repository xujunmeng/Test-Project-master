package 观察者模式.实例一;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by james on 2017/7/7.
 * NumObserver是观察者。当它的被观察者(NumObserable)执行了notifyObservers()后，它会执行uodate()方法。
 */
public class NumObserver implements Observer {

    public void update(Observable o, Object arg) {

        NumObservable myObserable=(NumObservable) o;

        System.out.println("Data has changed to " +myObserable.getData());

    }
}
