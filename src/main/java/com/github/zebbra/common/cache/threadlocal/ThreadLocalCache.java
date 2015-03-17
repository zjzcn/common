package com.github.zebbra.common.cache.threadlocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.github.zebbra.common.cache.Cache;
import com.github.zebbra.common.cache.CacheException;

/**
 * ThreadLocalCache
 * 
 */
public class ThreadLocalCache implements Cache {

    private ThreadLocal<Map<String, Object>> store;

    public ThreadLocalCache() {
        this.store = new ThreadLocal<Map<String, Object>>() {
            @Override
            protected Map<String, Object> initialValue() {
                return new HashMap<String, Object>();
            }
        };
    }

    public void put(String key, Object value) {
        store.get().put(key, value);
    }

    public Object get(String key) {
        return store.get().get(key);
    }

	@Override
	public Set<String> keys() throws CacheException {
		return Collections.unmodifiableSet(store.get().keySet());
	}

	@Override
	public void remove(String key) throws CacheException {
		store.get().remove(key);
	}

	@Override
	public void clear() throws CacheException {
		store.get().clear();
	}

	@Override
	public void destroy() throws CacheException {
		store = null;
	}


	@Override
	public int size() throws CacheException {
		synchronized (store) {
            return store.get().size();
        }
	}

	@Override
	public boolean containsKey(String key) throws CacheException {
		synchronized (store) {
            return store.get().containsKey(key);
        }
	}

}
