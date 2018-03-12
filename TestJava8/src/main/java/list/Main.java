package list;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by junmeng.xu on 2016/11/30.
 */
public class Main {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        list.add("ddd2");
        list.add("aaa2");
        list.add("bbb1");
        list.add("aaa1");
        list.add("bbb3");
        list.add("ccc");
        list.add("bbb2");
        list.add("ddd1");
        list.stream().filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);
    }


    @Test
    public void test(){
        List<String> list = new ArrayList<String>();
        list.add("ddd2");
        list.add("aaa2");
        list.add("bbb1");
        list.add("aaa1");
        list.add("bbb3");
        list.add("ccc");
        list.add("bbb2");
        list.add("ddd1");
        list.stream().sorted().filter(t -> t.startsWith("a")).forEach(t -> System.out.println(t));
    }

    @Test
    public void test2(){
        List<String> list = new ArrayList<String>();
        list.add("ddd2");
        list.add("aaa2");
        list.add("bbb1");
        list.add("aaa1");
        list.add("bbb3");
        list.add("ccc");
        list.add("bbb2");
        list.add("ddd1");
        Stream<String> stream = list.stream();
        Stream<String> map = stream.map(t -> t.toUpperCase());
        map.sorted((o1, o2) -> o1.compareTo(o2));
    }

    /**
     * 去重
     */
    @Test
    public void tsed(){
        List<String> cityList = new ArrayList<>();
        cityList.add("Delhi");
        cityList.add("Mumbai");
        cityList.add("Bangalore");
        cityList.add("Chennai");
        cityList.add("Kolkata");
        cityList.add("Mumbai");

        cityList = cityList.stream().distinct().collect(Collectors.toList());
        System.out.println(cityList);
    }

    /**
     * 去重
     */
    @Test
    public void asdf(){
        List<Xjm> list = new ArrayList<>();
        Xjm a = new Xjm();
        a.setAge(1);
        a.setName("aaa");
        list.add(a);
        Xjm b = new Xjm();
        b.setAge(2);
        b.setName("bbb");
        list.add(b);
        Xjm c = new Xjm();
        c.setAge(2);
        c.setName("bbb");
        list.add(c);
        list = list.stream().filter(distinctByKey(p -> p.getName())).collect(Collectors.toList());
        System.out.println(list);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Test
    public void test3(){
        List<String> list = new ArrayList<String>();
        list.add("ddd2");
        list.add("aaa2");
        list.add("bbb1");
        list.add("aaa1");
        list.add("bbb3");
        list.add("ccc");
        list.add("bbb2");
        list.add("ddd1");
        Stream<String> stream = list.stream();
        Stream<String> map = stream.map(new Function<String, String>() {

            @Override
            public String apply(String t) {
                return t.toUpperCase();
            }
        });
        Stream<String> sorted = map.sorted(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        long count = sorted.count();
        System.out.println(count);
    }



    @Test
    public void test4(){
        List<String> list = new ArrayList<String>();
        list.add("ddd2");
        list.add("aaa2");
        list.add("bbb1");
        list.add("aaa1");
        list.add("bbb3");
        list.add("ccc");
        list.add("bbb2");
        list.add("ddd1");
        Stream<String> stream = list.stream();
        Stream<String> map = stream.map(new Function<String, String>() {

            @Override
            public String apply(String t) {
                return t.toUpperCase();
            }
        });
        Stream<String> sorted = map.sorted(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        Optional<String> reduce = sorted.reduce(new BinaryOperator<String>() {

            @Override
            public String apply(String t, String u) {
                return t + "#" + u;
            }
        });
        reduce.ifPresent(new Consumer<String>() {

            @Override
            public void accept(String t) {
                System.out.println(t);
            }
        });
        System.out.println(reduce.toString());
    }

    @Test
    public void testere(){
        boolean a = false && false;
        System.out.println(a);
    }
    @Test
    public void testObj(){
        List<Xjm> list = new ArrayList<>();
        Xjm a = new Xjm();
        a.setAge(1);
        a.setName("aaa");
        Xjm b = new Xjm();
        b.setAge(2);
        b.setName("bbb");
        list.add(a);
        list.add(b);
        List<String> collect = list.stream().map((obj) -> obj.getName()).collect(Collectors.toList());
        for (String string : collect) {
            System.out.println(string);
        }
    }


    public class Xjm{
        private String name;
        private Integer age;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Integer getAge() {
            return age;
        }
        public void setAge(Integer age) {
            this.age = age;
        }
        @Override
        public String toString() {
            return "Xjm [name=" + name + ", age=" + age + "]";
        }
    }
}
