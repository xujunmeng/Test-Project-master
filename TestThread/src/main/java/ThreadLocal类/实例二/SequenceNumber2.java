package ThreadLocal类.实例二;

/**
 * Created by james on 2017/11/28.
 */
public class SequenceNumber2 {

    //通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    //获取下一个序列之值
    public int getNextNum() {
        seqNum.set(seqNum.get()+1);
        return seqNum.get();
    }

    public static void main(String[] args) {
        SequenceNumber2 sn = new SequenceNumber2();
        //三个线程共享sn，各自产生序列号
        TestClient t1 = new TestClient(sn);
        t1.start();

    }

    private static class TestClient extends Thread{
        private SequenceNumber2 sn;

        public TestClient(SequenceNumber2 sn) {
            this.sn = sn;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("Thread.currentThread().getName() : " + Thread.currentThread().getName() + ", sn.getNextNum() : " + sn.getNextNum());
            }
        }
    }

}
