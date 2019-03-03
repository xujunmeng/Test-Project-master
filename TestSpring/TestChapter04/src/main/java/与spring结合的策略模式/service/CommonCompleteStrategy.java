package 与spring结合的策略模式.service;

/**
 * Created by james on 2018/4/4.
 */
public abstract class CommonCompleteStrategy {

    public void commonMethod (String str) {
        System.out.println("CommonCompleteStrategy : " + str);
    }

}
