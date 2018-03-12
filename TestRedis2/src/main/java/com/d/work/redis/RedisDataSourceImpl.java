package com.d.work.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
@author junmeng.xu
@date  2016年4月20日下午1:24:08
 */
@Repository("redisDataSource")
public class RedisDataSourceImpl implements RedisDataSource {

	private static final Logger log = LoggerFactory.getLogger(RedisDataSourceImpl.class);
	
	@Autowired
	private ShardedJedisPool shardedJedisPool;
	
	public ShardedJedis getRedisClient() {
		try {
			ShardedJedis shardedJedis = shardedJedisPool.getResource();
			return shardedJedis;
		} catch (Exception e) {
			log.error("getRedisClient error", e);
		}
		return null;
	}

	public void returnResource(ShardedJedis shardedJedis) {
		shardedJedisPool.returnBrokenResource(shardedJedis);
	}

	public void returnResource(ShardedJedis shardedJedis, boolean broken) {
		if(broken){
			shardedJedisPool.returnBrokenResource(shardedJedis);
		}else{
			shardedJedisPool.returnResource(shardedJedis);
		}
		
	}

}
