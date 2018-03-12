package volatile变量;

/**
 * Created by james on 2017/5/27.
 */
public class ReaderThread extends Thread{

    @Override
    public void run() {
        SafeThread.setNumber(1);
    }

}