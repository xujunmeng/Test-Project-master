package redis.dao.实例四App;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.BaseTestCase;
import redis.dao.实例四.App;

/**
 * Created by james on 2018/2/24.
 */
public class Main extends BaseTestCase {

    @Autowired
    private App app;

    @Test
    public void test() {
        app.checkAndSet("XJM1", "XJM2");
    }

}
