package callableFuture.把类中的方法改为一个线程;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by junmeng.xu on 2016/12/15.
 */
public class Main {

    private static ExecutorService executor = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {

        List<Callable<Map<Integer, String>>> list = new ArrayList<>();
        list.add(method1());
        list.add(method2());
        list.add(method3());
        try {
            List<Future<Map<Integer, String>>> futures = executor.invokeAll(list);
            for (Future<Map<Integer, String>> future : futures) {
                Map<Integer, String> map = future.get();
                System.out.println("运行结果" + map);
            }

            executor.shutdown();
            System.out.println("主线程在执行任务");
            System.out.println("所有任务执行完毕");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Callable<Map<Integer, String>> method1(){
        Callable<Map<Integer, String>> callable = () -> {
            String name = Thread.currentThread().getName();
            System.out.println("子线程task1在进行计算=="+ name +"===");
            Map<Integer, String> map = new HashMap<>();
            String val = "子线程task1,当前是";
            for (int i = 0; i < 10; i++) {
                map.put(i, val + i);
            }
            return map;
        };
        return callable;
    }

    public static Callable<Map<Integer, String>> method2(){
        Callable<Map<Integer, String>> callable = () -> {
            String name = Thread.currentThread().getName();
            System.out.println("子线程task2在进行计算=="+ name +"===");
            Map<Integer, String> map = new HashMap<>();
            String val = "子线程task2,当前是";
            for (int i = 0; i < 10; i++) {
                map.put(i, val + i);
            }
            return map;
        };
        return callable;
    }

    public static Callable<Map<Integer, String>> method3(){
        Callable<Map<Integer, String>> callable = () -> {
            String name = Thread.currentThread().getName();
            System.out.println("子线程task3在进行计算=="+ name +"===");
            Map<Integer, String> map = new HashMap<>();
            String val = "子线程task3,当前是";
            for (int i = 0; i < 10; i++) {
                map.put(i, val + i);
            }
            return map;
        };
        return callable;
    }


}
