package 与spring结合的策略模式;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import 与spring结合的策略模式.service.CompleteStrategyFactory;
import 与spring结合的策略模式.service.ICompleteStrategy;

import java.util.Map;

/**
 * Created by james on 2018/4/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class CompleteStrategyFactoryTest {

    @Autowired
    private CompleteStrategyFactory completeStrategyFactory;

    @Test
    public void test() {
        Map<String, ICompleteStrategy> completeStrategyMap = completeStrategyFactory.getCompleteStrategyMap();
        System.out.println(completeStrategyMap);
    }

}
