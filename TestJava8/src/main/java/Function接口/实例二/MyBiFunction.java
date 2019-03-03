package Function接口.实例二;

import java.util.function.BiFunction;

/**
 * @author james
 * @date 2018/8/27
 */
public class MyBiFunction implements BiFunction<String, String, String> {

    @Override
    public String apply(String s, String s2) {
        return s + " ; " + s2;
    }
}
