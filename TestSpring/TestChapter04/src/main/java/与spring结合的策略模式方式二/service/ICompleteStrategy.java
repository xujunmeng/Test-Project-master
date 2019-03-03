package 与spring结合的策略模式方式二.service;

/**
 * Created by james on 2018/4/4.
 */
public interface ICompleteStrategy {

    String getCompleteStrategyCategory();

    void handler(String str);

}
