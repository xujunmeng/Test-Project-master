package 守护线程.实例三;

/**
 * @author james
 * @date 2018/9/3
 */
public class MyCommon implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("前台线程是第 : " + i + "次执行!!");
            try {
                Thread.sleep(7);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
