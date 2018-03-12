package ThreadLocal类.实例二;

/**
 * Created by james on 2017/8/25.
 */
public class SequenceNumber {

    //通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
    private static ThreadLocal<Integer> seqNumThreadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    //获取下一个序列的值
    public int getNextNum() {
        seqNumThreadLocal.set(seqNumThreadLocal.get() + 3);
        return seqNumThreadLocal.get();
    }

    public static void main(String[] args) {
        SequenceNumber sn = new SequenceNumber();
        //三个线程共享sn，各自产生序列号
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();

    }

    private static class TestClient extends Thread{
        private SequenceNumber sn;

        public TestClient(SequenceNumber sn) {
            this.sn = sn;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("thread[" + Thread.currentThread().getName() + "]sn[" + sn.getNextNum()+"]");
            }
        }
    }

}
