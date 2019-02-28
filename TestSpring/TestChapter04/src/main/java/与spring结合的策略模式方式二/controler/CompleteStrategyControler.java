package 与spring结合的策略模式方式二.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import 与spring结合的策略模式方式二.service.CompleteStrategyFactory;
import 与spring结合的策略模式方式二.service.ICompleteStrategy;

/**
 * Created by james on 2018/4/4.
 */
@Component
public class CompleteStrategyControler {

    @Autowired
    private CompleteStrategyFactory completeStrategyFactory;

    public void completePrint(String type) {
        ICompleteStrategy completeStrategy = completeStrategyFactory.getHandleByType(type);
        completeStrategy.handler("xjm");
    }

}
