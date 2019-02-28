package Function接口.实例二;

import java.util.function.Function;

/**
 * @author james
 * @date 2018/8/27
 */
public class MyFunction implements Function<String, Integer> {

    @Override
    public Integer apply(String s) {
        return s.length();
    }
}
