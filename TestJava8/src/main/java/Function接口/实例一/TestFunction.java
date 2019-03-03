package Function接口.实例一;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Function	Function< T, R >	接收T对象，返回R对象
 *
 * //将Function对象应用到输入的参数上，然后返回计算结果。
 * R apply(T t);
 *
 *
 * 返回一个先执行当前函数对象apply方法再执行after函数对象apply方法的函数对象。
 * default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
 *         Objects.requireNonNull(after);
 *         return (T t) -> after.apply(apply(t));
 *         }
 *
 * 返回一个先执行before函数对象apply方法再执行当前函数对象apply方法的函数对象
 * default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
 * Objects.requireNonNull(before);
 * return (V v) -> apply(before.apply(v));
 * }
 *
 *
 *
 * @author james
 * @date 2018/8/27
 */
public class TestFunction {

    @Test
    public void test() {

        Function<Integer, Integer> name = e -> e * 2;

        Function<Integer, Integer> square = e -> e * e;

        Object result = name.andThen(square).apply(2);
        System.out.println(result);

        System.out.println("==========================");

        Integer result2 = name.compose(square).apply(3);
        System.out.println(result2);

        Function<Integer, String> name2 = new Function<Integer, String>() {
            @Override
            public String apply(Integer e) {
                return String.valueOf(e);
            }
        };
    }

    public <V, R> List<R> getResultList(Function<List<V>, List<R>> wrapper, List<V> list, int pageSize) {

        List<R> result = new ArrayList<>();
        for (int i = 0; i < list.size();) {
            List<V> subList = list.stream().skip(i).limit(pageSize).collect(Collectors.toList());
            List<R> subResult = wrapper.apply(subList);
            result.addAll(subResult);
            i += pageSize;
        }
        return result;
    }

}
