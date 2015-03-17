package com.github.zebbra.common.cache.lru;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.github.zebbra.common.cache.Cache;
import com.github.zebbra.common.cache.CacheException;

public class LruCache implements Cache {
	
	private static final int DEFAULT_MAX_SIZE = 1000;
	
    private Map<String, Object> store;

    public LruCache(final int max) {
        this.store = new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 1L;

			@Override
            protected boolean removeEldestEntry(Entry<String, Object> eldest) {
                return this.size() > max;
            }
        };
    }

    public LruCache() {
    	this(DEFAULT_MAX_SIZE);
    }
    @Override
    public void put(String key, Object value) throws CacheException {
        synchronized (store) {
        	store.put(key, value);
        }
    }

    public Object get(String key) {
        synchronized (store) {
            return store.get(key);
        }
    }

	@Override
	public Set<String> keys() throws CacheException {
		return Collections.unmodifiableSet(store.keySet());
	}

	@Override
	public void remove(String key) throws CacheException {
		synchronized (store) {
            store.remove(key);
        }
	}

	@Override
	public void clear() throws CacheException {
		synchronized (store) {
            store.clear();
        }
	}

	@Override
	public void destroy() throws CacheException {
		store = null;
	}

	@Override
	public int size() throws CacheException {
		synchronized (store) {
            return store.size();
        }
	}

	@Override
	public boolean containsKey(String key) throws CacheException {
		synchronized (store) {
            return store.containsKey(key);
        }
	}

}
