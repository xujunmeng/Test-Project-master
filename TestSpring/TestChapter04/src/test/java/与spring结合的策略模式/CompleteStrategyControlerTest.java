package 与spring结合的策略模式;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import 与spring结合的策略模式.controler.CompleteStrategyControler;

/**
 * Created by james on 2018/4/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class CompleteStrategyControlerTest {

    @Autowired
    private CompleteStrategyControler completeStrategyControler;

    @Test
    public void test() {
        completeStrategyControler.completePrint("2");
    }

}
