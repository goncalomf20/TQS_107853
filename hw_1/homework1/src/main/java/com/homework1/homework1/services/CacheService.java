package com.homework1.homework1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

import com.homework1.homework1.Generated;

import java.util.Map;

@Generated
@Component
@EnableCaching
public class CacheService {

    @Autowired
    private final CacheManager cacheManager;

    @Autowired
    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void putIntoCache(String key, Map<String, Object> value) {
        Cache cache = cacheManager.getCache("currencies");
        cache.put(key, value);
    }

    public Map<String, Object> getFromCache(String key) {
        Cache cache = cacheManager.getCache("currencies");
        return cache.get(key, Map.class);
    }

    public void clearCache() {
        Cache cache = cacheManager.getCache("currencies");
        cache.clear();
    }

    

}