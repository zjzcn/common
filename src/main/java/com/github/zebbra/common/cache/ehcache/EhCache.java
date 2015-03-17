package com.github.zebbra.common.cache.ehcache;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.github.zebbra.common.cache.Cache;
import com.github.zebbra.common.cache.CacheException;
 
/**
 * EHCache
 */
public class EhCache implements Cache {
     
    private net.sf.ehcache.Cache ehcache;
 
    /**
     * Creates a new Hibernate pluggable cache based on a cache name.
     * <p/>
     *
     * @param cache The underlying EhCache instance to use.
     */
    public EhCache(net.sf.ehcache.Cache cache) {
        this.ehcache = cache;
    }
 
    @SuppressWarnings("unchecked")
	public Set<String> keys() throws CacheException {
    	Set<String> keys = new HashSet<String>(this.ehcache.getKeys());
        return Collections.unmodifiableSet(keys);
    }
 
    /**
     * Gets a value of an element which matches the given key.
     *
     * @param key the key of the element to return.
     * @return The value placed into the cache with an earlier put, or null if not found or expired
     * @throws CacheException
     */
	@Override
    public Object get(String key) throws CacheException {
        try {
            if ( key == null ) 
                return null;
            else {
                Element element = ehcache.get( key );
                if ( element != null )
                    return (Object)element.getObjectValue();                
            }
            return null;
        }
        catch (net.sf.ehcache.CacheException e) {
            throw new CacheException( e );
        }
    }
 
    /**
     * Puts an object into the cache.
     *
     * @param key   a key
     * @param value a value
     * @throws CacheException if the {@link CacheManager}
     *                        is shutdown or another {@link Exception} occurs.
     */
    public void put(String key, Object value) throws CacheException {
        try {
            Element element = new Element( key, value );
            ehcache.put( element );
        }
        catch (IllegalArgumentException e) {
            throw new CacheException( e );
        }
        catch (IllegalStateException e) {
            throw new CacheException( e );
        }
        catch (net.sf.ehcache.CacheException e) {
            throw new CacheException( e );
        }
 
    }
 
    /**
     * Removes the element which matches the key.
     * <p/>
     * If no element matches, nothing is removed and no Exception is thrown.
     *
     * @param key the key of the element to remove
     * @throws CacheException
     */
    @Override
    public void remove(String key) throws CacheException {
        try {
            ehcache.remove(key);
        }
        catch (IllegalStateException e) {
            throw new CacheException( e );
        }
        catch (net.sf.ehcache.CacheException e) {
            throw new CacheException( e );
        }
    }
 
    /**
     * Remove all elements in the cache, but leave the cache
     * in a useable state.
     *
     * @throws CacheException
     */
    public void clear() throws CacheException {
        try {
            ehcache.removeAll();
        }
        catch (IllegalStateException e) {
            throw new CacheException( e );
        }
        catch (net.sf.ehcache.CacheException e) {
            throw new CacheException( e );
        }
    }
 
    /**
     * Remove the cache and make it unuseable.
     *
     * @throws CacheException
     */
    public void destroy() throws CacheException {
        try {
            ehcache.getCacheManager().removeCache( ehcache.getName() );
        }
        catch (IllegalStateException e) {
            throw new CacheException( e );
        }
        catch (net.sf.ehcache.CacheException e) {
            throw new CacheException( e );
        }
    }

	@Override
	public int size() throws CacheException {
		 try {
	            return ehcache.getSize();
	        }
	        catch (IllegalStateException e) {
	            throw new CacheException( e );
	        }
	        catch (net.sf.ehcache.CacheException e) {
	            throw new CacheException( e );
	        }
	}

	@Override
	public boolean containsKey(String key) throws CacheException {
		return ehcache.isKeyInCache(key);
	}

}