package redis.dao.实例二.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.dao.实例二.CommonDAO;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by james on 2017/6/13.
 */
public class CommonDAOImpl implements CommonDAO {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean exists(String key, String value) {
        return stringRedisTemplate.opsForSet().isMember(key, value);
    }

    @Override
    public Set<String> getSerialNumber(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }


    @Override
    public Long setSerialNumber(String key, String value) {
        return stringRedisTemplate.opsForSet().add(key, value);
    }

    @Override
    public boolean liveTime(String key, long timeout) {
        return stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }
}
