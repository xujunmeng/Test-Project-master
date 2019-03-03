package 控制线程.join线程;

/**
 *
 * Thread提供了让一个线程等待另一个线程完成的方法，join()方法。
 * 当在某个程序执行流中调用其他线程的join()方法时，调用线程将被阻塞，直到被join()方法加入的join线程执行完为止。
 *
 * join()方法通常由使用线程的程序调用，以将大问题划分成许多小问题，每个小问题分配一个线程。
 * 当所有的小问题都得到处理后，再调用主线程来进一步操作。
 *
 *
 * 当主线程的循环变量i等于2时，启动了名为'被join的线程'的线程，该线程不会和main线程并发执行，main线程必须等该线程执行结束后
 * 才可以向下执行。
 *
 *
 *
 * @author james
 * @date 2018/9/3
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        //启动子线程
        //new JoinThread("新线程").start();

        for (int i = 0; i < 5; i++) {
            if(i == 2){
                JoinThread jt = new JoinThread("被Join的线程");
                jt.start();
                //main线程 调用了 jt线程 的join方法，main线程必须等jt执行结束才会向下执行

                //Waits for this thread to die. 等待这个线程死亡！！！！
                jt.join();
            }

            //这个是主线程（main线程）打印的日志
            System.out.println(Thread.currentThread().getName() + "   " + i);
        }

    }

}
