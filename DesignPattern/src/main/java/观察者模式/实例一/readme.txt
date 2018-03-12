实现观察者模式非常简单，
[1]创建被观察者类，它继承自java.util.Observable类；
[2]创建观察者类，它实现java.util.Observer接口；
[3]对于被观察者类，
添加它的观察者：
void addObserver(Observer o)
addObserver()方法把观察者对象添加到观察者对象列表中。

当被观察事件发生时，执行：
setChanged();
notifyObservers();

setChange()方法用来设置一个内部标志位注明数据发生了变化；
notifyObservers()方法会去调用观察者对象列表中所有的Observer的update()方法，通知它们数据发生了变化。
只有在setChange()被调用后，notifyObservers()才会去调用update()。

[4]对于观察者类，实现Observer接口的唯一方法update
void update(Observable o, Object arg)
形参Object arg，对应一个由notifyObservers(Object arg);传递来的参数，当执行的是notifyObservers();时，arg为null。