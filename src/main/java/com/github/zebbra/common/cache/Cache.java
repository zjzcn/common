package com.github.zebbra.common.cache;

import java.util.Set;

/**
 * »º´æ½Ó¿Ú
 */
public interface Cache {
 
    public void put(String key, Object value) throws CacheException;
     
    public Object get(String key) throws CacheException;
 
    public Set<String> keys() throws CacheException ;
     
    public int size() throws CacheException ;
    
    boolean containsKey(String key) throws CacheException;
    
    public void remove(String key) throws CacheException;
     
    public void clear() throws CacheException;
     
    public void destroy() throws CacheException;
     
}