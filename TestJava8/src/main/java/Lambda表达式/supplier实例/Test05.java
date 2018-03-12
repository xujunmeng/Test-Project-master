package Lambda表达式.supplier实例;

import java.util.function.BooleanSupplier;

/**
 * Created by james on 2017/10/24.
 */
public class Test05 {

    public static void main(String[] args) {

        BooleanSupplier bs = () -> true;//没有参数  但是有返回结果
        System.out.println(bs.getAsBoolean());

        int x = 0, y= 1;
        bs = () -> x > y;
        System.out.println(bs.getAsBoolean());

    }

}
