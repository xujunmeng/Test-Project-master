package redis.dao.实例四;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by james on 2018/2/24.
 */
public class App {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "testLuaScript")
    TestRedisScript testLuaScript;

    public boolean checkAndSet(String expectedValue, String newValue) {
        String[] strs = {expectedValue, newValue};
        List keys = new ArrayList<>();
        keys.add("key1");
        keys.add("key2");
        Object obj = stringRedisTemplate.execute(testLuaScript, keys, strs);
        System.out.println(obj);
        return true;
    }

}
