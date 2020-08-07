package com.wowjoy.boot.shiro.cache;

import lombok.extern.java.Log;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * 自定义缓存管理器
 * @author scq
 * @date 2020-08-07 13:26:00
 */
@Log
public class RedisCacheManager implements CacheManager {
    // 认证，授权缓存的统一名称
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        log.info("缓存名称：" + cacheName);
        return new RedisCache<>(cacheName);
    }
}
