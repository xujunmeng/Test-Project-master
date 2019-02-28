package yield.实例一;

/**
 * @author james
 * @date 2018/9/3
 */
public class Main {

    public static void main(String[] args) {

        //启动两条并发线程
        TestYield1 t1 = new TestYield1("高级");
        //将t1线程设置成最高优先级
		t1.setPriority(Thread.MAX_PRIORITY);

        TestYield1 t2 = new TestYield1("低级");
        //将t2线程设置成最低优先级
		t2.setPriority(Thread.MIN_PRIORITY);

        t2.start();
        t1.start();


    }

}
