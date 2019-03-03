
Object类提供wait(), notify()和notifyAll()三个方法。这三个方法必须由同步监视器对象来调用。这可分成以下两种情况：

1：对于使用synchronized修饰的同步方法，因为该类的默认实例(this)就是同步监视器，所以可以在同步方法中
直接调用这三个方法。
2：对于使用synchronized修饰的同步代码块，同步监视器是synchronized后括号里的对象，所以必须使用该对象调用这三个方法。

如果程序中不使用synchronized关键字来保证同步，而是直接使用Lock对象来保证同步，则系统中不存在隐式的同步监视器，
也就不能使用wait()、notify()、notifyAll()方法进行线程通信。

Condition将同步监视器方法(wait(), nofity(), nofityAll())分解成截然不同的对象，以便通过将这些对象与Lock对象组合使用，
为每个对象提供多个等待集(wait-set)。在这种情况下，Lock替代了同步方法或同步代码块，Condition替代了同步监视器的功能