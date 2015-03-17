package com.github.zebbra.common.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

import com.github.zebbra.common.cache.ehcache.EhCacheProvider;
import com.github.zebbra.common.cache.lru.LruCacheProvider;
import com.github.zebbra.common.cache.threadlocal.ThreadLocalCacheProvider;
import com.github.zebbra.common.cache.timetolive.TimeToLiveCacheProvider;
import com.github.zebbra.common.ruleengine.RuleEngineException;
 
/**
 * ª∫¥Ê÷˙ ÷
 */
public class CacheManager {
     
	private static final Map<String, CacheProvider> providers = new HashMap<String, CacheProvider>();
	
	static {
		initProvider();
	}
 
	private static void initProvider(){
		ServiceLoader<CacheProvider> loader;
		loader = ServiceLoader.load(CacheProvider.class);
		Iterator<CacheProvider> itr = loader.iterator();
		while (itr.hasNext()) {
			CacheProvider provider = itr.next();
			registerCacheProvider(provider.getName(), provider);
		}
	}
	
	public static CacheProvider getCacheProvider(String name) {
		CacheProvider provider = providers.get(name);
		if(provider == null) {
			throw new RuleEngineException("Can not get cache provider of name["+name+"].");
		}
		return provider;
	}
    
	public static void registerCacheProvider(String name, CacheProvider provider) {
		providers.put(name, provider);
	}

	private static void testTimeToLive() throws InterruptedException {
		CacheProvider provider = CacheManager.getCacheProvider(TimeToLiveCacheProvider.CACHE_NAME);
		provider.start();
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put(TimeToLiveCacheProvider.EXPIRATION_SECONDS, 10);
		Cache cache = provider.buildCache("test", attrs);
		cache.put("hehe", "dddd");
		System.out.println(cache.get("hehe"));
		Thread.sleep(15000);
		System.out.println(cache.get("hehe"));
	}
	
	private static void testEhCache() throws InterruptedException {
		CacheProvider provider = CacheManager.getCacheProvider(EhCacheProvider.CACHE_NAME);
		provider.start();
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put(TimeToLiveCacheProvider.EXPIRATION_SECONDS, 10);
		Cache cache = provider.buildCache("test", attrs);
		cache.put("hehe", "dddd");
		System.out.println(cache.get("hehe"));
		cache.remove("hehe");
		System.out.println(cache.get("hehe"));
	}
	
	private static void testLru() throws InterruptedException {
		CacheProvider provider = CacheManager.getCacheProvider(LruCacheProvider.CACHE_NAME);
		provider.start();
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put(TimeToLiveCacheProvider.EXPIRATION_SECONDS, 10);
		Cache cache = provider.buildCache("test", attrs);
		cache.put("hehe", "dddd");
		System.out.println(cache.get("hehe"));
		cache.remove("hehe");
		System.out.println(cache.get("hehe"));
	}

	private static void testThreadLocal() throws InterruptedException {
		CacheProvider provider = CacheManager.getCacheProvider(ThreadLocalCacheProvider.CACHE_NAME);
		provider.start();
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put(TimeToLiveCacheProvider.EXPIRATION_SECONDS, 10);
		Cache cache = provider.buildCache("test", attrs);
		cache.put("hehe", "dddd");
		System.out.println(cache.get("hehe"));
		cache.remove("hehe");
		System.out.println(cache.get("hehe"));
	}
	
	public static void main(String[] args) throws InterruptedException {
		testTimeToLive();
		
		//testEhCache();
		
		testLru();
		
		testThreadLocal();
	}

}