package 与spring结合的策略模式方式二.service.impl;

import org.springframework.stereotype.Service;
import 与spring结合的策略模式方式二.service.CommonCompleteStrategy;
import 与spring结合的策略模式方式二.service.ICompleteStrategy;

/**
 * Created by james on 2018/4/4.
 */
@Service("unifyCompleteStrategy")
public class UnifyCompleteStrategy extends CommonCompleteStrategy implements ICompleteStrategy {

    @Override
    public String getCompleteStrategyCategory() {
        return "3";
    }

    @Override
    public void handler(String str) {
        System.out.println("UnifyCompleteStrategy : " + str);
    }

}
