package redis.dao.实例三.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import redis.dao.实例三.DistributeLockHelper;
import redis.dao.实例三.DistributeLockHolder;

import java.util.concurrent.TimeUnit;

/**
 * Created by james on 2017/8/10.
 */
public class RedisDistributeLockHelper implements DistributeLockHelper {

    private String applicationCode;

    private Integer maxLockTimeSeconds = 60;

    private StringRedisTemplate redisTemplate;

    private static String keyLockTemplete = "RedisLock-aCode:%s-hCode:%s";

    public RedisDistributeLockHelper(String applicationCode, StringRedisTemplate redisTemplate) {
        this.applicationCode = applicationCode;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public DistributeLockHolder getLock(String hashCode) {

        DistributeLockHolder lockHolder = generateLockHolder(hashCode);

        Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bytes = lockHolder.getLock().getBytes();
                Boolean aBoolean = connection.setNX(bytes, "true".getBytes());
                return aBoolean;
            }
        });

        if (result == true) {
            String lock = lockHolder.getLock();
            Boolean aBoolean = redisTemplate.expire(lock, maxLockTimeSeconds, TimeUnit.SECONDS);
            return lockHolder;
        }
        return null;
    }

    @Override
    public void release(DistributeLockHolder lockHolder) {
        if (lockHolder != null) {
            String lock = lockHolder.getLock();
            redisTemplate.delete(lock);
        }
    }

    @Override
    public Boolean isLocked(String hashCode) {

        DistributeLockHolder lockHolder = generateLockHolder(hashCode);

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        String lock = lockHolder.getLock();

        String s = valueOperations.get(lock);

        if (s != null) {
            return true;
        }
        return false;
    }

    private DistributeLockHolder generateLockHolder(String hashCode) {

        DistributeLockHolder lockHolder = new DistributeLockHolder();

        String format = String.format(keyLockTemplete, applicationCode, hashCode);

        lockHolder.setLock(format);

        return lockHolder;
    }


    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }

    public Integer getMaxLockTimeSeconds() {
        return maxLockTimeSeconds;
    }

    public void setMaxLockTimeSeconds(Integer maxLockTimeSeconds) {
        this.maxLockTimeSeconds = maxLockTimeSeconds;
    }

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
