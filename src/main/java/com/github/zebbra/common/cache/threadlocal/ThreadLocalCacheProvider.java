package com.github.zebbra.common.cache.threadlocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.zebbra.common.cache.Cache;
import com.github.zebbra.common.cache.CacheException;
import com.github.zebbra.common.cache.CacheProvider;

public class ThreadLocalCacheProvider implements CacheProvider {

	public static final String CACHE_NAME = "cache.name.threadlocal";
	
	private Map<String, Cache> caches;
	
	@Override
	public Cache buildCache(String regionName, Map<String, Object> attributes) throws CacheException {
		Cache cache = caches.get(regionName);
		if(cache == null) {
			cache = new ThreadLocalCache();
			caches.put(regionName, cache);
		}
		return cache;
	}

	@Override
	public void start() throws CacheException {
		caches = new ConcurrentHashMap<String, Cache>();
	}

	@Override
	public void stop() {
		// nothing
	}

	@Override
	public String getName() {
		return CACHE_NAME;
	}

}
