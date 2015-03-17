package com.github.zebbra.common.cache.ehcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.ehcache.CacheManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.zebbra.common.cache.Cache;
import com.github.zebbra.common.cache.CacheException;
import com.github.zebbra.common.cache.CacheProvider;
 
/**
 * Cache Provider plugin
 * 
 * Taken from EhCache 1.3 distribution
 * @author Winter Lau
 */
public class EhCacheProvider implements CacheProvider{
	
    private static final Logger log = LoggerFactory.getLogger(EhCacheProvider.class);
    
    public static final String CACHE_NAME = "cache.name.ehcache";
 
    private CacheManager manager;
    
    private Map<String, Cache> caches;
 
    /**
     * Builds a Cache.
     * <p>
     * Even though this method provides properties, they are not used.
     * Properties for EHCache are specified in the ehcache.xml file.
     * Configuration will be read from ehcache.xml for a cache declaration
     * where the name attribute matches the name parameter in this builder.
     *
     * @param regionName the name of the cache. Must match a cache configured in ehcache.xml
     * @param properties not used
     * @return a newly built cache will be built and initialised
     * @throws CacheException inter alia, if a cache of the same name already exists
     */
    public Cache buildCache(String regionName, Map<String, Object> attributes) throws CacheException {
        Cache ehcache = caches.get(regionName);
        if(ehcache == null){
            try {
                net.sf.ehcache.Cache cache = manager.getCache(regionName);
                if (cache == null) {
                    log.warn("Could not find configuration [" + regionName + "]; using defaults.");
                    manager.addCache(regionName);
                    cache = manager.getCache(regionName);
                    log.debug("started EHCache region: " + regionName);                
                }
                ehcache = new EhCache(cache);
                caches.put(regionName, ehcache);
            }
            catch (net.sf.ehcache.CacheException e) {
                throw new CacheException(e);
            }
        }
        return ehcache;
    }
 
    /**
     * Callback to perform any necessary initialization of the underlying cache implementation
     * during SessionFactory construction.
     *
     * @param properties current configuration settings.
     */
    public void start() throws CacheException {
        if (manager != null) {
            log.warn("Attempt to restart an already started EhCacheProvider. Use sessionFactory.close() " +
                    " between repeated calls to buildSessionFactory. Using previously created EhCacheProvider." +
                    " If this behaviour is required, consider using net.sf.ehcache.hibernate.SingletonEhCacheProvider.");
            return;
        }
        manager = new CacheManager();
        caches = new ConcurrentHashMap<String, Cache>();
    }
 
    /**
     * Callback to perform any necessary cleanup of the underlying cache implementation
     * during SessionFactory.close().
     */
    public void stop() {
        if (manager != null) {
            manager.shutdown();
            manager = null;
        }
    }

	@Override
	public String getName() {
		return CACHE_NAME;
	}

}