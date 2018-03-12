package 适配器模式.类适配器模式;

/**
 * Created by james on 2017/7/12.
 */
public interface Target {

    //这是源类Adaptee也有的方法
    void sampleOperation1();

    //这是源类Adaptee没有的方法
    void sampleOperation2();

}
