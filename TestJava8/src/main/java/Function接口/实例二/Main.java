package Function接口.实例二;

import org.junit.Test;

/**
 * @author james
 * @date 2018/8/27
 */
public class Main {

    private static String hello = "Nice to meet you";
    private static String name = "my name is huohuo";

    @Test
    public void test() {
        MyFunction myFunction = new MyFunction();
        Integer apply = myFunction.apply(hello);
        System.out.println(apply);
    }

    @Test
    public void test2() {
        MyBiFunction biFunction = new MyBiFunction();
        String apply = biFunction.apply(hello, name);
        System.out.println(apply);
    }

}
