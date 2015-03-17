package com.github.zebbra.common.cache.redis;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.github.zebbra.common.cache.Cache;
import com.github.zebbra.common.cache.CacheException;
import com.github.zebbra.common.cache.CacheProvider;

public class RedisCacheProvider implements CacheProvider {
	
	public static final String CACHE_NAME = "cache.name.redis";
	
	public static final String HOST = "redis.host";
	public static final String PORT = "redis.port";
	public static final String PASSWORD = "redis.password";
	public static final String EXPIRE_SECONDS = "redis.expire.seconds";
	public static final String CONNECT_TIMEOUT_MILLISECONDS = "redis.connect.timeout.milliseconds";
	
	private String host = "127.0.0.1";
	private int port = 6379;
	// 0 - never expire
	private int expire = 0;
	//timeout for jedis try to connect to redis server, not expire time! In milliseconds
	private int timeout = 0;
	private String password = "";
	
	private Map<String, Cache> caches;
	

	@Override
	public String getName() {
		return CACHE_NAME;
	}

	@Override
	public Cache buildCache(String regionName, Map<String, Object> attributes) throws CacheException {
		if(attributes!=null && attributes.containsKey(HOST)){
			host = (String)attributes.get(HOST);
		}
		if(attributes!=null && attributes.containsKey(PORT)){
			port = (Integer)attributes.get(PORT);
		}
		if(attributes!=null && attributes.containsKey(CONNECT_TIMEOUT_MILLISECONDS)){
			timeout = (Integer)attributes.get(CONNECT_TIMEOUT_MILLISECONDS);
		}
		if(attributes!=null && attributes.containsKey(PASSWORD)){
			password = (String)attributes.get(PASSWORD);
		}
		String key = host+":"+port+":"+regionName;
		Cache cache = caches.get(key);
		if(cache == null){
			JedisPool jedisPool;
			if(password != null && !"".equals(password)){
				jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout, password);
			}else if(timeout != 0){
				jedisPool = new JedisPool(new JedisPoolConfig(), host, port,timeout);
			}else{
				jedisPool = new JedisPool(new JedisPoolConfig(), host, port);
			}
			cache = new RedisCache(regionName, jedisPool, expire);
			caches.put(key, cache);
		}
		return cache;
	}

	@Override
	public void start() throws CacheException {
		caches = new ConcurrentHashMap<String, Cache>();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
}
