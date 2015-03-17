package com.github.zebbra.common.cache.lru;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.zebbra.common.cache.Cache;
import com.github.zebbra.common.cache.CacheException;
import com.github.zebbra.common.cache.CacheProvider;

public class LruCacheProvider implements CacheProvider {

	public static final String CACHE_NAME = "cache.name.lru";
	
	public static final String LRU_CACHE_SIZE_KEY = "lru.cache.size.key";
	
	private Map<String, Cache> caches;
	
	@Override
	public Cache buildCache(String regionName, Map<String, Object> attributes) {
		Cache cache = caches.get(regionName);
		if(cache == null) {
			if(attributes != null && attributes.containsKey(LRU_CACHE_SIZE_KEY)) {
				int max = (Integer)attributes.get(LRU_CACHE_SIZE_KEY);
				cache = new LruCache(max);
			} else {
				cache = new LruCache();
			}
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
		// no-op
	}

	@Override
	public String getName() {
		return CACHE_NAME;
	}

}
