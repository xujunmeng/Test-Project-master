package ThreadLocal类.实例三;

/**
 * Created by james on 2017/5/27.
 */
public class MyTest extends Thread{

    //定义一个Account类型的成员变量
    private Account account;

    public MyTest(Account account, String name){
        super(name);
        this.account = account;
    }

    @Override
    public void run() {
        //循环10次
        for (int i = 0; i < 10; i++) {
            //当i == 6时输出 将账户名替换成当前线程名
            if(i == 6){
                account.setName(getName());
            }
            //输出同一个账户的账户名和循环变量
            System.out.println("account.getName() : " + account.getName() + " 账户的i值 :  " + i);

        }
    }
}