package com.d.work.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;

/**
@author junmeng.xu
@date  2016年4月20日下午1:23:44
 */
@Repository("redisClientTemplate")
public class RedisClientTemplate {

	private static final Logger log = LoggerFactory.getLogger(RedisClientTemplate.class);
	
	@Autowired
	private RedisDataSource redisDataSource;
	
	public void disconnect(){
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		shardedJedis.disconnect();
	}
	
	/**
	 * 设置单个值
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(String key, String value){
		String result = null;
		
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if(shardedJedis == null){
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.set(key, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}
	
	/**
	 * 获取单个值
	 * @param key
	 * @return
	 */
    public String get(String key) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.get(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    
    public Boolean exists(String key){
    	Boolean result = false;
    	ShardedJedis shardedJedis = redisDataSource.getRedisClient();
    	if(shardedJedis == null){
    		return result;
    	}
    	boolean broken = false;
    	try {
			result = shardedJedis.exists(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
    	return result;
    }
    
}
