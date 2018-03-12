package ThreadLocal类.实例三;

/**
 * Created by james on 2017/5/27.
 */
public class Account {

    //定义一个ThreadLocal类型的变量，该变量将是一个线程局部变量，每个线程都会保留该变量的一个副本
    private ThreadLocal<String> name = new ThreadLocal<>();

    //定义一个初始化name属性的构造器
    public Account(String str){
        name.set(str);
        //下面代码用于访问当前线程的name副本的值
        System.out.println("--------" + name.get());
    }

    //定义name属性的setter和getter方法
    public String getName(){
        return name.get();
    }

    public void setName(String str){
        this.name.set(str);
    }
}
