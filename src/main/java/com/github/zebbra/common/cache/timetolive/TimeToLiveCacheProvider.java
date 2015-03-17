package com.github.zebbra.common.cache.timetolive;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.zebbra.common.cache.Cache;
import com.github.zebbra.common.cache.CacheException;
import com.github.zebbra.common.cache.CacheProvider;

public class TimeToLiveCacheProvider implements CacheProvider {
	
	public static final String CACHE_NAME = "cache.name.timetolive";
	
	public static final String EXPIRATION_SECONDS = "time.map.cache.expiration.seconds.key";
	
	public static final String BUCKET_NUM = "time.map.cache.bucket.num.key";
	
	private Map<String, Cache> caches;
	
	@Override
	public Cache buildCache(String regionName, Map<String, Object> attributes) throws CacheException {
		Cache cache = caches.get(regionName);
		if(cache == null) {
			if(attributes == null || !attributes.containsKey(EXPIRATION_SECONDS)) {
				throw new CacheException("Attributes must contain key[" + EXPIRATION_SECONDS + "]");
			}

			int seconds = (Integer)attributes.get(EXPIRATION_SECONDS);
			if(seconds <= 0) {
				throw new CacheException("Expiration seconds must be greater than 0, but now is "+seconds);
			}

			if(attributes.containsKey(BUCKET_NUM)) {
				int buckets = (Integer)attributes.get(BUCKET_NUM);
				if(buckets <= 0) {
					throw new CacheException("Buckets num must be greater than 0, but now is "+buckets);
				}
				cache = new TimeToLiveCache(seconds, buckets);
			} else {
				cache = new TimeToLiveCache(seconds);
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
		// nothing
	}

	@Override
	public String getName() {
		return CACHE_NAME;
	}

}
