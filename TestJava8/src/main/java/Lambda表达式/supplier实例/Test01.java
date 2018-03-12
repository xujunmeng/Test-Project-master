package Lambda表达式.supplier实例;

import org.junit.Test;

import java.util.function.Supplier;

/**
 * Created by james on 2017/10/24.
 */
public class Test01 {

    @Test
    public void test1() {
        Supplier<String> supplier = () -> "Supplier";//定义了输出结果 因此没有必要重写toString()
        System.out.println(supplier.get());
    }

}
