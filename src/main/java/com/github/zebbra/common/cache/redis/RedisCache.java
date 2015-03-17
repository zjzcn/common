package com.github.zebbra.common.cache.redis;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.github.zebbra.common.cache.Cache;
import com.github.zebbra.common.cache.CacheException;

public class RedisCache implements Cache {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
	/**
     * The wrapped Jedis instance.
     */
	private JedisPool jedisPool;
	private String regionName = "";
	private int expireSeconds = 0;
	
	/**
	 * 通过一个JedisManager实例构造RedisCache
	 */
	public RedisCache(String regionName, JedisPool jedisPool, int expireSeconds){
		this.regionName = regionName;
		if (jedisPool == null) {
			throw new IllegalArgumentException("JedisPool argument cannot be null.");
		}
		this.jedisPool = jedisPool;
		this.expireSeconds = expireSeconds;
	}
	
	@Override
	public void clear() throws CacheException {
		logger.debug("从redis中删除所有元素");
		Jedis jedis = jedisPool.getResource();
		try{
			jedis.flushDB();
		}finally{
			jedisPool.returnResource(jedis);
		}
	}

	@Override
	public int size() {
		Jedis jedis = jedisPool.getResource();
		try{
			return jedis.keys(regionName+":*").size();
		}finally{
			jedisPool.returnResource(jedis);
		}
	}

	@Override
	public Set<String> keys() {
		Jedis jedis = jedisPool.getResource();
		try{
			return jedis.keys(regionName+":*");
		}finally{
			jedisPool.returnResource(jedis);
		}
	}

	@Override
	public void put(String key, Object value) throws CacheException {
		key = redisKey(regionName, key);
		Jedis jedis = jedisPool.getResource();
		try{
			jedis.set((String)key,(String)value);
			if(this.expireSeconds > 0){
				jedis.expire((String)key, this.expireSeconds);
		 	}
		}finally{
			jedisPool.returnResource(jedis);
		}
	}

	@Override
	public Object get(String key) throws CacheException {
		key = redisKey(regionName, key);
		String value = null;
		Jedis jedis = jedisPool.getResource();
		try{
			value = jedis.get((String)key);
		}finally{
			jedisPool.returnResource(jedis);
		}
		return value;
	}

	@Override
	public boolean containsKey(String key) throws CacheException {
		key = redisKey(regionName, key);
		Jedis jedis = jedisPool.getResource();
		try{
			return jedis.exists((String)key);
		}finally{
			jedisPool.returnResource(jedis);
		}
	}

	@Override
	public void remove(String key) throws CacheException {
		key = redisKey(regionName, key);
		Jedis jedis = jedisPool.getResource();
		try{
			jedis.del(key);
		}finally{
			jedisPool.returnResource(jedis);
		}
	}

	@Override
	public void destroy() throws CacheException {
		// TODO Auto-generated method stub
		
	}

	private String redisKey(String regionName, String key) {
		return regionName + ":" + key;
 	}
}