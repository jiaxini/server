package com.service.jiaxini.conf.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *
 * @description: 自定义CacheManager
 * </p>
 * @author: ZengGuangfu
 * @since 2019-09-2019/9/23
 */
public class CustomCacheManager implements CacheManager {

    @Autowired
    private RedisCache redisCache;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return redisCache;
    }
}
