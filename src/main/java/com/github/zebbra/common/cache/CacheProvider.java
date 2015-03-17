package com.github.zebbra.common.cache;

import java.util.Map;


/**
* Support for pluggable caches.
*/
public interface CacheProvider {

	String getName();
   /**
    * Configure the cache
    *
    * @param regionName the name of the cache region
    * @param autoCreate autoCreate settings
    * @throws CacheException
    */
   public Cache buildCache(String regionName, Map<String, Object> attributes) throws CacheException;

   /**
    * Callback to perform any necessary initialization of the underlying cache implementation
    * during SessionFactory construction.
    *
    * @param properties current configuration settings.
    */
   public void start() throws CacheException;

   /**
    * Callback to perform any necessary cleanup of the underlying cache implementation
    * during SessionFactory.close().
    */
   public void stop();
    
}