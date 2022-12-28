package com.kenzie.appserver.config;

import com.kenzie.appserver.service.model.Freelancer;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/** This file was created by another team member **/

public class CacheStore {
    private Cache<String, Freelancer> cache;

    public CacheStore(int expiry, TimeUnit timeUnit) {
        this.cache = CacheBuilder.newBuilder()
                .expireAfterWrite(expiry, timeUnit)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();    
    }

    public Freelancer get(String key) {
        return cache.getIfPresent(key);
    }

    public void evict(String key) {
        cache.invalidate(key);
    }

    public void add(String key, Freelancer value) {
        cache.put(key, value);
    }
}
