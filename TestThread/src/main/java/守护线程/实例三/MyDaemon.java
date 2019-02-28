package 守护线程.实例三;

/**
 * @author james
 * @date 2018/9/3
 */
public class MyDaemon implements Runnable{

    @Override
    public void run() {
        for(long i = 0 ; i < 999999L ; i++){
            System.out.println("守护线程第 : " + i+ "次执行!!");
            try {
                Thread.sleep(7);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
