package 线程通信.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author james
 * @date 2018/9/20
 */
public class Account {

    /**
     * 显式定义Lock对象
     */
    private final Lock lock = new ReentrantLock();

    /**
     * 获的指定Lock对象对应的Condition
     */
    private final Condition condition = lock.newCondition();

    /**
     * 账号编号
     */
    private String accountNo;

    /**
     * 账户余额
     */
    private double balance;

    /**
     * 标识账户中是否已有存款的旗帜
     */
    private boolean flag = false;

    public Account() {
    }

    public Account(String accountNo, double balance, boolean flag) {
        this.accountNo = accountNo;
        this.balance = balance;
        this.flag = flag;
    }

    public void draw(double drawAmount) {
        //加锁
        lock.lock();
        try {
            //如果flag为假，表明账户中还咩有人存钱进去，取钱方法阻塞
            if (!flag) {
                condition.await();
            } else {
                //执行取钱操作
                System.out.println(Thread.currentThread().getName() + " 取钱 : " + drawAmount);
                balance -= drawAmount;
                System.out.println(Thread.currentThread().getName() + " 账户余额为 : " + balance);

                //将标识账户是否已有存款的旗标设为false
                flag = false;

                //唤醒其他线程
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //使用finally块来释放锁
        finally {
            lock.unlock();
        }
    }

    public void deposit(double depositAmount) {

        lock.lock();
        try {
            //如果flag为真，表明账户中已有人存钱进去，存钱方法阻塞
            if (flag) {
                condition.await();
            } else {
                //执行存款操作
                System.out.println(Thread.currentThread().getName() + " 存款 : " + depositAmount);
                balance += depositAmount;

                //表示账户是否已有存款的旗帜设为true
                flag = true;

                //唤醒其他线程
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        finally {
            lock.unlock();
        }
    }

}
