package com.service.jiaxini.conf.shiro.cache;

import com.service.jiaxini.conf.redis.RedisAuxiliary;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * <p>
 *
 * @description: Cache实现
 * </p>
 * @author: ZengGuangfu
 * @since 2019-09-2019/9/20
 */

@Slf4j
@Component
public class RedisCache<K,V> implements Cache<K,V> {

    public static final String PREFIXKEY = "authentication_key_";
    
    @Autowired
    private RedisAuxiliary redisAuxiliary;

    /**
     * 工具方法，用于
     */
    protected String bindingKey(K key){
        if (key instanceof String){
            return PREFIXKEY + key;
        }
        return key.toString();
    }

    @Override
    public V get(K k) throws CacheException {
        log.info("从Cache中获取value...");
        return (V)redisAuxiliary.get( bindingKey(k));
    }

    public V getValue(K k) throws CacheException {
        return (V)redisAuxiliary.get( k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {
        try{
            redisAuxiliary.setWithExpire( bindingKey(k), v, 1800);
            log.info("储存value到cache中，超时时间为半个小时");
        }catch (Exception e){
            e.printStackTrace();
            log.error("储存value失败");
        }
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        V v = get(k);
        redisAuxiliary.delete( bindingKey(k));
        return v;
    }

    @Override
    public void clear() throws CacheException {
        Set<K> keys = keys();
        Iterator<K> iterator = keys.iterator();
        while( iterator.hasNext()){
            K next = iterator.next();
            if (next instanceof String)
            redisAuxiliary.delete( (String)next);
        }
    }

    @Override
    public int size() {
        Set<K> keys = keys();
        return keys.size();
    }

    @Override
    public Set<K> keys() {
        Set<K> keys = (Set<K>)redisAuxiliary.keys(PREFIXKEY);
        if (Objects.isNull(keys)){
            return new HashSet<K>();
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> list = new ArrayList<>();
        Set<K> keys = keys();
        for (K k : keys){
            list.add( getValue(k));
        }
        return list;
    }
}
