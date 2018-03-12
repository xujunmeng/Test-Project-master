package 观察者模式.实例一;

import java.util.Observable;

/**
 * Created by james on 2017/7/7.
 * NumObserable是一个被观察者，当它的成员变量data的数值发生变化时，会通知所有的观察者。
 */
public class NumObservable extends Observable {

    private int data = 0;

    public int getData() {

        return data;

    }

    public void setData(int i) {

        data = i;

        setChanged();

        notifyObservers();

    }
}
