package Lambda表达式.supplier实例;

import java.util.function.Supplier;

/**
 * Created by james on 2017/10/24.
 */
public class Test02 {

    public static SunPower produce(Supplier<SunPower> supp) {
        return supp.get();
    }

    public static void main(String[] args) {

        SunPower sunPower = new SunPower();

        SunPower p1 = produce(() -> sunPower);//只会初始化一次，因此只会输出一个结果
        SunPower p2 = produce(() -> sunPower);

        System.out.println(p1);
        System.out.println(p2);


    }

}
class SunPower {
    public SunPower() {
        System.out.println("Sun Power initialized...");
    }
}