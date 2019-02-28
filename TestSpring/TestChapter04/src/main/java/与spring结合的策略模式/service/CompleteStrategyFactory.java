package 与spring结合的策略模式.service;

import java.util.Map;

/**
 * Created by james on 2018/4/4.
 */
public class CompleteStrategyFactory {

    public Map<String, ICompleteStrategy> completeStrategyMap;

    public Map<String, ICompleteStrategy> getCompleteStrategyMap() {
        return completeStrategyMap;
    }

    public void setCompleteStrategyMap(Map<String, ICompleteStrategy> completeStrategyMap) {
        this.completeStrategyMap = completeStrategyMap;
    }

    public ICompleteStrategy getHandleByType(String type) {
        return completeStrategyMap.get(type);
    }
}
