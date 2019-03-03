package 与spring结合的策略模式方式二.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by james on 2018/4/4.
 */
@Component
public class CompleteStrategyFactory {

    /**
     *使用线程安全的ConcurrentHashMap存储所有实现Strategy接口的Bean
     *key:beanName
     *value：实现Strategy接口Bean
     */
    private final Map<String, ICompleteStrategy> completeStrategyMap = new ConcurrentHashMap<>();

    /**
     * 注入所有实现了Strategy接口的Bean
     */
    @Autowired
    public CompleteStrategyFactory(Map<String, ICompleteStrategy> map) {
        for (Map.Entry<String, ICompleteStrategy> entry : map.entrySet()) {
            completeStrategyMap.put(entry.getValue().getCompleteStrategyCategory(), entry.getValue());
        }

    }

    public Map<String, ICompleteStrategy> getCompleteStrategyMap() {
        return completeStrategyMap;
    }

    public ICompleteStrategy getHandleByType(String type) {
        return completeStrategyMap.get(type);
    }
}
