package 与spring结合的策略模式.service.impl;

import 与spring结合的策略模式.service.CommonCompleteStrategy;
import 与spring结合的策略模式.service.ICompleteStrategy;

/**
 * Created by james on 2018/4/4.
 */
public class FuturesCompleteStrategy extends CommonCompleteStrategy implements ICompleteStrategy {

    @Override
    public void handler(String str) {
        commonMethod(str);
        System.out.println("FuturesCompleteStrategy : " + str);
    }
}
