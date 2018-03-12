package 适配器模式.对象适配器模式;

/**
 * Created by james on 2017/7/12.
 */
public class Adapter {

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    //源类Adaptee有的方法sampleOperation1,因此适配器类直接委派即可
    public void sampleOperation1() {
        this.adaptee.sampleOperation1();
    }

    //源类Adaptee没有的方法sampleOperation2,因此由适配器类需要补充此方法
    public void sampleOperation2() {
        //写相关的代码
        System.out.println("Adapter.sampleOperation2()");
    }
}
