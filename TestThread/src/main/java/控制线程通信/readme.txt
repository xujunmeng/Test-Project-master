java使用ThreadGroup来表示线程组，他可以对一批线程进行分类管理

1：如果程序没有显式指定线程属于那个线程组，则该线程属于默认线程组
2：在默认情况下，子线程和创建它的父线程处于同一个线程组内

ThreadGroup定义了一个方法：
void uncaughtException(Thread t, Throwable e)
可以处理该线程组内的任意线程所抛出的未处理异常