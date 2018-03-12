package 适配器模式.对象适配器模式;

/**
 * Created by james on 2017/7/12.
 */
public class Test {

    public static void main(String[] args) {

        Adapter adapter = new Adapter(new Adaptee());

        adapter.sampleOperation1();

        adapter.sampleOperation2();

    }

}
