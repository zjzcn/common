package com.github.zebbra.common.cache.timetolive;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import com.github.zebbra.common.cache.Cache;
import com.github.zebbra.common.cache.CacheException;

public class TimeToLiveCache implements Cache {
	// this default ensures things expire at most 50% past the expiration time
	private static final int DEFAULT_NUM_BUCObjectETS = 3;

	private final LinkedList<HashMap<String, Object>> buckets;
	private final Object lock = new Object();
	private final Thread cleaner;

	public TimeToLiveCache(int expirationSecs, int numBuckets) {
		if (numBuckets < 2) {
			throw new IllegalArgumentException("numBuckets must be >= 2");
		}
		buckets = new LinkedList<HashMap<String, Object>>();
		for (int i = 0; i < numBuckets; i++) {
			buckets.add(new HashMap<String, Object>());
		}
		final long expirationMillis = expirationSecs * 1000L;
		final long sleepTime = expirationMillis / (numBuckets - 1);
		cleaner = new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						Thread.sleep(sleepTime);
						synchronized (lock) {
							buckets.removeLast();
							buckets.addFirst(new HashMap<String, Object>());
						}
					}
				} catch (InterruptedException ex) {
					
				}
			}
		});
		cleaner.setDaemon(true);
		cleaner.start();
	}

	public TimeToLiveCache(int expirationSecs) {
		this(expirationSecs, DEFAULT_NUM_BUCObjectETS);
	}

	@Override
	public boolean containsKey(String key) {
		synchronized (lock) {
			for (HashMap<String, Object> bucket : buckets) {
				if (bucket.containsKey(key)) {
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public Object get(String key) {
		synchronized (lock) {
			for (HashMap<String, Object> bucket : buckets) {
				if (bucket.containsKey(key)) {
					return bucket.get(key);
				}
			}
			return null;
		}
	}

	@Override
	public void put(String key, Object value) {
		synchronized (lock) {
			Iterator<HashMap<String, Object>> it = buckets.iterator();
			HashMap<String, Object> bucket = it.next();
			bucket.put(key, value);
			while (it.hasNext()) {
				bucket = it.next();
				bucket.remove(key);
			}
		}
	}

	@Override
	public void remove(String key) {
		synchronized (lock) {
			for (HashMap<String, Object> bucket : buckets) {
				if (bucket.containsKey(key)) {
					bucket.remove(key);
				}
			}
		}
	}
	
	@Override
	public int size() {
		synchronized (lock) {
			int size = 0;
			for (HashMap<String, Object> bucket : buckets) {
				size += bucket.size();
			}
			return size;
		}
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			cleaner.interrupt();
		} finally {
			super.finalize();
		}
	}

	@Override
	public Set<String> keys() throws CacheException {
		synchronized (lock) {
			Set<String> keys = new HashSet<String>();
			for (HashMap<String, Object> bucket : buckets) {
				keys.addAll(bucket.keySet());
			}
			return Collections.unmodifiableSet(keys);
		}
	}

	@Override
	public void clear() throws CacheException {
		synchronized (lock) {
			for (HashMap<String, Object> bucket : buckets) {
				bucket.clear();
			}
		}
	}

	@Override
	public void destroy() throws CacheException {
		// TODO Auto-generated method stub
		
	}
}
