package 与spring结合的策略模式方式二.service.impl;

import org.springframework.stereotype.Service;
import 与spring结合的策略模式方式二.service.CommonCompleteStrategy;
import 与spring结合的策略模式方式二.service.ICompleteStrategy;

/**
 * Created by james on 2018/4/4.
 */
@Service("activityCompleteStrategy")
public class ActivityCompleteStrategy extends CommonCompleteStrategy implements ICompleteStrategy {

    @Override
    public String getCompleteStrategyCategory() {
        return "1";
    }

    @Override
    public void handler(String str) {
        commonMethod(str);
        System.out.println("ActivityCompleteStrategy handler : " + str);
    }

    @Override
    public void commonMethod(String str) {
        System.out.println("ActivityCompleteStrategy commonMethod : " + str);
    }
}
