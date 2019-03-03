package redis.dao.实例四;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import redis.BaseTestCase;
import redis.dao.实例三.DistributeLockHolder;
import redis.dao.实例三.impl.RedisDistributeLockHelper;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by james on 2017/8/10.
 */
public class Main extends BaseTestCase {

    @Autowired
    RedisDistributeLockHelper lockHelper;

    @Test
    public void test() throws Exception {
        DistributeLockHolder lockHolder = null;
        try {
            for (int i = 0; i < 5; i++) {

                String lockKey = "junmeng:test";

                lockHolder = lockHelper.getLock(lockKey);

                if (lockHolder == null) {
                    throw new Exception("正在使用，请稍后。。。。。");
                }
                System.out.println("1111111");

            }
        }
        finally {
                if (lockHolder != null) {
                    lockHelper.release(lockHolder);
                }
            }
        }

    @Test
    public void test2() {
        Set<Integer> ids = new HashSet<>();
        ids.add(11);
        ids.add(22);
        if (CollectionUtils.isEmpty(ids) || ids.size() > 1 || !ids.contains(11)) {
            System.out.println("sdf");
        }

    }

}
